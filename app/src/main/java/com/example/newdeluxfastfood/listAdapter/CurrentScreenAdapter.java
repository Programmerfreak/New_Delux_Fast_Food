package com.example.newdeluxfastfood.listAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdeluxfastfood.R;
import com.example.newdeluxfastfood.custom_loading_screen.CurrentOrderScreenDialog;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class CurrentScreenAdapter extends RecyclerView.Adapter<CurrentScreenAdapter.ListViewHolder> {
    private static final String TAG = "CurrentScreenAdapter";
    private List<String> priceList = new ArrayList<>();
    private List<String> orderIDs = new ArrayList<>();
    private CurrentOrderScreenDialog dialog;

    public CurrentScreenAdapter(Context context) {
        dialog = new CurrentOrderScreenDialog(context);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_order_item_view, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        if(priceList.size()>0) {
            holder.priceTextView.setText(priceList.get(position));
            holder.orderID = orderIDs.get(position);
        }
    }

    public void updatePriceList(DocumentSnapshot snapshot) {
        priceList.add(snapshot.getString("amountPaid"));
        orderIDs.add(snapshot.getId());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return priceList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView priceTextView;
        private String orderID;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            priceTextView = itemView.findViewById(R.id.current_price_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            dialog.showDialog(orderID);
        }
    }
}
