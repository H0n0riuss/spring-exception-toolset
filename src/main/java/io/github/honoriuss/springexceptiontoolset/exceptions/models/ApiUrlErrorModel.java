package io.github.honoriuss.springexceptiontoolset.exceptions.models;

public class ApiUrlErrorModel extends ApiErrorModel {
    public String url;
    public ApiUrlErrorModel(String message, String url) {
        super(message);
        this.url = url;
    }
}
