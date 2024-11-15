package org.testboard.plyma_backend.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testboard.plyma_backend.domain.comment.domain.Comment;
import org.testboard.plyma_backend.domain.comment.domain.repository.CommentRepository;
import org.testboard.plyma_backend.domain.comment.presentation.dto.CommentRequest;
import org.testboard.plyma_backend.domain.comment.service.exception.CommentNotFound;
import org.testboard.plyma_backend.domain.post.domain.Post;
import org.testboard.plyma_backend.domain.post.domain.repository.PostRepository;
import org.testboard.plyma_backend.domain.post.presentation.dto.ReturnIdResponse;
import org.testboard.plyma_backend.domain.post.service.exception.PostNotFoundException;
import org.testboard.plyma_backend.domain.user.exception.UserNotMatchException;
import org.testboard.plyma_backend.domain.user.service.util.UserUtil;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserUtil userUtil;

    @Transactional
    public ReturnIdResponse create(CommentRequest request){
        Post post = postRepository.findById(request.getId())
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        Comment comment = commentRepository.save(Comment.builder()
                        .post(post)
                        .user(userUtil.getUser())
                        .content(request.getContent())
                .build());
        return new ReturnIdResponse(comment.getId());
    }

    @Transactional
    public ReturnIdResponse update(Long id, CommentRequest request){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> CommentNotFound.EXCEPTION);
        if(!comment.getUser().getUserId().equals(userUtil.getUserId())) throw UserNotMatchException.EXCEPTION;

        comment.update(request.getContent());
        return new ReturnIdResponse(comment.getId());
    }

    @Transactional
    public void delete(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> CommentNotFound.EXCEPTION);
        if(comment.getUser().getUserId().equals(userUtil.getUserId())) throw UserNotMatchException.EXCEPTION;

        commentRepository.delete(comment);
    }
}
