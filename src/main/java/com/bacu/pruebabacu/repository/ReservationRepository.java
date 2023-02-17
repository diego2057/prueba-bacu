package com.bacu.pruebabacu.repository;

import com.bacu.pruebabacu.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

    Optional<Reservation> findReservationByReservation(int reservation);
}
