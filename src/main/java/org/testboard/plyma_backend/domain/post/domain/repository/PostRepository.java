package org.testboard.plyma_backend.domain.post.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.testboard.plyma_backend.domain.post.domain.Post;
import org.testboard.plyma_backend.domain.user.domain.User;

public interface PostRepository extends CrudRepository<Post, Long> {
//    Page<Post> findAllByTitleContainingOrderByIdDesc(String title, Pageable pageable);
    Page<Post> findAllByTitleContainingAndOrderByIdDesc(String title, Pageable pageable);
    Page<Post> findAllByUserOrderByIdDesc(User user, Pageable pageable);

}
