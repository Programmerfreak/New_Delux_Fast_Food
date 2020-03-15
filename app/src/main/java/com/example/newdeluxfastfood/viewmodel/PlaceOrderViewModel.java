package com.example.newdeluxfastfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.newdeluxfastfood.utils.MenuItem;
import com.example.newdeluxfastfood.utils.Order;

import java.util.List;

public class PlaceOrderViewModel extends ViewModel {
    private Order mOrder;
    private LiveData<List<MenuItem>> orders;

    public PlaceOrderViewModel() {
        mOrder = Order.getInstance();
        orders = mOrder.getOrders();
    }

    public LiveData<List<MenuItem>> getOrders() {
        return orders;
    }

    public void deleteItemFromOrder(int position) {
        mOrder.deleteItem(position);
    }

    public List<MenuItem> getMenuItemList() {
        return orders.getValue();
    }
}
