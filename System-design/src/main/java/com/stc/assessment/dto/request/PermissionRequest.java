package com.stc.assessment.dto.request;

import com.stc.assessment.enums.PermissionLevel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record PermissionRequest(@NotNull PermissionLevel level, @NotNull @Email String userEmail, @NotNull Long groupId) {
}
