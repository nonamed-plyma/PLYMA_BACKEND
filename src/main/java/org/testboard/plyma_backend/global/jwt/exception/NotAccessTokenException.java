package org.testboard.plyma_backend.global.jwt.exception;

import org.testboard.plyma_backend.global.error.exception.BaseException;
import org.testboard.plyma_backend.global.error.exception.ErrorCode;

public class NotAccessTokenException extends BaseException {
    public static NotAccessTokenException EXCEPTION = new NotAccessTokenException();

    protected NotAccessTokenException(){
        super(ErrorCode.NOT_ACCESS_TOKEN);
    }
}
