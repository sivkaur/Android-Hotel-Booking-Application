package com.example.hotel_reservation_system;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Only add the fragment if this is the first creation of the activity
        if (savedInstanceState == null) {
            // Begin the transaction
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            // Replace the contents of the container with the new fragment
            fragmentTransaction.add(R.id.fragment_container, new HotelSearchFragment());

            // Complete the changes added above
            fragmentTransaction.commit();
        }
    }
}