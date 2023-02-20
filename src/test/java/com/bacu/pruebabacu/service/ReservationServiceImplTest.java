package com.bacu.pruebabacu.service;

import com.bacu.pruebabacu.dto.ReservationDto;
import com.bacu.pruebabacu.model.Reservation;
import com.bacu.pruebabacu.repository.ReservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReservationServiceImplTest {

    @Autowired
    private ReservationService reservationService;

    @MockBean
    private ReservationRepository reservationRepository;

    private static final String CLIENT = "client1";
    @Test
    void showSuccess() {
        Reservation reservation = new Reservation(CLIENT, 1);
        when(reservationRepository.findById("client1"))
                .thenReturn(Optional.of(reservation));

        Assertions.assertEquals(reservation.getReservation(),
                reservationService.findById(CLIENT).getReservation());
    }

    @Test
    void showThrowResponseStatusException() {
        when(reservationRepository.findById("client1"))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class,
                () -> reservationService.findById(CLIENT),
                "ERROR - Cliente " + CLIENT + " aun no tiene reserva");
    }

    @Test
    void saveWhenSuccess() {
        ReservationDto reservationDto = new ReservationDto(CLIENT, 1);
        Reservation reservation = new Reservation(CLIENT, 1);

        when(reservationRepository.findById("client1"))
                .thenReturn(Optional.empty());
        when(reservationRepository.findReservationByReservation(1))
                .thenReturn(Optional.empty());
        when(reservationRepository.save(reservation))
                .thenReturn(reservation);

        Assertions.assertEquals(reservationDto.getReservation(),
                reservationService.save(reservationDto).getReservation());
    }

    @Test
    void saveWhenClientAlreadyHaveAReservation() {
        ReservationDto reservationDto = new ReservationDto(CLIENT, 1);
        Reservation reservation = new Reservation(CLIENT, 1);

        when(reservationRepository.findById("client1"))
                .thenReturn(Optional.of(reservation));

        Assertions.assertThrows(ResponseStatusException.class,
                () -> reservationService.save(reservationDto),
                "ERROR - " + reservationDto.getId() + " ya tiene reserva");
    }

    @Test
    void saveWhenReservationNumberIsAlreadyReserved() {
        ReservationDto reservationDto = new ReservationDto(CLIENT, 1);
        Reservation reservation = new Reservation(CLIENT, 1);

        when(reservationRepository.findReservationByReservation(1))
                .thenReturn(Optional.of(reservation));

        Assertions.assertThrows(ResponseStatusException.class,
                () -> reservationService.save(reservationDto),
                "ERROR - El número " + reservationDto.getReservation() + " ya esta reservado");
    }

    @Test
    void index() {
        List<Reservation> reservationDtoList = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            reservationDtoList.add(new Reservation(CLIENT, 1));
        }

        when(reservationRepository.findAll())
                .thenReturn(reservationDtoList);

        Assertions.assertEquals(reservationDtoList.size(),
                reservationService.index().size());
    }
}