package org.example.laf.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PasswordForm(
        @NotBlank @Size(min = 6, max = 32) String password,
        @NotBlank @Size(min = 6, max = 32) String confirmPassword
) {
}