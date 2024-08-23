package org.testboard.plyma_backend.domain.user.service.exception;

import org.testboard.plyma_backend.global.error.exception.BaseException;
import org.testboard.plyma_backend.global.error.exception.ErrorCode;

public class UserIdAlreadyExistException extends BaseException {
    public static final BaseException EXCEPTION = new UserIdAlreadyExistException();

    public UserIdAlreadyExistException(){
        super(ErrorCode.USER_ID_ALREADY_EXIST);
    }
}
