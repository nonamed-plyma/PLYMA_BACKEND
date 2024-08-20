package org.testboard.plyma_backend.domain.post.service.exception;

import org.testboard.plyma_backend.global.error.exception.BaseException;
import org.testboard.plyma_backend.global.error.exception.ErrorCode;

public class PostNotFoundException extends BaseException {
    public static final BaseException EXCEPTION = new PostNotFoundException();

    public PostNotFoundException(){
        super(ErrorCode.POST_NOT_FOUND);
    }
}
