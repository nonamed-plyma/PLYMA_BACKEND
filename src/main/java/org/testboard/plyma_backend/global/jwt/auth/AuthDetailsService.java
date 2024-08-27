//package org.testboard.plyma_backend.global.jwt.auth;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//import org.testboard.plyma_backend.domain.user.domain.repository.UserRepository;
//import org.testboard.plyma_backend.domain.user.exception.UserNotFoundException;
//
//@Service
//@RequiredArgsConstructor
//public class AuthDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String userId){
//        return userRepository.findByUserId(userId)
//                .map(AuthDetails::new)
//                .orElseThrow(()-> UserNotFoundException.EXCEPTION);
//    }
//}
