package com.bacu.pruebabacu.repository;

import com.bacu.pruebabacu.model.Reservation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface ReservationRepository extends ReactiveMongoRepository<Reservation, String> {
    Flux<Reservation> findAll();
    Mono<Reservation> findByReservation(int reservation);
}
