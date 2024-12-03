package org.testboard.plyma_backend.domain.post.presentation.dto;

import lombok.Getter;

@Getter
public class ReturnIdResponse {
    private Long id;

    public ReturnIdResponse(Long id){
        this.id = id;
    }
}
