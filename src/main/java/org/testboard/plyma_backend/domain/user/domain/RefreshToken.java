package org.testboard.plyma_backend.domain.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken {

    @Id
    private String userId;

    @Column(nullable = false)
    private String refreshToken;

    @Builder
    public RefreshToken(String userId, String refreshToken){
        this.userId = userId;
        this.refreshToken = refreshToken;
    }
}
