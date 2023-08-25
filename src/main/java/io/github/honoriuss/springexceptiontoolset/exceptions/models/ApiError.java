package io.github.honoriuss.springexceptiontoolset.exceptions.models;

public class ApiError {
    public String uri;
    public String message;

    public ApiError(String uri, String message) {
        this.uri = uri;
        this.message = message;
    }
}
