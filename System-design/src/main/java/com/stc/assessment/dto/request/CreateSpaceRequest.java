package com.stc.assessment.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateSpaceRequest(@NotBlank(message = "Space name is required") String name) {
}
