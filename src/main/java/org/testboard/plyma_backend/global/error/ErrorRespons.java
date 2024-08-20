package org.testboard.plyma_backend.global.error;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorRespons {
    private final Integer statusCode;
    private final String errorMessage;
}
