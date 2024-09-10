package org.testboard.plyma_backend.domain.post.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.testboard.plyma_backend.domain.post.domain.Post;
import org.testboard.plyma_backend.domain.post.domain.repository.PostRepository;
import org.testboard.plyma_backend.domain.post.presentation.dto.PostListResponse;
import org.testboard.plyma_backend.domain.user.domain.repository.UserRepository;
import org.testboard.plyma_backend.domain.user.service.util.UserUtil;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostListService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserUtil userUtil;

    public PostListResponse getUserPostPage(Pageable pageable){
        Page<Post> posts = postRepository.findAllByUserOrderByIdDesc(userUtil.getUser(), pageable);

        return new PostListResponse(posts.stream().map(this::ofPostResponse).collect(Collectors.toList()), pageable.getPageNumber());
    }

    public PostListResponse findPost(String title, Pageable pageable){
        Page<Post> posts;

        posts = postRepository.findAllByTitleContainingAndOrderByIdDesc(title, pageable);

        return new PostListResponse(posts.stream().map(this::ofPostResponse).collect(Collectors.toList()), posts.getTotalPages());
    }

    private PostListResponse.PostResponse ofPostResponse(Post post){
        return PostListResponse.PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .userNickname(post.getUser().getName())
//                .state(post.getState())
                .crateDate(post.getCreateDate())
                .build();
    }
}
