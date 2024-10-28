package com.clientservice.clientservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "client")
@Getter
@Setter
public class Client extends Person {

    public Client(String name, String gen, Integer age, String identification, String address, String phone, String password, String state) {
        super(name, gen, age, identification, address, phone);
        this.password = password;
        this.state = state;
    }

    public Client(Integer id) {
        super(id);
    }

    public Client() {
        super();
    }

    //    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE)
//    private Integer clientId;
    private String password;
    private String state;





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        if (!super.equals(o)) return false;
        return //Objects.equals(getClientId(), client.getClientId()) &&
                Objects.equals(getPassword(), client.getPassword()) && Objects.equals(getState(), client.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),
                //getClientId(),
                getPassword(), getState());
    }
}
