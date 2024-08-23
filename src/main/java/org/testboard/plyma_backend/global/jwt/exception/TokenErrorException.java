package org.testboard.plyma_backend.global.jwt.exception;

import org.testboard.plyma_backend.global.error.exception.BaseException;
import org.testboard.plyma_backend.global.error.exception.ErrorCode;
;
public class TokenErrorException extends BaseException {
    public static TokenErrorException EXCEPTION = new TokenErrorException();

    public TokenErrorException(){
        super(ErrorCode.TOKEN_ERROR);
    }
}
