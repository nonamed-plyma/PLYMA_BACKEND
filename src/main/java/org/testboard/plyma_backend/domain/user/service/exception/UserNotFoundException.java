package org.testboard.plyma_backend.domain.user.service.exception;

import org.testboard.plyma_backend.global.error.exception.BaseException;
import org.testboard.plyma_backend.global.error.exception.ErrorCode;

public class UserNotFoundException extends BaseException {
    public static final BaseException EXCEPTION = new UserNotFoundException();

    public UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}
