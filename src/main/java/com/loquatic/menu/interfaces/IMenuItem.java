package com.loquatic.baristamatic.menu.interfaces;

import com.loquatic.baristamatic.inventory.interfaces.IInventoryItem;

import java.util.HashMap;

public interface IMenuItem extends Comparable<Object> {

    public void setItemNumber(int itemNum);

    public int getItemNumber();

    public void addIngredient(IInventoryItem item, float quantity);

    public void setName(String name);

    public String getName();

    public float getCost();

    public void setMessageFormat(String format);

    public HashMap<IInventoryItem, Float> getIngredients();

    public String toString(int index, boolean available);

}
