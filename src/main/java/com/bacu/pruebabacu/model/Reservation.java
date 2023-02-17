package com.bacu.pruebabacu.model;


import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;


@Document
public class Reservation {

	@Id
	@NotNull
	private String id;

	@Field("reservation")
	@NotNull
	private int reservation;

	public Reservation(String id, int reservation) {
		this.id = id;
		this.reservation = reservation;
	}

	public Reservation() {
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

		Reservation that = (Reservation) o;

		if (reservation != that.reservation) return false;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + reservation;
		return result;
	}

	@Override
	public String toString() {
		return "Reservation{" +
				"id='" + id + '\'' +
				", reservation=" + reservation +
				'}';
	}
}