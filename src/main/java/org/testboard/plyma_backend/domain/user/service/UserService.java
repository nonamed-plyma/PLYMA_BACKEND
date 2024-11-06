package org.testboard.plyma_backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.testboard.plyma_backend.domain.post.domain.repository.PostRepository;
import org.testboard.plyma_backend.domain.user.domain.User;
import org.testboard.plyma_backend.domain.user.domain.repository.UserRepository;
import org.testboard.plyma_backend.domain.user.exception.UserIdAlreadyExistException;
import org.testboard.plyma_backend.domain.user.presentation.dto.UserDetailResponse;
import org.testboard.plyma_backend.domain.user.service.util.UserUtil;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserUtil util;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public UserDetailResponse getUser(){
        User user = util.getUser();

        return UserDetailResponse.builder()
                .userId(user.getUserId())
                .nickname(user.getName())
                .totalPosts(postRepository.countByUser(user))
                .build();
    }

    public void existsUserId(String userId){
        if(userRepository.existsByUserId(userId)) throw UserIdAlreadyExistException.EXCEPTION;
    }
}
