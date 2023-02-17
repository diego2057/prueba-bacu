package com.bacu.pruebabacu.controller;

import com.bacu.pruebabacu.dto.ReservationDto;
import com.bacu.pruebabacu.dto.ReservationMapper;
import com.bacu.pruebabacu.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    private final ReservationMapper mapper;

    public ReservationController(ReservationService reservationService, ReservationMapper mapper) {
        this.reservationService = reservationService;
        this.mapper = mapper;
    }

    @PostMapping()
    public Mono<Object> newReservation(@Valid @RequestBody ReservationDto reservation) {
        try {
            return reservationService.save(reservation);
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ReservationDto>> show(@PathVariable String id) {
        return reservationService.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map(it -> ResponseEntity.ok().body(mapper.toDto(it)));
    }

    @GetMapping
    public Flux<ReservationDto> index() {
        return reservationService.index();
    }
}
