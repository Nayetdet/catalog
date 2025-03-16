package io.github.nayetdet.catalog.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {

    private int status;
    private String message;
    private List<String> errors;

    public ExceptionResponse(int status, String message) {
        this(status, message, List.of());
    }

}
