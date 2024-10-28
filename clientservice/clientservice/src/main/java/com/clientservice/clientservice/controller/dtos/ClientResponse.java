package com.clientservice.clientservice.controller.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public ClientResponse(Integer id, String name, String gen, Integer age, String identification, String address, String phone, String password, String state) {
        this.id = id;
        this.name = name;
        this.gen = gen;
        this.age = age;
        this.identification = identification;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.state = state;
    }

}
