package com.example.newdeluxfastfood.utils;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newdeluxfastfood.utils.menu_item.item1.Item1;
import com.example.newdeluxfastfood.utils.menu_item.item2.Item2;
import com.example.newdeluxfastfood.utils.menu_item.item3.Item3;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Order {
    private static Order mOrder;
    private MutableLiveData<List<MenuItem>> orders = new MutableLiveData<>();

    //Temporary list to hold values
    private List<MenuItem> tempList = new LinkedList<>(Collections.<MenuItem>emptyList());

    //Setting up a flag to know that there is an instance of item already present in tempList
    //and not adding it as new item in tempList
    private boolean MODIFIED = false;

    private Order() {
        orders.setValue(tempList);
    }

    //Singleton class
    public static Order getInstance() {
        if(mOrder == null)
            mOrder = new Order();
        return mOrder;
    }

    //Used to modify Live Data
    public void updateOrder(MenuItem item) {
        for(int i=0;i<tempList.size();i++)
            if(item instanceof Item1 && tempList.get(i) instanceof Item1 ||
                item instanceof Item2 && tempList.get(i) instanceof Item2 ||
                item instanceof Item3 && tempList.get(i) instanceof Item3) {

                Log.i("Order", "There is an instance of Item1 in order list");
                tempList.remove(i);
                tempList.add(i, item);
                //Set true to know the list is modified
                MODIFIED = true;
                break;
            }

        //Check if the list is already modified or not
        if(!MODIFIED)
            tempList.add(item);
        //Reset value
        MODIFIED = false;
        orders.postValue(tempList);
    }

    public void deleteItem(int position) {
        tempList.remove(position);
        orders.postValue(tempList);
    }

    public LiveData<List<MenuItem>> getOrders() {
        return orders;
    }
}
