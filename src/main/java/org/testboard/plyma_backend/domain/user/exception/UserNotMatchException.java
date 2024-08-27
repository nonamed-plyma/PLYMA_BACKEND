package org.testboard.plyma_backend.domain.user.exception;

import org.testboard.plyma_backend.global.error.exception.BaseException;
import org.testboard.plyma_backend.global.error.exception.ErrorCode;

public class UserNotMatchException extends BaseException {
    public static final BaseException EXCEPTION = new UserNotMatchException();

    public UserNotMatchException(){
        super(ErrorCode.USER_NOT_MATCH);
    }
}
