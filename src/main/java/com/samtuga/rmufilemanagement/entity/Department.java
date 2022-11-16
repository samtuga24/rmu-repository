package com.samtuga.rmufilemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Department {
    @SequenceGenerator(name = "department_sequence", sequenceName = "department_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "department_sequence")
    @Id
    private Long id;
    private String name;
    @JsonIgnore
    @OneToMany
    private Set<FileDiary> diaries = new HashSet<>();

    public Department(String name) {
        this.name = name;
    }
}
