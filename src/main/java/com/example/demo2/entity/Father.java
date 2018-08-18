package com.example.demo2.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Father {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    private String pesel;

    @NotNull
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate birthDate;

    @NotNull
    @Column(nullable = false)
    private String secondName;

    @NotNull
    @OneToOne(optional = false)
    private Family family;
}
