package com.example.hotel_reservation_system;

import com.example.hotel_reservation_system.model.Hotel;
import com.example.hotel_reservation_system.model.Reservation;
import com.example.hotel_reservation_system.model.ReservationResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HotelApiService {
    @GET("api/hotels")
    Call<List<Hotel>> getHotels(
            @Query("checkin") String checkInDate,
            @Query("checkout") String checkOutDate,
            @Query("numberOfGuests") int numberOfGuests
    );

    @POST("api/reservations")
    Call<ReservationResponse> createReservation(@Body Reservation reservation);
}

