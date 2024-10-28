package com.clientservice.clientservice.controller.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientBody {

    private String name;
    private String gen;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private String password;
    private String state;

    public ClientBody(String name, String gen, Integer age, String identification, String address, String phone, String password, String state) {
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
