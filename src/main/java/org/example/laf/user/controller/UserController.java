package org.example.laf.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.laf.user.UserFacade;
import org.example.laf.user.dto.UserRegistrationForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
class UserController {

    private static final class Routes {
        private static final String REGISTRATION_USER = "/api/registration/user";
    }

    private final UserFacade userFacade;

    @PostMapping(Routes.REGISTRATION_USER)
    UUID registerUser(@RequestBody UserRegistrationForm registrationForm) {
        return userFacade.register(registrationForm);
    }
}