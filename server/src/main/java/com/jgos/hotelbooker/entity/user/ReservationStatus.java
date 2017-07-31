package com.jgos.hotelbooker.entity.user;

/**
 * Created by Bos on 2017-07-25.
 */


public enum ReservationStatus {
    WAIT_FOR_CONFIRMATION("Oczekuje na weryfikacje"),
    WAIT_FOR_PAYMENT("Oczekuje na wpłatę"),
    RESERVATION_DONE("Zarezerwowane"),

    UNKNOWN("unknown");

    private final String text;

    private ReservationStatus(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        if (text == null) {
            return "error";
        }
        return text;
    }
}

