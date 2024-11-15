package org.testboard.plyma_backend.domain.post.presentation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.testboard.plyma_backend.domain.post.presentation.dto.PostListResponse;
import org.testboard.plyma_backend.domain.post.presentation.dto.PostRequest;
import org.testboard.plyma_backend.domain.post.presentation.dto.PostResponse;
import org.testboard.plyma_backend.domain.post.presentation.dto.ReturnIdResponse;
import org.testboard.plyma_backend.domain.post.service.PostCrudService;
import org.testboard.plyma_backend.domain.post.service.PostDetailsService;
import org.testboard.plyma_backend.domain.post.service.PostListService;


@RequestMapping("/post")
@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostCrudService postService;
    private final PostDetailsService postDetailsService;
    private final PostListService postListService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReturnIdResponse create(@RequestBody @Valid PostRequest request) {return postService.create(request);}

    @PatchMapping("/{id}")
    public Long update(@PathVariable @NotNull Long id, @RequestBody @Valid PostRequest request){return postService.update(id,request);}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull Long id) {postService.delete(id);}

    @GetMapping("/{id}")
    public PostResponse getPostDetails(@PathVariable @NotNull Long id){return postDetailsService.getPostDetails(id);}

    @GetMapping("/search")
    public PostListResponse findPost(@RequestParam(value = "title") String title, Pageable pageable){ return postListService.findPost(title,pageable);}

    @GetMapping("/user")
    public PostListResponse getUserPostsPaged(Pageable pageable){return postListService.getUserPostPage(pageable);}
}
