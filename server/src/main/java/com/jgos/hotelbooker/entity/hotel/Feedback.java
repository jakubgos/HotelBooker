package com.jgos.hotelbooker.entity.hotel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Size(min = 1, max = 10)
    private int value;

    Boolean isValid;


    public Feedback() {
    }

    public Feedback(int value, Boolean isValid) {
        this.value = value;
        this.isValid = isValid;
    }

    public Feedback(Boolean isValid) {
        this.isValid = isValid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", value=" + value +
                ", isValid=" + isValid +
                '}';
    }
}
