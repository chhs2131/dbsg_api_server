package kr.co.dbsg.api.api.auth.exception;

public class OauthClientException extends RuntimeException {
    public OauthClientException() {
    }

    public OauthClientException(String message) {
        super(message);
    }
}
