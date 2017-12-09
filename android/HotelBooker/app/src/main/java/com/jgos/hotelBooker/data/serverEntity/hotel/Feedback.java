package com.jgos.hotelBooker.data.serverEntity.hotel;


public class Feedback {


    private long id;

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
