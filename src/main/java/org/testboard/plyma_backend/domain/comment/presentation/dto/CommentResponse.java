package org.testboard.plyma_backend.domain.comment.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponse {
    private final Long id;
    private final String nickName;
    private final String content;
    private final String createDate;

    @Builder
    CommentResponse(Long id, String nickName, String content, String createDate){
        this.id = id;
        this.nickName = nickName;
        this.content = content;
        this.createDate = createDate;
    }
}
