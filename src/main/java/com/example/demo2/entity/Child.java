package com.example.demo2.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Child {

    public enum  Sex {MALE, FEMALE}

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = false)
    private String secondName;

    @NotNull
    @Column(nullable = false)
    @Enumerated( EnumType.STRING )
    private Sex sex;

    private String pesel;

    @NotNull
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate birthDate;

    @NotNull
    @ManyToOne(targetEntity = Family.class, optional = false)
    private Family family;
}
