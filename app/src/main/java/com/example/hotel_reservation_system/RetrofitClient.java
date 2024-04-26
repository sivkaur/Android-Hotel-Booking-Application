package com.example.hotel_reservation_system;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://hotel-reservation-env.eba-x77aii2m.us-east-2.elasticbeanstalk.com/";

    public static HotelApiService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(HotelApiService.class);
    }
}
