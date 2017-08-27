package com.jgos.hotelbooker.entity.user;

/**
 * Created by Bos on 2017-07-25.
 */


public enum ReservationStatus {
    WAIT_FOR_CONFIRMATION("Oczekuje na weryfikacje"),
    WAIT_FOR_PAYMENT("Oczekuje na wpłatę"),
    RESERVATION_DONE("Zarezerwowane"),
    RESERVATION_REJECTED("Odrzucono przez hotel"),
    RESERVATION_FINISHED("Zrealizowano"),
    UNKNOWN("unknown");

    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    ReservationStatus(final String text) {
        this.text = text;
    }

    ReservationStatus() {
    }
    public static ReservationStatus getEnumByString(String code){
        for(ReservationStatus e : ReservationStatus.values()){
            if(code.equals(e.text)) return e;
        }
        return null;
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

