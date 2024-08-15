package org.testboard.plyma_backend.domain.user.domain.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    student("학생"), teacher("선생님");

    private final String userRole;
}
