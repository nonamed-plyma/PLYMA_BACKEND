package org.testboard.plyma_backend.domain.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.testboard.plyma_backend.domain.user.domain.User;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String userId);
}
