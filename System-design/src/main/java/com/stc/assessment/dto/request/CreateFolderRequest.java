package com.stc.assessment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateFolderRequest(@NotBlank(message = "Folder name is required") String name,
                                  @NotNull(message = "Space Id is required") Long spaceId) {
}
