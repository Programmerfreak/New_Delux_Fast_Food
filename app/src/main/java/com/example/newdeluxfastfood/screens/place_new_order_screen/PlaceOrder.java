package com.example.newdeluxfastfood.screens.place_new_order_screen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newdeluxfastfood.databinding.ActivityPlaceOrderBinding;
import com.example.newdeluxfastfood.listAdapter.ListAdapter;
import com.example.newdeluxfastfood.screens.place_new_order_screen.payment_screen.PaymentOptions;
import com.example.newdeluxfastfood.utils.MenuItem;
import com.example.newdeluxfastfood.viewmodel.PlaceOrderViewModel;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrder extends AppCompatActivity implements ListAdapter.RecyclerViewOnItemClickListener {
    private ActivityPlaceOrderBinding binding;
    private ListAdapter adapter;
    private PlaceOrderViewModel viewModel;
    private boolean IS_ORDER_EMPTY = true;
    private int price = 0;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaceOrderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(PlaceOrderViewModel.class);

        adapter = new ListAdapter(viewModel.getMenuItemList(), this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setRecyclerViewOnItemClickListener(this);

        setLayoutItems();

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlaceOrder.this, Menu.class));
            }
        });

        binding.payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> orderItems = new ArrayList<>();
                for(MenuItem order: viewModel.getOrders().getValue()!=null?viewModel.getOrders().getValue():new ArrayList<MenuItem>())
                    orderItems.add(order.getItemName());
                startActivity(new Intent(PlaceOrder.this, PaymentOptions.class)
                        .putExtra("Price", price)
                        .putExtra("orderItems", orderItems));
            }
        });

        viewModel.getOrders().observe(this, new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(List<MenuItem> menuItems) {
                if(viewModel.getOrders().getValue().size() != 0)
                    IS_ORDER_EMPTY = false;
                else
                    IS_ORDER_EMPTY = true;
                setLayoutItems();
                getEstimatedPrice();

                Log.i("PlaceOrder:", String.valueOf(viewModel.getOrders().getValue().size()));
                adapter.updateList(menuItems);
            }
        });
    }

    void getEstimatedPrice() {
        //Setting price again to 0 because the price appends whenever the item is pressed from the list 'ListAdapter'
        //So to avoid that price is set to 0 every time
        price = 0;
        if(!IS_ORDER_EMPTY) {
            for (MenuItem values : viewModel.getOrders().getValue())
                price += values.getPrice();
            binding.finalPriceTextView.setText("Total Pay: "+price);
        }
        Log.i("PlaceOrder price", String.valueOf(price));
    }

    void setLayoutItems() {
        if(IS_ORDER_EMPTY) {
            binding.cartEmptyTextView.animate().alpha(1);
            binding.payButton.animate().alpha(0);
            binding.finalPriceTextView.animate().alpha(0);
        }
        else {
            binding.cartEmptyTextView.animate().alpha(0);
            binding.payButton.animate().alpha(1);
            binding.finalPriceTextView.animate().alpha(1);
        }
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