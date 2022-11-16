package com.samtuga.rmufilemanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Address {
    @SequenceGenerator(name = "add_sequence", sequenceName = "add_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "add_sequence")
    @Id
    private Long id;
    private String address;

    public Address(String address) {
        this.address = address;
    }
}
