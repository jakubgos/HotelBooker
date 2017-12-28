package com.jgos.hotelbooker.entity.hotel;

import com.jgos.hotelbooker.entity.user.UserDb;

import javax.persistence.*;

@Entity
public class ImagePath {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    String path;

    @OneToOne
    private UserDb owner;

    public ImagePath() {
    }

    public ImagePath(String path, UserDb owner) {
        this.path = path;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "ImagePath{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", owner=" + owner +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public UserDb getOwner() {
        return owner;
    }

    public void setOwner(UserDb owner) {
        this.owner = owner;
    }
}
