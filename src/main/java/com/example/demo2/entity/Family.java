package com.example.demo2.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Family {

    @Id
    @GeneratedValue
    private Integer id;
}
