package org.testboard.plyma_backend.global.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest reques, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = resolveToken(reques);

        if(token != null && jwtTokenProvider.validateToken(token)){
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(reques, response);
    }

    private String resolveToken(HttpServletRequest request){
        String bearnerToken = request.getHeader(HEADER);

        if (bearnerToken != null && bearnerToken.startsWith(PREFIX)) return bearnerToken.substring(7);
        return null;
    }
}