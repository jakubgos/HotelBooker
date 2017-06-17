package com.jgos.hotelbooker.entity.shared;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * Created by Bos on 2017-06-17.
 */
@Entity
public class Rating {

    @Id
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long id;

    @Size(min = 1,max = 5)
    private int value;

    public Rating() {
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
