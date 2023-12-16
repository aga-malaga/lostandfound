package org.example.laf.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class AdminProfileController {

    private static final class Routes {
        private static final String USER_PROFILE = "/api/backoffice/user-profile";
    }
}