package com.stc.assessment.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record CreateFileRequest(@NotNull(message = "File can't be null") MultipartFile file,
                                @NotNull(message = "Space Id can't be null") Long spaceId,
                                Long folderId) {
}
