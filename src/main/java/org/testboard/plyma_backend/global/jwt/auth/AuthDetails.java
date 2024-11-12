package org.testboard.plyma_backend.global.jwt.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.testboard.plyma_backend.domain.user.domain.User;

import java.util.Collection;

@RequiredArgsConstructor
public class AuthDetails implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){return null;}

    @Override
    public String getPassword(){return user.getPassword();}//password가져오기

    @Override
    public String getUsername(){return user.getName();}//Username가져오기

    @Override
    public boolean isAccountNonExpired(){return true;}//계정 비활성화 되어있지 않는가?

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }//계정이 잠겨있지 않은가?

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }//자격이 만료되었는가

    @Override
    public boolean isEnabled() {
        return true;
    }//활성화 되어있는가?
}
