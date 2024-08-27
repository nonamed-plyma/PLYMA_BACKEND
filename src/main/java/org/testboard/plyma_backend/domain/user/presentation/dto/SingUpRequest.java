package org.testboard.plyma_backend.domain.user.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SingUpRequest {
    @NotBlank
    @Size(min = 1, max = 4)
    private String nickname;

    @NotBlank
    @Size(min = 4, max = 4)
    private String userId;

    @NotBlank
    @Size(min = 4, max = 18)
    private String password;
}
