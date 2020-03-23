package com.example.newdeluxfastfood.screens.payment_screen.Paytm;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PaytmChecksumGenerator {
    @FormUrlEncoded
    @POST("generateChecksum.php")
    Call<PaytmChecksum> getPaytmChecksum(@FieldMap HashMap<String, String>parameters);
}
