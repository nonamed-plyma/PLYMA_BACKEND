package org.testboard.plyma_backend.domain.comment.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentRequest {

    @NotNull
    private Long id;

    @NotBlank
    private String content;
}
