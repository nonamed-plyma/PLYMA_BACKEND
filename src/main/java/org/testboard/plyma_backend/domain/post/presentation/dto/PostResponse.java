package org.testboard.plyma_backend.domain.post.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import org.testboard.plyma_backend.domain.comment.presentation.dto.CommentResponse;

import java.util.List;

@Getter
public class PostResponse {
    private final Long id;
    private final String userId;
    private final String userNickname;
    private final String createDate;
    private final String title;
    private final String content;
    private final String state;
    private final List<CommentResponse> comments;

    @Builder
    private PostResponse(Long id, String userId, String userNickname, String title, String content, String createDate, String state, List<CommentResponse> comments){
        this.id = id;
        this.userId = userId;
        this.userNickname = userNickname;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.state = state;
        this.comments = comments;
    }
}
