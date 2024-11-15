package org.testboard.plyma_backend.domain.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.testboard.plyma_backend.domain.user.domain.role.UserRole;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @Column(length = 10, nullable = false, unique = true)
    private String userId;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 4, nullable = false)
    private Integer classNum;

    @Column(length = 4, nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Builder
    public User(String userId, String password, String name, UserRole userRole, Integer classNum){
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.userRole = userRole;
        this.classNum = classNum;
    }
}
