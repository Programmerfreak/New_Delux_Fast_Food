package com.example.newdeluxfastfood.listAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdeluxfastfood.R;
import com.example.newdeluxfastfood.utils.menu_item.item1.Item1;
import com.example.newdeluxfastfood.utils.menu_item.item1.Item1Activity;
import com.example.newdeluxfastfood.utils.menu_item.item2.Item2;
import com.example.newdeluxfastfood.utils.menu_item.item2.Item2Activity;
import com.example.newdeluxfastfood.utils.menu_item.item3.Item3Activity;
import com.example.newdeluxfastfood.utils.MenuItem;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private List<MenuItem> list;
    private Context mContext;
    private RecyclerViewOnItemClickListener mRecyclerViewOnItemClickListener;

    public interface RecyclerViewOnItemClickListener {
        void onItemClickListener(int position);
    }

    public ListAdapter(List<MenuItem> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    public void setRecyclerViewOnItemClickListener(RecyclerViewOnItemClickListener rV) {
        this.mRecyclerViewOnItemClickListener = rV;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_order_list_item_view, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        if(!list.isEmpty()) {
            holder.itemTextView.setText(list.get(position).getItemName());
            holder.valueTextView.setText(list.get(position).getQuantity());
            holder.position = position;
        }
    }

    @Override
    public int getItemCount() {
        if(!list.isEmpty())
            return list.size();
        return 0;
    }

    public void updateList(List<MenuItem> items) {
        list = items;
        notifyDataSetChanged();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemTextView, valueTextView;
        int position;
        ImageButton deleteItem;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.itemTextView);
            valueTextView = itemView.findViewById(R.id.valueTextView);
            deleteItem = itemView.findViewById(R.id.deleteImageButton);
            itemView.setOnClickListener(this);

            deleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mRecyclerViewOnItemClickListener != null) {
                        Toast.makeText(mContext, "Item deleted", Toast.LENGTH_SHORT).show();
                        mRecyclerViewOnItemClickListener.onItemClickListener(position);
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            if(list.get(position) instanceof Item1)
                mContext.startActivity(new Intent(mContext, Item1Activity.class).putExtra("Item1", position));
            else if(list.get(position) instanceof Item2)
                mContext.startActivity(new Intent(mContext, Item2Activity.class).putExtra("Item2", position));
            else
                mContext.startActivity(new Intent(mContext, Item3Activity.class).putExtra("Item3", position));
        }
    }
}
