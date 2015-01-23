package com.loquatic.baristamatic.inventory.impl;

import com.loquatic.baristamatic.inventory.interfaces.IInventoryItem;

public class InventoryItem implements IInventoryItem {

    private String itemName;
    private float itemCost;
    private int currentInventory;

    public InventoryItem(String name, float cost, int initialInventory) {
        setName(name);
        setCost(cost);
        setInventory(initialInventory);
    }

    public float getCost() {
        return itemCost;
    }

    public String getName() {
        return itemName;
    }

    public void setCost(float cost) {
        itemCost = cost;
    }

    public void setName(String name) {
        itemName = name;
    }

    public int getInventory() {
        return currentInventory;
    }

    public void setInventory(int inventory) {
        currentInventory = inventory;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
        sb.append(", ");
        sb.append(getCost());
        sb.append(", ");
        sb.append(getInventory());

        return sb.toString();
    }

}
