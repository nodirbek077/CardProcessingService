package uz.asgardia.carPledge.payload.request;

import jakarta.validation.constraints.NotNull;

public class CarPledgeContractRequest {
    @NotNull(message = "name cannot be null")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
