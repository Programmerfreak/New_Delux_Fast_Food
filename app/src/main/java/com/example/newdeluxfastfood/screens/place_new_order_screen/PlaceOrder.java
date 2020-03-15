package com.example.newdeluxfastfood.screens.place_new_order_screen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newdeluxfastfood.databinding.ActivityPlaceOrderBinding;
import com.example.newdeluxfastfood.listAdapter.ListAdapter;
import com.example.newdeluxfastfood.utils.MenuItem;
import com.example.newdeluxfastfood.viewmodel.PlaceOrderViewModel;

import java.util.List;

public class PlaceOrder extends AppCompatActivity implements ListAdapter.RecyclerViewOnItemClickListener {
    private ActivityPlaceOrderBinding binding;
    private ListAdapter adapter;
    private PlaceOrderViewModel viewModel;
    private boolean IS_ORDER_EMPTY = true;
    private LiveData<List<MenuItem>> orders;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaceOrderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(PlaceOrderViewModel.class);
        orders = viewModel.getOrders();

        adapter = new ListAdapter(viewModel.getMenuItemList(), this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setRecyclerViewOnItemClickListener(this);

        setTextView();

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlaceOrder.this, Menu.class));
            }
        });

        orders.observe(this, new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(List<MenuItem> menuItems) {
                if(orders.getValue().size() != 0)
                    IS_ORDER_EMPTY = false;
                else
                    IS_ORDER_EMPTY = true;
                setTextView();

                Log.i("PlaceOrder:", String.valueOf(orders.getValue().size()));
                adapter.updateList(menuItems);
            }
        });
    }

    void setTextView() {
        if(IS_ORDER_EMPTY)
            binding.cartEmptyTextView.animate().alpha(1);
        else
            binding.cartEmptyTextView.animate().alpha(0);
    }

    @Override
    public void onItemClickListener(int position) {
        this.position = position;
        Log.i("PlaceOrder ListAdapter", ""+position);
        deleteItem();
    }

    public void deleteItem() {
        viewModel.deleteItemFromOrder(position);
    }
}

