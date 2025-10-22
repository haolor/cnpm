package com.e_commerce.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomException extends RuntimeException {
    private final List<ErrorResponse> errors;
    private final String additionalDetail;
    private final HttpStatusCode status;

    public CustomException(List<ErrorResponse> errors) {
        super(errors.stream().map(ErrorResponse::getMessage).collect(Collectors.joining(", ")));
        this.errors = errors;
        this.additionalDetail = null;
        this.status = null;
    }

    public CustomException(ErrorResponse error) {
        super(error.getMessage());
        this.errors = List.of(error);
        this.additionalDetail = null;
        this.status = error.getStatusCode();
    }

    public CustomException(List<ErrorResponse> errors, String additionalDetail) {
        super(errors.stream()
                .map(e -> e.getMessage() + (additionalDetail != null ? " (" + additionalDetail + ")" : ""))
                .collect(Collectors.joining(", ")));
        this.errors = errors;
        this.additionalDetail = additionalDetail;
        this.status = null;
    }

    public List<String> getErrorMessages() {
        return errors.stream().map(ErrorResponse::getMessage).collect(Collectors.toList());
    }

    public List<String> getErrorMessagesWithId() {
        return errors.stream()
                .map(e -> e.getMessage() + (additionalDetail != null ? " (" + additionalDetail + ")" : ""))
                .collect(Collectors.toList());
    }

    public HttpStatus getStatusCode() {
        return errors != null && !errors.isEmpty()
                ? HttpStatus.valueOf(errors.get(0).getStatusCode().value())
                : HttpStatus.BAD_REQUEST;
    }

}
