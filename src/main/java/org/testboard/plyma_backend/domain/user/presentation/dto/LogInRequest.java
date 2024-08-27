package org.testboard.plyma_backend.domain.user.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LogInRequest {
    @NotBlank
    @Size(min = 4, max = 12)
    private String userId;

    @NotBlank
    @Size(min = 4, max = 25)
    private String password;
}
