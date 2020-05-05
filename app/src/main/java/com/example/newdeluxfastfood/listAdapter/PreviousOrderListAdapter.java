package com.example.newdeluxfastfood.listAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdeluxfastfood.R;
import com.example.newdeluxfastfood.custom_loading_screen.PreviousOrderSummaryDialog;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class PreviousOrderListAdapter extends RecyclerView.Adapter<PreviousOrderListAdapter.ListViewHolder> {
    private static final String TAG = "PreviousOrderListAdapte";
    private List<String> priceList = new ArrayList<>();
    private List<String> timeList = new ArrayList<>();
    private ArrayList<ArrayList<String>> itemName = new ArrayList<>();
    private PreviousOrderSummaryDialog dialog;

    public PreviousOrderListAdapter(Context context) {
        dialog = new PreviousOrderSummaryDialog(context);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.previous_order_item_view, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        if (priceList.size() != 0) {
            holder.priceTextView.setText(priceList.get(position));
            holder.timeTextView.setText(timeList.get(position));
            holder.orderItems = itemName.get(position);
            holder.price = priceList.get(position);
        }
    }

    @Override
    public int getItemCount() {
        return priceList.size();
    }

    public void updatePriceList(DocumentSnapshot snapshot) {
        priceList.add(snapshot.get("amountPaid").toString());
        timeList.add(snapshot.get("time").toString());
        Log.d(TAG, "updatePriceList: "+snapshot.get("orderItems"));
        try {
            itemName.add((ArrayList<String>) (snapshot.get("orderItems")));
        }catch (Exception e) {
            Log.e(TAG, "updatePriceList: error", e);
            itemName.remove(itemName.size()-1);
        }
        //Log.d(TAG, "updatePriceList: "+snapshot.get("orderItems").toString());
        notifyDataSetChanged();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView priceTextView;
        TextView timeTextView;
        String price;
        ArrayList<String> orderItems;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            priceTextView = itemView.findViewById(R.id.price_text_view);
            timeTextView = itemView.findViewById(R.id.order_date_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            dialog.showSummaryScreen(price, orderItems);
        }
    }
}
