package com.example.hotel_reservation_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hotel_reservation_system.model.Guest;
import com.example.hotel_reservation_system.model.Hotel;
import com.example.hotel_reservation_system.model.Reservation;
import com.example.hotel_reservation_system.model.ReservationResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmationFragment extends Fragment {

    private TextView reservationNumberTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.confirmation_fragment_layout, container, false);

        reservationNumberTextView = view.findViewById(R.id.reservationNumberTextView);

        // Extract the reservation data from arguments
        long hotelId = getArguments().getLong("hotelId");
        String checkInDate = getArguments().getString("checkInDate");
        String checkOutDate = getArguments().getString("checkOutDate");
        int numberOfRoomsBooked = (int) Math.ceil((getArguments().getInt("numberOfGuests")) / 2);
        ArrayList<Guest> guests = (ArrayList<Guest>) getArguments().getSerializable("guests");

        Hotel hotel = new Hotel(hotelId);

        // Build the reservation object
        Reservation reservation = new Reservation(hotel, checkInDate, checkOutDate, numberOfRoomsBooked, guests);

        // Send the reservation details to the backend
        sendReservationDetails(reservation);

        return view;
    }

    private void sendReservationDetails(Reservation reservation) {
        HotelApiService service = RetrofitClient.getService();

        service.createReservation(reservation).enqueue(new Callback<ReservationResponse>() {
            @Override
            public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    reservationNumberTextView.setText(String.valueOf(response.body().getReservationNumber()));
                } else {
                    Toast.makeText(getContext(), "Failed to make reservation.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ReservationResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
