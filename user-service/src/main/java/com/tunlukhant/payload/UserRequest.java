package com.tunlukhant.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotBlank(message = "Name is required")
    private String name;

    private String surname;

    @Email(message = "Email should be valid")
    private String email;

    private String address;

    private boolean alerting;

    private double energyAlertingThreshold;
}
