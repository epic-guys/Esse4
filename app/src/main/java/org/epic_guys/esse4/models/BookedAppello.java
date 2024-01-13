package org.epic_guys.esse4.models;

import androidx.annotation.NonNull;

public class BookedAppello {
    private final String name;
    private final String reservationDate;
    private final String peso;


    public BookedAppello(String name, String reservationDate, String peso) {
        this.name = name;
        this.reservationDate = reservationDate;
        this.peso = peso;
    }

    public String getName() {
        return name;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public String getPeso() {
        return peso;
    }

    @NonNull
    @Override
    public String toString() {
        return "BookedAppello{" +
                "name='" + name + '\'' +
                ", reservationDate='" + reservationDate + '\'' +
                ", peso='" + peso + '\'' +
                '}';
    }
}
