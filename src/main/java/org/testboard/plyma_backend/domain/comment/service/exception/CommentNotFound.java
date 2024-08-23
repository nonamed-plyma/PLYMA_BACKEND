package org.testboard.plyma_backend.domain.comment.service.exception;

import org.testboard.plyma_backend.domain.comment.domain.Comment;
import org.testboard.plyma_backend.global.error.exception.BaseException;
import org.testboard.plyma_backend.global.error.exception.ErrorCode;

public class CommentNotFound extends BaseException {
    public static final BaseException EXCEPTION = new CommentNotFound();

    public CommentNotFound(){
        super(ErrorCode.COMMENT_NOT_FOUND);
    }
}
