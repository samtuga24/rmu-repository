package com.samtuga.rmufilemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Unit {
    @SequenceGenerator(name = "unit_sequence", sequenceName = "unit_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "unit_sequence")
    @Id
    private Long id;
    private String name;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;
    public Unit(String name) {
        this.name = name;
    }
}
