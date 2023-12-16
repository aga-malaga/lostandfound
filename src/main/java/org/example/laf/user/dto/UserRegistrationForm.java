package org.example.laf.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegistrationForm(
        @NotBlank @Size(max = 64) String firstName,
        @NotBlank @Size(max = 64) String lastName,
        @NotNull @Size(max = 254) String email,
        @Size(min = 9, max = 20) String phoneNumber,
        @NotBlank @Size(min = 6, max = 32) String password,
        @NotBlank @Size(min = 6, max = 32) String confirmPassword
) {
}
