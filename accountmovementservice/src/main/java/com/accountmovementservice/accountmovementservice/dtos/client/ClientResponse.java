package com.accountmovementservice.accountmovementservice.dtos.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientResponse {

    private Integer id;
    private String name;
    private String gen;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private String password;
    private String state;
}
