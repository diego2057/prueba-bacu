package com.bacu.pruebabacu.service;

import com.bacu.pruebabacu.dto.ReservationDto;
import com.bacu.pruebabacu.dto.ReservationMapper;
import com.bacu.pruebabacu.model.Reservation;
import com.bacu.pruebabacu.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;
    private final ReservationMapper mapper;

    public ReservationServiceImpl(ReservationRepository repository, ReservationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public ReservationDto findById(String id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                                "ERROR - Cliente " + id + " aun no tiene reserva")
                )
        );
    }

    @Override
    public ReservationDto save(ReservationDto reservationDto) {
        Optional<Reservation> reservationById = repository.findById(reservationDto.getId());
        if(reservationById.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "ERROR - " + reservationDto.getId() + " ya tiene reserva");
        }

        Optional<Reservation> reservationByNumber =
                repository.findReservationByReservation(reservationDto.getReservation());
        if(reservationByNumber.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "ERROR - El número " + reservationDto.getReservation() + " ya esta reservado");
    }
        return mapper.toDto(repository.save(mapper.toModel(reservationDto)));

    }

    @Override
    public List<ReservationDto> index() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
