package org.testboard.plyma_backend.domain.post.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.testboard.plyma_backend.domain.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByTitleContainingOrderByIdDesc(String title, Pageable pageable);


}
