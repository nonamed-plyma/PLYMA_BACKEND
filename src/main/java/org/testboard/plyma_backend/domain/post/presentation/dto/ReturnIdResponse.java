package org.testboard.plyma_backend.domain.post.presentation.dto;

import lombok.Getter;

@Getter
public class ReturnIdResponse {
    private Long userId;

    public ReturnIdResponse(Long userId){
        this.userId = userId;
    }
}
