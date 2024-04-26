package com.example.hotel_reservation_system;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel_reservation_system.R;

public class HotelViewHolder extends RecyclerView.ViewHolder {

    TextView textViewHotelName;
    TextView textViewHotelPrice;
    TextView textViewHotelAvailability;

    public HotelViewHolder(View itemView) {
        super(itemView);
        textViewHotelName = itemView.findViewById(R.id.textViewHotelName);
        textViewHotelPrice = itemView.findViewById(R.id.textViewHotelPrice);
        textViewHotelAvailability = itemView.findViewById(R.id.textViewHotelAvailability);
    }
}
