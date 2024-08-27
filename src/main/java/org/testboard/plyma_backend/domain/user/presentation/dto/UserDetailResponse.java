package org.testboard.plyma_backend.domain.user.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDetailResponse {
    private final String nickname;
    private final String userId;
    private final Integer totalPosts;
}
