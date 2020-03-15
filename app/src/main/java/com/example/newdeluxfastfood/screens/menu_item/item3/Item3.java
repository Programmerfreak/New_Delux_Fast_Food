package com.example.newdeluxfastfood.screens.menu_item.item3;

import com.example.newdeluxfastfood.utils.MenuItem;

public class Item3 extends MenuItem {
    private final int halfPlatePrice = 40;
    private final int fullPlatePrice = 80;
    private int positionOfPlateSelected;
    private String quantity;

    /**
     * Remember to add constructor to initialize values
     */
    public Item3() {
        setItemName();
        setDescription();
        setItemDetailsTextViewText();
    }

    @Override
    public void setPrice() {
        /**
         * TODO
         * set price by user order quantity ordered
         */
        this.price = 30;
    }

    @Override
    public void setItemName() {
        this.itemName = "Lollipop";
    }

    @Override
    public void setDescription() {
        this.description = "Spicy chicken wings, enough to satisfy hunger";
    }

    @Override
    public void setItemDetailsTextViewText() {
        this.itemDetailsTextViewText = "Half Plate(3 pieces) - 40\nOne Plate(6 pieces) - 80";
    }

    @Override
    public void setQuantity(String val1) {
        quantity = val1;
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

    public int getHalfPlatePrice() {
        return halfPlatePrice;
    }

    public int getFullPlatePrice() {
        return fullPlatePrice;
    }

    public int getPositionOfPlateSelected() {
        return positionOfPlateSelected;
    }

    public void setPositionOfPlateSelected(int positionOfPlateSelected) {
        this.positionOfPlateSelected = positionOfPlateSelected;
    }
}
