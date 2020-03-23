package com.example.newdeluxfastfood.screens.payment_screen.Paytm;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.newdeluxfastfood.screens.payment_screen.PaymentOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Remember to add
 * compileOptions {
 *         sourceCompatibility JavaVersion.VERSION_1_8
 *         targetCompatibility JavaVersion.VERSION_1_8
 *     }
 * in app level gradle file inside android.
 * If not done the app will crash
*/

public class PaytmPayMethod {
    private static final String TAG = "PaytmPayMethod";
    private String customerID;
    private String orderID;
    private Context mContext;
    private HashMap<String, String> param = new HashMap<>();
    private final String mid = "cWjXlj51560085282100";
    private String price;
    private Connector mConnector = new PaymentOptions();
    private String checksumHash = "";
    private final PaytmPGService service = PaytmPGService.getStagingService("");

    public PaytmPayMethod(Context context, String price) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        customerID = auth.getUid();
        this.mContext = context;
        orderID = generateOrderID();
        this.price = price;
        generateChecksum();
    }

    public void generateChecksum() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://newdeluxepayment.000webhostapp.com/paytm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PaytmChecksumGenerator generator = retrofit.create(PaytmChecksumGenerator.class);

        String callBackUrl = "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=" + orderID;

        param.put("MID", mid);
        param.put("ORDER_ID", orderID);
        param.put("CUST_ID", customerID);
        param.put("CHANNEL_ID", "WAP");
        param.put("TXN_AMOUNT", ""+price);
        param.put("WEBSITE", "WEBSTAGING");
        param.put("CALLBACK_URL", callBackUrl);
        param.put("INDUSTRY_TYPE_ID", "Retail");

        Call<PaytmChecksum> checksumCall = generator.getPaytmChecksum(param);
        checksumCall.enqueue(new Callback<PaytmChecksum>() {
            @Override
            public void onResponse(Call<PaytmChecksum> call, Response<PaytmChecksum> response) {

                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse unSuccessful: "+response.message());
                    return;
                }

                PaytmChecksum paytmChecksum = response.body();
                Log.d(TAG, "onResponse Successful: "+paytmChecksum.getChecksum());
                checksumHash = paytmChecksum.getChecksum();
                startTransaction();
            }

            @Override
            public void onFailure(Call<PaytmChecksum> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void startTransaction() {

        param.put("CHECKSUMHASH", checksumHash);
        PaytmOrder order = new PaytmOrder(param);

        service.initialize(order, null);
        service.startPaymentTransaction(mContext, true, true,
                new PaytmPaymentTransactionCallback() {
                    @Override
                    public void onTransactionResponse(Bundle inResponse) {
                        Log.d(TAG, "onTransactionResponse: "+inResponse.toString());
                        mConnector.getResponse(inResponse.toString());
                    }

                    @Override
                    public void networkNotAvailable() {

                    }

                    @Override
                    public void clientAuthenticationFailed(String inErrorMessage) {

                    }

                    @Override
                    public void someUIErrorOccurred(String inErrorMessage) {

                    }

                    @Override
                    public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {

                    }

                    @Override
                    public void onBackPressedCancelTransaction() {
                        mConnector.getResponse("onBackPressedCancelTransaction called");
                    }

                    @Override
                    public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
                        Log.d(TAG, "onTransactionCancel: "+inResponse.toString());
                        mConnector.getResponse(inResponse.toString());
                    }
                });
    }

    private String generateOrderID() {
        String s = UUID.randomUUID().toString();
        return s.replace("-", "");
    }

    public interface Connector {
        void getResponse(String response);
    }
}
