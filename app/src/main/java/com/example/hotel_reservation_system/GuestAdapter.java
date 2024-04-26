package com.example.hotel_reservation_system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel_reservation_system.model.Guest;

import java.util.List;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.GuestViewHolder> {

    private List<Guest> guestList;

    public GuestAdapter(List<Guest> guestList) {
        this.guestList = guestList;
    }

    @NonNull
    @Override
    public GuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_guest_details_item, parent, false);
        return new GuestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestViewHolder holder, int position) {
        Guest guest = guestList.get(position);
        holder.guestNameEditText.setText(guest.getName());
        holder.genderGroup.check(guest.getGender().equals("Male") ? R.id.genderMale : R.id.genderFemale);
    }

    @Override
    public int getItemCount() {
        return guestList.size();
    }

    public static class GuestViewHolder extends RecyclerView.ViewHolder {
        EditText guestNameEditText;
        RadioGroup genderGroup;
        RadioButton genderMale, genderFemale, genderOther;

        public GuestViewHolder(View itemView) {
            super(itemView);
            guestNameEditText = itemView.findViewById(R.id.guestName);
            genderGroup = itemView.findViewById(R.id.genderGroup);
            genderMale = itemView.findViewById(R.id.genderMale);
            genderFemale = itemView.findViewById(R.id.genderFemale);
            genderOther = itemView.findViewById(R.id.genderOther);
        }
    }
}
