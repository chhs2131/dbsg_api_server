package kr.co.dbsg.api.global.jwt;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthenticationException extends RuntimeException {
    AuthenticationException(String message) {
        super(message);
    }

    AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
