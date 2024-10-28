package com.clientservice.clientservice.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public class Person {

    public Person(String name, String gen, Integer age, String identification, String address, String phone) {
        this.name = name;
        this.gen = gen;
        this.age = age;
        this.identification = identification;
        this.address = address;
        this.phone = phone;

    }
    public Person(Integer id) {
        this.id = id;
    }

    public Person() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    protected Integer id;
    protected String name;
    protected String gen;
    protected Integer age;
    protected String identification;
    protected String address;
    protected String phone;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getName(), person.getName()) && Objects.equals(getGen(), person.getGen()) && Objects.equals(getAge(), person.getAge()) && Objects.equals(getIdentification(), person.getIdentification()) && Objects.equals(getAddress(), person.getAddress()) && Objects.equals(getPhone(), person.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getGen(), getAge(), getIdentification(), getAddress(), getPhone());
    }
}
