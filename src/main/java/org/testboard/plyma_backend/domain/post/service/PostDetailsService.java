package org.testboard.plyma_backend.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testboard.plyma_backend.domain.comment.domain.Comment;
import org.testboard.plyma_backend.domain.comment.presentation.dto.CommentResponse;
import org.testboard.plyma_backend.domain.post.domain.Post;
import org.testboard.plyma_backend.domain.post.domain.repository.PostRepository;
import org.testboard.plyma_backend.domain.post.presentation.dto.PostResponse;
import org.testboard.plyma_backend.domain.post.service.exception.PostNotFoundException;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostDetailsService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostResponse getPostDetails(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);

        return PostResponse.builder()
                .id(post.getId())
                .userId(post.getUser().getUserId())
                .userNickname(post.getUser().getName())
                .createDate(post.getCreateDate())
                .title(post.getTitle())
                .content(post.getContent())
                .comments(post.getCommentList().stream().map(comment -> {
                    return CommentResponse.builder()
                            .id(comment.getId())
                            .content(comment.getContent())
                            .nickName(comment.getUser().getName())
                            .build();
                }
                ).collect(Collectors.toList()))
                .build();
    }
}
