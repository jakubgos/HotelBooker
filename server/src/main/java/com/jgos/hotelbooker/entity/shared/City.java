package com.jgos.hotelbooker.entity.shared;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by Bos on 2017-05-20.
 */
@Entity
public class City {

    @Id
    @Min(1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private long id;

    @Column
    @NotNull
    @Size(min = 3)
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
