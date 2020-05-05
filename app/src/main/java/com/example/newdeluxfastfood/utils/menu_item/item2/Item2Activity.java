package com.example.newdeluxfastfood.utils.menu_item.item2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newdeluxfastfood.R;
import com.example.newdeluxfastfood.databinding.ActivityItem2Binding;
import com.example.newdeluxfastfood.utils.Order;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class Item2Activity extends AppCompatActivity {
    private ActivityItem2Binding binding;
    private final String ITEM2_TAG = "Item2Activity";
    private String quantity;
    private Item2 item;
    private int pricePerKG;
    private int pricePerGram;
    private Order mOrder;
    private int price;
    private int position;
    private String [] gramItems;
    private String quantityInKg;
    private String quantityInGram;
    private int spinnerKG = 0;
    private int spinnerGram = 0;
    private Snackbar mSnackbar;

    public Item2Activity() {
        mOrder = Order.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String itemQuantityInKg;
        String itemQuantityInGram;
        Item2 item2Object = new Item2();
        View contextView = findViewById(android.R.id.content);
        mSnackbar = Snackbar.make(contextView, "Order cannot be placed", BaseTransientBottomBar.LENGTH_SHORT);

        super.onCreate(savedInstanceState);
        binding = ActivityItem2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.itemDetailTextView.setText(new Item2().getItemDetailsTextViewText());
        pricePerKG = item2Object.getPricePerKG();
        pricePerGram = item2Object.getPricePerGram();

        position = getIntent().getIntExtra("Item2", -1);

        gramItems = getResources().getStringArray(R.array.Chicken65gm);

        for(String v:gramItems)
            System.out.println(v);

        if(position != -1) {
            //Casting MenuItem object to Item2 object explicitly
            itemQuantityInKg = ((Item2)mOrder.getOrders().getValue().get(position)).getQuantityKg();
            itemQuantityInGram = ((Item2)mOrder.getOrders().getValue().get(position)).getQuantityGram();

            binding.kgSpinner.setSelection(Integer.parseInt(itemQuantityInKg));
            binding.gramSpinner.setSelection(Integer.parseInt(itemQuantityInGram)/100);
            Log.i(ITEM2_TAG, itemQuantityInKg+" "+itemQuantityInGram);
        }

        binding.kgSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quantityInKg = parent.getItemAtPosition(position).toString();
                spinnerKG = Integer.parseInt(quantityInKg);
                setEstimatedTextView(spinnerKG, spinnerGram);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.gramSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quantityInGram = parent.getItemAtPosition(position).toString();
                spinnerGram = Integer.parseInt(quantityInGram);
                setEstimatedTextView(spinnerKG, spinnerGram);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerKG != 0 || spinnerGram != 0) {
                    item = new Item2();
                    //Setting quantity string that would be displayed in 'ListAdapter'
                    setQuantityForClass(quantityInKg, quantityInGram);

                    //Passing three string values, from which 'quantityInKg' and 'quantityInGram' can be
                    //taken again from object to set spinner values when activity is called from 'ListAdapter'
                    item.setQuantity(quantity, quantityInKg, quantityInGram);
                    item.setPrice(price);
                    mOrder.updateOrder(item);
                    Toast.makeText(Item2Activity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    mSnackbar.show();
                }
            }
        });
    }

    void setQuantityForClass(String kg, String gram) {
        quantity = String.format("%s kg %s gram", kg, gram);
    }

    void setEstimatedTextView(int spinnerKG, int spinnerGram) {
        if(spinnerKG!=0 || spinnerGram!=0) {
            binding.estimatedTextView.animate().alpha(1);
            price = (spinnerKG * pricePerKG) + ((spinnerGram / 100) * pricePerGram);
            Log.i("Estimated price:", String.valueOf(price));
            binding.estimatedTextView.setText(String.valueOf(price));
        } else {
            binding.estimatedTextView.animate().alpha(0);
        }
    }
}
