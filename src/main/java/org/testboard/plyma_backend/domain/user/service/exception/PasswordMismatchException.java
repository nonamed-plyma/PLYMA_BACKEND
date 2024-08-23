package org.testboard.plyma_backend.domain.user.service.exception;

import org.testboard.plyma_backend.global.error.exception.BaseException;
import org.testboard.plyma_backend.global.error.exception.ErrorCode;

public class PasswordMismatchException extends BaseException {
    public static final BaseException EXCEPTION = new PasswordMismatchException();

    public PasswordMismatchException(){
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
