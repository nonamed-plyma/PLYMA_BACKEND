package org.testboard.plyma_backend.domain.comment.presentation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.testboard.plyma_backend.domain.comment.presentation.dto.CommentRequest;
import org.testboard.plyma_backend.domain.comment.service.CommentService;
import org.testboard.plyma_backend.domain.post.presentation.dto.ReturnIdResponse;

@RequestMapping("/comment")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReturnIdResponse create(@RequestBody @Valid CommentRequest request){return commentService.create(request);}

    @PatchMapping("/{id}")
    public ReturnIdResponse update(@PathVariable @NotNull Long id, @RequestBody @Valid CommentRequest request){return commentService.update(id, request);}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull Long id){commentService.delete(id);}
}
