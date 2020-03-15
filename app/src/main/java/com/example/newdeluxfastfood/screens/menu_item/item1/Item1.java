package com.example.newdeluxfastfood.screens.menu_item.item1;

import com.example.newdeluxfastfood.utils.MenuItem;

public class Item1 extends MenuItem {
    private final int pricePerKG = 340;
    private final int pricePerGram = 35;
    private String quantity;
    private String quantityInKg;
    private String quantityInGram;

    /**
     * Remember to add constructor to initialize values
     */
    public Item1() {
        setPrice();
        setItemName();
        setDescription();
        setItemDetailsTextViewText();
    }

    void setQuantity(String quantity, String quantityInKg, String quantityInGram) {
        setQuantity(quantity);
        this.quantityInKg = quantityInKg;
        this.quantityInGram = quantityInGram;
    }

    @Override
    public void setPrice() {
        /**
         * TODO
         * Change price to actual price of item that's selected by the user
         * like 1kg100gram is chosen by user the price should be 375
         */
        this.price = 35;
    }

    @Override
    public void setItemName() {
        this.itemName = "Chicken 65";
    }

    @Override
    public void setDescription() {
        this.description = "The popular item here. You are definitely gonna like it";
    }

    @Override
    public void setItemDetailsTextViewText() {
        this.itemDetailsTextViewText = "35 - 100 gram\n340 - 1kg";
    }

    @Override
    public void setQuantity(String val1) {
        this.quantity = val1;
    }

    @Override
    public String getItemName() {
        return this.itemName;
    }

    @Override
    public String getDescriptionName() {
        return this.description;
    }

    @Override
    public String  getQuantity() {
        return quantity;
    }

    String getQuantityKg() {
        return quantityInKg;
    }

    String getQuantityGram() {
        return quantityInGram;
    }

    @Override
    public String getItemDetailsTextViewText() {
        return itemDetailsTextViewText;
    }

    int getPricePerKG() {
        return pricePerKG;
    }

    int getPricePerGram() {
        return pricePerGram;
    }
}
