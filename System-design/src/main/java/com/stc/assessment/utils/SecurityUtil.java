package com.stc.assessment.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public final class SecurityUtil {

    private SecurityUtil() throws InstantiationException {
        throw new InstantiationException("Util class");
    }

    public static Optional<String> getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Objects.nonNull(authentication) && authentication.isAuthenticated()
                ? Optional.of(authentication.getName()) : Optional.empty();

    }
}
