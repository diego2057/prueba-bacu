package com.bacu.pruebabacu.service;

import com.bacu.pruebabacu.dto.ReservationDto;
import com.bacu.pruebabacu.model.Reservation;

import java.util.List;

public interface ReservationService {
    ReservationDto findById(String id);

    ReservationDto save(ReservationDto reservation);

    List<ReservationDto> index();
}
