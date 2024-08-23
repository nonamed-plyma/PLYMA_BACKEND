package org.testboard.plyma_backend.global.jwt.exception;

import org.testboard.plyma_backend.global.error.exception.BaseException;
import org.testboard.plyma_backend.global.error.exception.ErrorCode;

public class TokenUnauthorizedException extends BaseException {
    public static TokenUnauthorizedException EXCEPTION = new TokenUnauthorizedException();

    protected TokenUnauthorizedException(){
        super(ErrorCode.TOKEN_UNAUTHORIZED);
    }
}
