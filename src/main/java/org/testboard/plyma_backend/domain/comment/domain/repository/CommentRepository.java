package org.testboard.plyma_backend.domain.comment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.testboard.plyma_backend.domain.comment.domain.Comment;
import org.testboard.plyma_backend.domain.post.domain.Post;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Comment> findByPostOrderByIdDesc(Post post);
}
