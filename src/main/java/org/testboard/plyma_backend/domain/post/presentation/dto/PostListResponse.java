package org.testboard.plyma_backend.domain.post.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponse {
    private final Long id;
    private final String userId;
    private final String userNickname;
    private final String title;
    private final String content;

    @Builder
    private PostResponse(Long id, String userId, String userNickname, String title, String content){
        this.id = id;
        this.userId = userId;
        this.userNickname = userNickname;
        this.title = title;
        this.content = content;
    }
}
