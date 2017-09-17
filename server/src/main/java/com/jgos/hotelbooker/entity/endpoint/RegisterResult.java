package com.jgos.hotelbooker.entity.endpoint;

/**
 * Created by Bos on 2017-09-17.
 */
public enum RegisterResult {
    USEREXIST(0), OK(1);

    private final double id;

    RegisterResult(int id) {
        this.id = id;
    }
}
