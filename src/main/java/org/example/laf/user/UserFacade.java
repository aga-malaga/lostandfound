package org.example.laf.user;

import org.example.laf.user.dto.PasswordForm;
import org.example.laf.user.dto.UserRegistrationForm;
import org.example.laf.user.dto.UserUpdateForm;

import java.util.UUID;

public interface UserFacade {
    UUID register(UserRegistrationForm registrationForm);
    void update(UUID userUuid, UserUpdateForm userUpdateForm);
    void changePassword(PasswordForm passwordForm);
}