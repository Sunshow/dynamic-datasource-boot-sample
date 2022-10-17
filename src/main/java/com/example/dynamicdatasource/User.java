package com.example.dynamicdatasource;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "t_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

}
