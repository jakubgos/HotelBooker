package com.jgos.hotelbooker.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by Bos on 2017-05-20.
 */
@Entity
public class City {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private long id;
    @Column
    private String name;

    public City() {

    }

    public City(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
