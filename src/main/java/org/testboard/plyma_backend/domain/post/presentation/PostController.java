package org.testboard.plyma_backend.domain.post.presentation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.testboard.plyma_backend.domain.post.presentation.dto.PostRequest;
import org.testboard.plyma_backend.domain.post.presentation.dto.ReturnIdResponse;
import org.testboard.plyma_backend.domain.post.service.PostCrudService;

@RequestMapping("/post")
@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostCrudService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReturnIdResponse create(@RequestBody @Valid PostRequest request) {return postService.create(request);}

    @PatchMapping("/{id}")
    public Long update(@PathVariable @NotNull Long id, @RequestBody @Valid PostRequest request){return postService.update(id,request);}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull Long id) {postService.delete(id);}


}
