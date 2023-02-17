package com.bacu.pruebabacu.dto;

import com.bacu.pruebabacu.model.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationDto toDto(Reservation source);
    Reservation toModel(ReservationDto destination);
}
