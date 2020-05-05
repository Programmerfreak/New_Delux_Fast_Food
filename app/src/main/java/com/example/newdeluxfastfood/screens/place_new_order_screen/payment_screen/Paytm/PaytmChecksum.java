package com.example.newdeluxfastfood.screens.place_new_order_screen.payment_screen.Paytm;

import com.google.gson.annotations.SerializedName;

public class PaytmChecksum {

    @SerializedName("CHECKSUMHASH")
    private String checksum;

    public String getChecksum() {
        return checksum;
    }
}
