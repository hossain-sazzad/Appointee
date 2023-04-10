package com.appointee.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "my_user", uniqueConstraints = @UniqueConstraint(name="uk_username", columnNames = {"username"}))
public class User extends BaseEntity<Long>{
    private String username;
    private String name;
}
