package org.testboard.plyma_backend.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.testboard.plyma_backend.global.jwt.exception.NotAccessTokenException;
import org.testboard.plyma_backend.global.jwt.exception.TokenErrorException;
import org.testboard.plyma_backend.global.jwt.exception.TokenUnauthorizedException;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@PropertySource("classpath:application.yml")
public class JwtTokenProvider {

    @Value("${JWT_AC}")
    private Long accessTokenTime;

    @Value("${JWT_RF}")//302400초
    private Long refreshTokenTime;

    private final UserDetailsService userDetailsService;

    private final Key key;

    public JwtTokenProvider(@Value("${JWT_KEY}") String secretKey, UserDetailsService userDetailsService){
        this.userDetailsService =userDetailsService;
        byte[] keyByte = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyByte);
    }

    public String generateAccessToken(String id){
        return Jwts.builder()
                .setHeaderParam("typ", "access")
                .setSubject(id)
                .signWith(SignatureAlgorithm.ES256, key)
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenTime * 1000))
                .compact();
    }

    public String generateRefreshToken(String id){
        return Jwts.builder()
                .setHeaderParam("typ", "refresh")
                .setSubject(id)
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenTime * 1000))
                .signWith(SignatureAlgorithm.ES256, key)
                .compact();
    }

    public Authentication getAuthentication(String tk){
        UserDetails userDetails = userDetailsService.loadUserByUsername(getSubject(tk));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getSubject(String tk){
        try{
            return getBody(tk).getSubject();
        } catch (Exception exception){
            throw TokenUnauthorizedException.EXCEPTION;
        }
    }

    public Claims getBody(String tk){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(tk).getBody();
    }

    public JwsHeader getHeader(String tk){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(tk).getHeader();
    }

    public void isRefreshToken(String tk){
        if(getHeader(tk).equals("refresh"))
            throw NotAccessTokenException.EXCEPTION;
    }

    public boolean validateToken(String tk){
        isRefreshToken(tk);

        try{
            Jwts.parser().setSigningKey(key).parseClaimsJws(tk);
            return true;
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException exception){
            log.error("잘못된 JWT서명");
            throw TokenErrorException.EXCEPTION;
        }catch (IllegalArgumentException exception){
            log.error("잘못된 JWT토큰");
            throw TokenErrorException.EXCEPTION;
        }catch (ExpiredJwtException exception){
            log.error("JWT토큰 만료");
            throw TokenUnauthorizedException.EXCEPTION;
        }catch (UnsupportedJwtException exception){
            log.error("지원하지 않는 토큰");
            throw TokenErrorException.EXCEPTION;
        }catch (Exception exception){
            throw TokenUnauthorizedException.EXCEPTION;
        }
    }
}
