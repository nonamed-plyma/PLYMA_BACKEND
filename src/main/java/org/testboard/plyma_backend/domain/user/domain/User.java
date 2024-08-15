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

    @Column(length = 18, nullable = false)
    private String password;

    @Column(length = 4, nullable = false)
    private String name;

    @Column(length = 4)
    private Integer studentNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Builder
    public User(String userId, String password, String name, Integer studentNumber, UserRole userRole){
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.studentNumber = studentNumber;
        this.userRole = userRole;
    }
}
