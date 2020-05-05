package com.example.newdeluxfastfood.utils.menu_item.item2;

import com.example.newdeluxfastfood.utils.MenuItem;

public class Item2 extends MenuItem {
    private final int pricePerKG = 400;
    private final int pricePerGram = 40;
    private String quantity;
    private String quantityKg;
    private String quantityGram;

    /**
     * Remember to add constructor to initialize values
     */
    public Item2() {
        setItemName();
        setDescription();
        setItemDetailsTextViewText();
    }

    @Override
    public void setPrice(int price) {
        /**
         * TODO
         * Change price to actual price of item that's selected by the user
         * like 1kg100gram is chosen by user the price should be 375
         */
        this.price = price;
    }

    public void setQuantity(String quantity, String quantityKg, String quantityGram) {
        setQuantity(quantity);
        this.quantityKg = quantityKg;
        this.quantityGram = quantityGram;
    }

    @Override
    public void setItemName() {
        this.itemName = "Boneless";
    }

    @Override
    public void setDescription() {
        this.description = "Soft piece of chicken without any bones.";
    }

    @Override
    public void setItemDetailsTextViewText() {
        this.itemDetailsTextViewText = "40 - 100 gram\n400 - 1kg";
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
    public String getQuantity() {
        return quantity;
    }

    @Override
    public String getItemDetailsTextViewText() {
        return this.itemDetailsTextViewText;
    }

    int getPricePerKG() {
        return pricePerKG;
    }

    int getPricePerGram() {
        return pricePerGram;
    }

    String getQuantityKg() {
        return quantityKg;
    }

    String getQuantityGram() {
        return quantityGram;
    }
}
