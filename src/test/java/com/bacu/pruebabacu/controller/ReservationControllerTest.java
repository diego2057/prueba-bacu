package com.bacu.pruebabacu.controller;

import com.bacu.pruebabacu.dto.ReservationDto;
import com.bacu.pruebabacu.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.server.ResponseStatusException;
import java.util.Collections;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String CLIENT = "client1";

    @Test
    public void testGetReservationByIdSuccess() throws Exception {
        ReservationDto reservation = new ReservationDto(CLIENT, 1);

        when(reservationService.findById("client1")).thenReturn(reservation);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/reservations/client1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservation", equalTo(1)));
    }

    @Test
    public void testGetReservationByIdNotFound() throws Exception {
        when(reservationService.findById("client1")).thenThrow(
                new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                        "ERROR - Cliente " + CLIENT + " aun no tiene reserva")
        );

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/reservations/client1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testGetAllReservationSuccess() throws Exception {
        ReservationDto reservation = new ReservationDto(CLIENT, 1);
        when(reservationService.index()).thenReturn(Collections.singletonList(reservation));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/reservations"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].reservation", equalTo(1)));
    }

    @Test
    public void testCreateReservationSuccess() throws Exception {
        ReservationDto reservation = new ReservationDto(CLIENT, 1);
        when(reservationService.save(reservation)).thenReturn(reservation);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/reservations")
                        .content(objectMapper.writeValueAsString(reservation))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservation", equalTo(1)));
    }
}