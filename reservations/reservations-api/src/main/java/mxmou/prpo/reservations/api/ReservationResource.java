package mxmou.prpo.reservations.api;

import io.vertx.core.http.HttpServerResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import mxmou.prpo.reservations.api.model.Reservation;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservationResource implements ReservationApi {
    @Context
    HttpServerResponse response;

    @Context
    UriInfo uriInfo;

    final String exampleId = "123e4567-e89b-12d3-a456-426614174000";

    @Override
    public List<Reservation> getAllReservations(Integer room) {
        List<Reservation> result = new ArrayList<>();
        if (room == null || room.equals(123)) {
            result.add(this.getReservation(exampleId));
        }
        return result;
    }

    @Override
    public Reservation createReservation(@Valid @NotNull Reservation reservation) {
        String id = UUID.randomUUID().toString();
        reservation.setId(id);
        response.setStatusCode(201);
        response.putHeader("Location", uriInfo.getAbsolutePathBuilder().path(id).build().toString());
        return reservation;
    }

    @Override
    public Reservation getReservation(String id) {
        if (id.equals(exampleId)) {
            return new Reservation()
                    .id(exampleId)
                    .userId(exampleId)
                    .room(123)
                    .dateFrom(OffsetDateTime.of(2026, 11, 1,
                            17, 0, 0, 0, ZoneOffset.UTC))
                    .dateTo(OffsetDateTime.of(2026, 11, 1,
                            18, 30, 0, 0, ZoneOffset.UTC));
        }
        throw new NotFoundException("Unknown reservation: " + id);
    }

    @Override
    public Reservation updateReservation(String id, @Valid @NotNull Reservation reservation) {
        if (id.equals(exampleId)) {
            reservation.setId(id);
            return reservation;
        }
        throw new NotFoundException("Unknown reservation: " + id);
    }

    @Override
    public void deleteReservation(String id) {
        if (!id.equals(exampleId)) {
            throw new NotFoundException("Unknown reservation: " + id);
        }
    }
}
