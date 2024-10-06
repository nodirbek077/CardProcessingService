package uz.asgardia.carPledge.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.asgardia.carPledge.payload.request.CarPledgeContractRequest;
import uz.asgardia.carPledge.payload.response.CarPledgeContractResponse;
import uz.asgardia.carPledge.service.CarPledgeService;

@RestController
@RequestMapping("/api/car-pledge")
public class CarPledgeController {
    private final CarPledgeService carPledgeService;

    public CarPledgeController(CarPledgeService carPledgeService) {
        this.carPledgeService = carPledgeService;
    }

    @PostMapping("/contract/create")
    public HttpEntity<?> createContract(@Valid @RequestBody CarPledgeContractRequest carPledgeContractRequestDto){
        CarPledgeContractResponse createdContract = carPledgeService.createContract(carPledgeContractRequestDto);
        return ResponseEntity.ok(createdContract);
    }
}
