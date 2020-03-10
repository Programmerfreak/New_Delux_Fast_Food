package com.example.newdeluxfastfood.utils;

public class Order {
    private static Order order;

    static Order getInstance() {
        if(order == null)
            order = new Order();
        return order;
    }
}
