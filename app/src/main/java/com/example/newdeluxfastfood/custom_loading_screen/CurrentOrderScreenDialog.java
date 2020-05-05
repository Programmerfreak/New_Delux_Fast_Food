package com.example.newdeluxfastfood.custom_loading_screen;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;

import com.example.newdeluxfastfood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.lang.ref.WeakReference;

public class CurrentOrderScreenDialog {
    private static final String TAG = "CurrentOrderScreenDialo";
    private WeakReference<Context> context;
    private Dialog mDialog;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public CurrentOrderScreenDialog(Context context) {
        this.context = new WeakReference<>(context);
    }

    public void showDialog(String orderID) {
        Log.d(TAG, "showDialog: "+orderID);

        mDialog = new Dialog(context.get());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.current_order_screen_dialog);

        ImageView imageView = mDialog.findViewById(R.id.QR_code_image_view);

        try {
            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix matrix = writer.encode(mAuth.getUid()+"-"+orderID, BarcodeFormat.QR_CODE, 600, 600);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            imageView.setImageBitmap(bitmap);
            mDialog.show();
        }catch (Exception e) {
            Log.e(TAG, "showDialog: ", e);
        }
    }
}
