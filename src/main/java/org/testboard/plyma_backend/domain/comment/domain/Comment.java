package org.testboard.plyma_backend.domain.comment.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.testboard.plyma_backend.domain.post.domain.Post;
import org.testboard.plyma_backend.domain.user.domain.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post postId;

    @Builder
    public Comment(User user, Post post, String content){
        this.userId = user;
        this.postId = post;
        this.content = content;
    }
}
