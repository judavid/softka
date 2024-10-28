package com.accountmovementservice.accountmovementservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private  boolean response;
    private String message;

    public Response(boolean response, String message) {
        this.response = response;
        this.message = message;
    }

}
