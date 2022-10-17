package com.example.dynamicdatasource;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "t_admin")
public class AdminUser extends User {

    private String address;

}
