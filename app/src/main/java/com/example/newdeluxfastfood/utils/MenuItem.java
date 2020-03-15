package com.example.newdeluxfastfood.utils;

public abstract class MenuItem {
    protected int price;
    protected String itemName;
    protected String description;
    protected String itemDetailsTextViewText;

    public abstract void setPrice();
    public abstract void setItemName();
    public abstract void setDescription();
    public abstract void setItemDetailsTextViewText();
    public abstract void setQuantity(String val1);

    public abstract String getItemName();
    public abstract String getDescriptionName();
    public abstract String getQuantity();
    public abstract String getItemDetailsTextViewText();
}
