package com.example.newdeluxfastfood.listAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newdeluxfastfood.R;
import com.example.newdeluxfastfood.screens.menu_item.item1.Item1;
import com.example.newdeluxfastfood.screens.menu_item.item1.Item1Activity;
import com.example.newdeluxfastfood.screens.menu_item.item2.Item2;
import com.example.newdeluxfastfood.screens.menu_item.item2.Item2Activity;
import com.example.newdeluxfastfood.screens.menu_item.item3.Item3;
import com.example.newdeluxfastfood.screens.menu_item.item3.Item3Activity;
import com.example.newdeluxfastfood.utils.MenuItem;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ListHolder> {
    //For temporary purpose making new Menu List every time the activity is created
    private List<MenuItem> mMenuItems = new LinkedList<>(Collections.<MenuItem>emptyList());
    private Context mContext;

    public MenuListAdapter(Context context) {
        mMenuItems.add(new Item1());
        mMenuItems.add(new Item2());
        mMenuItems.add(new Item3());
        mContext = context;
    }

    @NonNull
    @Override
    public MenuListAdapter.ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menuitem_list_item_view, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuListAdapter.ListHolder holder, int position) {
        holder.itemTextView.setText(mMenuItems.get(position).getItemName());
        holder.descTextView.setText(mMenuItems.get(position).getDescriptionName());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return mMenuItems.size();
    }

    class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemTextView;
        TextView descTextView;
        int position;

        ListHolder(@NonNull View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.itemNameTextView);
            descTextView = itemView.findViewById(R.id.descTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(position == 0)
                mContext.startActivity(new Intent(mContext, Item1Activity.class));
            else if(position == 1)
                mContext.startActivity(new Intent(mContext, Item2Activity.class));
            else
                mContext.startActivity(new Intent(mContext, Item3Activity.class));
        }
    }
}
