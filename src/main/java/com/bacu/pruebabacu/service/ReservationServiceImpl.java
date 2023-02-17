package com.bacu.pruebabacu.service;

import com.bacu.pruebabacu.dto.ReservationDto;
import com.bacu.pruebabacu.dto.ReservationMapper;
import com.bacu.pruebabacu.model.Reservation;
import com.bacu.pruebabacu.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;
    private final ReservationMapper mapper;

    public ReservationServiceImpl(ReservationRepository repository, ReservationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public Mono<Reservation> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Object> save(ReservationDto reservationDto) {
        Mono<Reservation> reservationByNumber = repository.findByReservation(reservationDto.getReservation());
        Mono<Reservation> reservationById = repository.findById(reservationDto.getId());

        AtomicReference<Reservation> byNumber = new AtomicReference<>();
        AtomicReference<Reservation> byId = new AtomicReference<>();
        reservationById.subscribe(reservation -> {
            if(reservation != null){
                byId.set(reservation);
            }
        });
        reservationByNumber.subscribe(reservation -> {
            if(reservation != null){
                byNumber.set(reservation);
            }
        });
        if(byId.get() != null){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "ERROR - " + reservationDto.getId() + " ya tiene reserva");
        }
        if(byNumber.get() != null){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "ERROR - El número " + reservationDto.getReservation() + " ya esta reservado");
    }
        return repository.save(mapper.toModel(reservationDto)).then(Mono.just(reservationDto));

    }

    @Override
    public Flux<ReservationDto> index() {
        return repository.findAll().map(mapper::toDto);
    }
}
