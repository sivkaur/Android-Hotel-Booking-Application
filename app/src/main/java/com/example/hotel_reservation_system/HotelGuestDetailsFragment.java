package com.example.hotel_reservation_system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel_reservation_system.model.Guest;

import java.util.ArrayList;
import java.util.List;

public class HotelGuestDetailsFragment extends Fragment {

    private TextView hotelNameTextView, checkInDateTextView, checkOutDateTextView, priceTextView;
    private RecyclerView guestsRecyclerView;
    private Button submitButton;
    private ProgressBar progressBar;
    private List<Guest> guestsList = new ArrayList<>();
    private GuestAdapter guestAdapter;
    private int numberOfGuests;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hotel_guest_details_fragment, container, false);

        hotelNameTextView = view.findViewById(R.id.textViewHotelName);
        checkInDateTextView = view.findViewById(R.id.textViewCheckInDate);
        checkOutDateTextView = view.findViewById(R.id.textViewCheckOutDate);
        priceTextView = view.findViewById(R.id.textViewPrice);
        guestsRecyclerView = view.findViewById(R.id.recyclerViewGuests);
        submitButton = view.findViewById(R.id.submitNext);
        progressBar = view.findViewById(R.id.progressBarLoading);

        // Set up RecyclerView
        guestAdapter = new GuestAdapter(guestsList);
        guestsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        guestsRecyclerView.setAdapter(guestAdapter);

        // Load data passed from previous screen
        loadHotelDetails();

        // Load guests input based on the number of guests
        initializeGuestInput();

        submitButton.setOnClickListener(v -> submitDetails());

        return view;
    }

    private void loadHotelDetails() {
        Bundle args = getArguments();
        if (args != null) {
            hotelNameTextView.setText(args.getString("hotelName", "Not Available"));
            checkInDateTextView.setText(args.getString("checkInDate", "Not Available"));
            checkOutDateTextView.setText(args.getString("checkOutDate", "Not Available"));
            priceTextView.setText(args.getString("hotelPrice", "0"));

        }
    }

    private void initializeGuestInput() {
        numberOfGuests = getArguments() != null ? getArguments().getInt("numberOfGuests", 1) : 1;
        for (int i = 0; i < numberOfGuests; i++) {
            guestsList.add(new Guest("", "Male"));
        }
        guestAdapter.notifyDataSetChanged();
    }

    private void submitDetails() {
        ArrayList<Bundle> guestsBundles = new ArrayList<>();
        for (Guest guest : guestsList) {
            Bundle guestBundle = new Bundle();
            guestBundle.putString("name", guest.getName());
            guestBundle.putString("gender", guest.getGender());
            guestsBundles.add(guestBundle);
        }

        Bundle reservationDetails = new Bundle();
        reservationDetails.putSerializable("guests", guestsBundles);
        reservationDetails.putLong("hotelId", getArguments().getLong("hotelId"));
        reservationDetails.putString("checkinDate", checkInDateTextView.getText().toString());
        reservationDetails.putString("checkoutDate", checkOutDateTextView.getText().toString());
        reservationDetails.putString("hotelName", hotelNameTextView.getText().toString());
        reservationDetails.putString("price", priceTextView.getText().toString());
        reservationDetails.putInt("numberOfRoomsBooked", numberOfGuests);

        ConfirmationFragment confirmationFragment = new ConfirmationFragment();
        confirmationFragment.setArguments(reservationDetails);

        // Perform the fragment transaction to replace this fragment with ConfirmationFragment
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, confirmationFragment);
        transaction.addToBackStack(null);
        transaction.commit();


    }
}
