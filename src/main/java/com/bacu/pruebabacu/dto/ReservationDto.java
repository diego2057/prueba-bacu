package com.bacu.pruebabacu.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;


public class ReservationDto {
    @NotNull
    private String id;

    @NotNull
    @Min(value = 1, message = "El numero a reservar debe ser mayor a 0")
    private int reservation;

    public ReservationDto(String id, int reservation) {
        this.id = id;
        this.reservation = reservation;
    }

    public ReservationDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getReservation() {
        return reservation;
    }

    public void setReservation(int reservation) {
        this.reservation = reservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDto that = (ReservationDto) o;
        return reservation == that.reservation && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservation);
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "id='" + id + '\'' +
                ", reservation=" + reservation +
                '}';
    }
}
