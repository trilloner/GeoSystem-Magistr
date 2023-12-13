package com.geosystem.springbootbackend.models;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
@Table(name = "user", schema = "public")
public class User {
    private Long id;
    private String name;
    private String email;
    private Date date;
    private String password;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }


    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }


    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

}
