package com.jgos.hotelbooker.entity.hotel.data;

import javax.persistence.*;

/**
 * Created by Bos on 2017-06-17.
 */
@Entity
public class FoodOffer {
    //none,breakfast,lunch,dinner

    //Dining,Vending,Exercise,Recreation,SwimmingPool,Parking
    @Id
    @GeneratedValue( strategy = GenerationType.TABLE )
    private long id;

    @Column(nullable = false)
    private String name;

    public FoodOffer() {
    }

    @Override
    public String toString() {
        return "FoodOffer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
