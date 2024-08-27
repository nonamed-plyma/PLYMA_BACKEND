package org.testboard.plyma_backend.domain.user.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.testboard.plyma_backend.domain.user.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByUserId(String userId);


}
