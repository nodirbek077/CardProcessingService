package uz.asgardia.carPledge.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import uz.asgardia.carPledge.payload.request.CarPledgeContractRequest;
import uz.asgardia.carPledge.payload.response.CarPledgeContractResponse;

import java.sql.Types;
import java.util.Map;

@Service
public class CarPledgeService {
    private final JdbcTemplate  jdbcTemplate;

    public CarPledgeService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CarPledgeContractResponse createContract(CarPledgeContractRequest contract){
        CarPledgeContractResponse response;

        try {
            SqlOutParameter vErrorCode = new SqlOutParameter("v_error_code", Types.INTEGER);
            SqlOutParameter vErrorMsg = new SqlOutParameter("v_error_msg", Types.VARCHAR);

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
            String packageName = "CAR_PLEDGE_API";
            jdbcCall.withCatalogName(packageName);
            jdbcCall.withFunctionName("CREATE_CONTRACT");
            jdbcCall.declareParameters(vErrorCode,vErrorMsg);

            SqlParameterSource parameterSourceIn = new MapSqlParameterSource()
                    .addValue("v_name", contract.getName());

            Map<String, Object> parameterSourceOut = jdbcCall.execute(parameterSourceIn);

            int result = Integer.parseInt(String.valueOf(parameterSourceOut.get("v_error_code")));

            if (result == 0) {
                Integer contractId = Integer.parseInt(String.valueOf(parameterSourceOut.get("return")));
                response = new CarPledgeContractResponse(contractId);
            }else if (result < -20000)
                response = new CarPledgeContractResponse(result + 20000, String.valueOf(parameterSourceOut.get("v_error_msg")));
            else
                response = new CarPledgeContractResponse(result, String.valueOf(parameterSourceOut.get("v_error_msg")));
        }catch (Exception e){
            response = new CarPledgeContractResponse(-1, e.getMessage());
        }

        return response;
    }
}
