package org.testboard.plyma_backend.domain.post.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.testboard.plyma_backend.domain.post.domain.Post;
import org.testboard.plyma_backend.domain.post.domain.repository.PostRepository;
import org.testboard.plyma_backend.domain.post.presentation.dto.PostRequest;
import org.testboard.plyma_backend.domain.post.presentation.dto.ReturnIdResponse;
import org.testboard.plyma_backend.domain.post.service.exception.PostNotFoundException;
import org.testboard.plyma_backend.domain.user.exception.UserNotMatchException;
import org.testboard.plyma_backend.domain.user.service.util.UserUtil;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserUtil userUtil;

    @Transactional
    public ReturnIdResponse create(PostRequest request){
        Post post = postRepository.save(Post.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .build());

        return new ReturnIdResponse(post.getId());
    }

    @Transactional
    public Long update(Long id, PostRequest request){
        Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);
        if(!post.getUser().getUserId().equals(userUtil.getUserId())) throw UserNotMatchException.EXCEPTION;

        return post.update(request.getTitle(), request.getContent());
    }

    @Transactional
    public void delate(Long id){
        Post
    }
}
