package org.testboard.plyma_backend.global.jwt.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.testboard.plyma_backend.domain.user.domain.User;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class AuthDetails implements UserDetails {
    private final org.testboard.plyma_backend.domain.user.domain.User user; // 도메인 User 사용

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자 권한을 반환하는 로직 추가
        return Collections.emptyList(); // 현재는 빈 리스트 반환
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // 도메인 User에서 패스워드 가져오기
    }

    @Override
    public String getUsername() {
        return user.getName(); // 도메인 User에서 Username 가져오기
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정 비활성화 되어있지 않음
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정이 잠겨있지 않음
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 자격이 만료되지 않음
    }

    @Override
    public boolean isEnabled() {
        return true; // 활성화 되어있음
    }
}
