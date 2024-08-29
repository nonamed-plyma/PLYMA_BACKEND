package org.testboard.plyma_backend.domain.user.exception;

import org.testboard.plyma_backend.global.error.exception.BaseException;
import org.testboard.plyma_backend.global.error.exception.ErrorCode;

public class RefreshTokenNotFound extends BaseException {
    public static final BaseException EXCEPTION = new RefreshTokenNotFound();

    public RefreshTokenNotFound(){super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);}
}
