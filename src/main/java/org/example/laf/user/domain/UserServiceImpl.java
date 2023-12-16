package org.example.laf.user.domain;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.example.laf.security.UserDetails;
import org.example.laf.user.UserFacade;
import org.example.laf.user.dto.PasswordForm;
import org.example.laf.user.dto.UserRegistrationForm;
import org.example.laf.user.dto.UserUpdateForm;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.UUID;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.example.laf.user.domain.UserMapper.toEntity;

@RequiredArgsConstructor
class UserServiceImpl implements UserFacade {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    static {
        try (ValidatorFactory validatorFactory = Validation.byDefaultProvider().configure()
                .buildValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
    }

    private static final Validator validator;

    @Transactional
    @Override
    public UUID register(UserRegistrationForm registrationForm) {
        validator.validate(registrationForm);

        String password = passwordEncoder.encode(registrationForm.password());
        userRepository.save(toEntity(registrationForm, password));
        return null;
    }

    @Transactional
    @Override
    public void update(UUID userUuid, UserUpdateForm updateForm) {
        validator.validate(updateForm);

        User user = findUser(userUuid);
        verifyIfUserActive(user);

        user.setFirstName(updateForm.firstName());
        user.setLastName(updateForm.lastName());
        user.setPhoneNumber(updateForm.phoneNumber());
    }

    @Transactional
    @Override
    public void changePassword(PasswordForm passwordForm) {
        User user = findUser(getLoggedInUserUuid());
        user.setPassword(passwordEncoder.encode(passwordForm.password()));
    }

    private User findUser(UUID userUuid) {
        return userRepository.findByUuid(userUuid)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    private void verifyIfUserActive(User user) {
        if (isFalse(user.isActivated())) {
            throw new RuntimeException("User not active");
        }
    }


    public static UUID getLoggedInUserUuid() {
        if (isLoggedIn()) {
            return getLoggedInUser().getUuid();
        }
        return null;
    }

    public static boolean isLoggedIn() {
        return nonNull(SecurityContextHolder.getContext().getAuthentication())
                && isFalse(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    private static UserDetails getLoggedInUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}