package com.example.hotel_reservation_system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel_reservation_system.HotelViewHolder;
import com.example.hotel_reservation_system.R;
import com.example.hotel_reservation_system.model.Hotel;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelViewHolder> {

    private List<Hotel> hotelList;
    private ItemClickListener itemClickListener;
    private int selectedPosition = -1;

    public HotelAdapter(List<Hotel> hotelList, ItemClickListener listener) {
        this.hotelList = hotelList;
        this.itemClickListener = listener;
    }
    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_list_item, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.textViewHotelName.setText(hotel.getName());
        holder.textViewHotelPrice.setText(hotel.getPrice());
        String availabilityText = "true".equals(hotel.getAvailability()) ? "Available" : "Not Available";
        holder.textViewHotelAvailability.setText(availabilityText);

        // Highlight the selected item
        holder.itemView.setSelected(selectedPosition == position);

        // Set the click listener to update the selected position
        holder.itemView.setOnClickListener(view -> {
            if (selectedPosition != position) {
                notifyItemChanged(selectedPosition);
                selectedPosition = position;
                notifyItemChanged(selectedPosition);
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(view, position);
                }
            }
        });
    }

    // Method to get the hotel at a given position
    public Hotel getItem(int position) {
        return hotelList.get(position);
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    // Method to get the selected hotel
    public Hotel getSelectedHotel() {
        if (selectedPosition != -1) {
            return hotelList.get(selectedPosition);
        }
        return null;
    }
}