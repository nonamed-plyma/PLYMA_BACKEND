package org.testboard.plyma_backend.domain.user.presentation.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpRequest {
    @NotBlank
    @Size(min = 1, max = 4)
    private String userName;

//    @NotBlank
//    @Size(min = 4, max = 4)
    @Column(length = 4, nullable = false)
    private Integer classNum;

    @NotBlank
    @Size(min = 4, max = 10)
    private String userId;

    @NotBlank
    @Size(min = 4, max = 18)
    private String password;
}
