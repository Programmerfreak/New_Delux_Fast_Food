package com.example.newdeluxfastfood.screens.menu_item.item3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newdeluxfastfood.databinding.ActivityItem3Binding;
import com.example.newdeluxfastfood.utils.Order;

public class Item3Activity extends AppCompatActivity {
    private ActivityItem3Binding binding;
    private String quantity;
    private String spinnerValue;
    private Item3 item3;
    private Order mOrder;
    private int position;
    private int spinnerPosition;
    private int priceOfPlate;

    public Item3Activity() {
        mOrder = Order.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int selection;

        super.onCreate(savedInstanceState);
        binding = ActivityItem3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.itemDetailTextView.setText(new Item3().getItemDetailsTextViewText());

        position = getIntent().getIntExtra("Item3", -1);

        priceOfPlate = new Item3().getHalfPlatePrice();

        if(position != -1) {
            selection = ((Item3)mOrder.getOrders().getValue().get(position)).getPositionOfPlateSelected();
            binding.plateSpinner.setSelection(selection);
        }

        binding.plateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue = parent.getItemAtPosition(position).toString();
                spinnerPosition = position;
                setEstimatedTextView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item3 = new Item3();
                setThisClassQuantity();
                item3.setQuantity(quantity);
                Toast.makeText(Item3Activity.this, "Item added to cart", Toast.LENGTH_SHORT).show();
                item3.setPositionOfPlateSelected(spinnerPosition);
                mOrder.updateOrder(item3);
                finish();
            }
        });
    }

    public void setThisClassQuantity() {
        //'quantity' is used in 'ListAdapter' to show quantity of item
        quantity = spinnerValue + " pieces";
    }

    public void setEstimatedTextView() {
        int price = (Integer.parseInt(spinnerValue)/3)*priceOfPlate;
        binding.estimatedTextView.setText(String.valueOf(price));
    }
}
