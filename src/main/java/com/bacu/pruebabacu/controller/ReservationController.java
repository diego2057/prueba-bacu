package com.bacu.pruebabacu.controller;

import com.bacu.pruebabacu.dto.ReservationDto;
import com.bacu.pruebabacu.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping()
    public ResponseEntity<ReservationDto> newReservation(@Valid @RequestBody ReservationDto reservation) {
        return ResponseEntity.ok().body(reservationService.save(reservation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> show(@PathVariable String id) {
        return ResponseEntity.ok().body(reservationService.findById(id));
    }

    @GetMapping
    public List<ReservationDto> index() {
        return reservationService.index();
    }
}
