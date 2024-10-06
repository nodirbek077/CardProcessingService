package uz.asgardia.carPledge.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CarPledgeContractResponse {
    private Integer contractId;
    @JsonIgnore
    private Integer errorCode;
    @JsonIgnore
    private String errorMessage;

    public CarPledgeContractResponse(Integer contractId) {
        this.contractId = contractId;
    }

    public CarPledgeContractResponse(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
