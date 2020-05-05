package com.example.newdeluxfastfood.custom_loading_screen;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.example.newdeluxfastfood.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class PreviousOrderSummaryDialog {
    private static final String TAG = "OrderSummaryDialog";
    private WeakReference<Context> mWeakReference;
    private Dialog dialog;

    public PreviousOrderSummaryDialog(Context context) {
        mWeakReference = new WeakReference<>(context);
    }

    public void showSummaryScreen(String price, ArrayList<String> orderItems) {
        String items = "";

        dialog = new Dialog(mWeakReference.get());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.order_summary_dialog_screen);

        TextView priceTextView = dialog.findViewById(R.id.dialog_price_text_view);
        TextView itemsTextView = dialog.findViewById(R.id.items_name_text_view);

        priceTextView.append(price);

        for(String s: orderItems) {
            items += "-"+s+"\n";
            Log.d(TAG, "showSummaryScreen: " + s);
        }

        itemsTextView.append(items);

        dialog.show();
    }
}
