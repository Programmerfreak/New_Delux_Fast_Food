package com.example.newdeluxfastfood.screens.place_new_order_screen;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.newdeluxfastfood.databinding.ActivityMenuBinding;
import com.example.newdeluxfastfood.listAdapter.MenuListAdapter;

public class Menu extends AppCompatActivity {
    private ActivityMenuBinding binding;
    private final MenuListAdapter mMenuListAdapter = new MenuListAdapter(Menu.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.menuRecyclerView.setAdapter(mMenuListAdapter);
        binding.menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
