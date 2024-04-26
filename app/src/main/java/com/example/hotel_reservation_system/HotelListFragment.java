package com.example.hotel_reservation_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel_reservation_system.model.Hotel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

public class HotelListFragment extends Fragment implements ItemClickListener {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private HotelAdapter adapter;
    private TextView headingTextView;
    private String checkInDate, checkOutDate, guestName;
    private int numberOfGuests;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.hotel_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        headingTextView = view.findViewById(R.id.textViewHeading);
        setupUIComponents(view);

        retrieveArguments();

        headingTextView.setText("Welcome "+ guestName +" , displaying hotel for " + numberOfGuests + " guests staying from " + checkInDate +
                " to " + checkOutDate);

        getHotelsListsData();
    }

    private void setupUIComponents(View view) {
        recyclerView = view.findViewById(R.id.recyclerViewHotels);
        progressBar = view.findViewById(R.id.progressBarLoading);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void retrieveArguments() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            checkInDate = bundle.getString("checkInDate");
            checkOutDate = bundle.getString("checkOutDate");
            try {
                numberOfGuests = Integer.parseInt(bundle.getString("numberOfGuests"));
            } catch (NumberFormatException e) {
                numberOfGuests = 1;
            }
            guestName = bundle.getString("guestName");

            checkInDate = formatDate(bundle.getString("checkInDate"));
            checkOutDate = formatDate(bundle.getString("checkOutDate"));
        }
    }

    private void getHotelsListsData() {
        progressBar.setVisibility(View.VISIBLE);
        HotelApiService service = RetrofitClient.getService();
        service.getHotels(checkInDate, checkOutDate, numberOfGuests).enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    setupRecyclerView(response.body());
                } else {
                    // Handle the error situation
                    String errorMessage = "An error occurred: " + response.message();
                    if (response.code() == 404) {
                        errorMessage = "No hotels found for the given dates.";
                    } else if (response.code() == 500) {
                        errorMessage = "Server error. Please try again later.";
                    }
                    errorMessage = "An error occurred: " + response.message();
                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                String errorMessage = "An error occurred: " + t.getMessage();
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupRecyclerView(List<Hotel> hotels) {
        adapter = new HotelAdapter(hotels, this);
        recyclerView.setAdapter(adapter);
    }

    private String formatDate(String dateInput) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate date = LocalDate.parse(dateInput, inputFormatter);
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            // Handle exception if the date input is not in the expected format
            Toast.makeText(getContext(), "Invalid date format.", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        // Get the clicked hotel
        Hotel clickedHotel = adapter.getItem(position);
        Toast.makeText(getContext(), "Clicked: " + clickedHotel.getName(), Toast.LENGTH_SHORT).show();

        // Prepare data to pass to the new fragment
        Bundle bundle = new Bundle();
        bundle.putString("hotelName", clickedHotel.getName());
        bundle.putLong("hotelId", clickedHotel.getId());
        bundle.putString("hotelPrice", clickedHotel.getPrice());
        bundle.putString("checkInDate", checkInDate);
        bundle.putString("checkOutDate", checkOutDate);
        bundle.putInt("numberOfGuests", numberOfGuests);

        // Create a new instance of HotelDetailFragment and set its arguments
        HotelGuestDetailsFragment hotelDetailFragment = new HotelGuestDetailsFragment();
        hotelDetailFragment.setArguments(bundle);

        // Perform the fragment transaction to replace this fragment with HotelDetailFragment
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, hotelDetailFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }



}