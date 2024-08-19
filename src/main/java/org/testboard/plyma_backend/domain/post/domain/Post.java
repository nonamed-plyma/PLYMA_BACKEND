package org.testboard.plyma_backend.domain.post.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.testboard.plyma_backend.domain.comment.domain.Comment;
import org.testboard.plyma_backend.domain.user.domain.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String content;

    @Column(nullable = false)
    private String createDate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    //뭔가 하긴 했는데 이게 맞는지 잘 모르겠슴다
    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    @Builder
    public Post(User user, Long id, String title, String content){
        this.user = user;
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.mm.dd"));
    }
}