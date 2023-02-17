package com.bacu.pruebabacu.controller;

import com.bacu.pruebabacu.dto.ReservationDto;
import com.bacu.pruebabacu.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private ReservationService reservationService;

    @Test()
    public void testGetReservationById() {
        ReservationDto reservation = new ReservationDto("client1", 1);

        when(reservationService.findById("client1")).thenReturn(reservation);

        webTestClient.get()
                .uri("/v1/reservations/client1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ReservationDto.class)
                .value(reservationDto -> reservation.getReservation(), equalTo(1));
    }

}