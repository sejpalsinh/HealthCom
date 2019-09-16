package com.example.healthcom;

public class Item {
    private String itemName;
    private String itemDescription;

    public Item(String name, String description) {
        this.itemName = name;
        this.itemDescription = description;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }
}
