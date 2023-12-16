package org.example.laf.user.domain;

import lombok.NoArgsConstructor;
import org.example.laf.user.dto.UserRegistrationForm;

@NoArgsConstructor
final class UserMapper {

    static User toEntity(UserRegistrationForm form, String password) {
        User user = new User();
        user.setFirstName(form.firstName());
        user.setLastName(form.lastName());
        user.setEmail(form.email());
        user.setPhoneNumber(form.phoneNumber());
        user.setPassword(password);
        return user;
    }
}
