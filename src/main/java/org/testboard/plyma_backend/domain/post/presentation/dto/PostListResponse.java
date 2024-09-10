package org.testboard.plyma_backend.domain.post.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PostListResponse {
    private final List<PostResponse> postResponses;
    private final int ListCount;

    @Getter
    @Builder
    public static class PostResponse{
        private final Long id;
        private final String userNickname;
        private final String title;
        private final String crateDate;
        private final String state;
    }
}
