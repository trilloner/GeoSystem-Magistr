package com.geosystem.springbootbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;



@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
@Table(name = "location", schema = "public")
public class Location {
    private Long id;
    private String name;
    private String description;
    private String city;
    private Date date;
    private Field fieldByFieldId;
    private User userByUserId;

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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }


    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }


    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    @ManyToOne
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    @JsonIgnore
    public Field getFieldByFieldId() {
        return fieldByFieldId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUserByUserId() {
        return userByUserId;
    }

}
