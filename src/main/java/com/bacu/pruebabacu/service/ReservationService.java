package com.bacu.pruebabacu.service;

import com.bacu.pruebabacu.dto.ReservationDto;
import com.bacu.pruebabacu.model.Reservation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReservationService {
    Mono<Reservation> findById(String id);

    Mono<Object> save(ReservationDto reservation);

    Flux<ReservationDto> index();
}
