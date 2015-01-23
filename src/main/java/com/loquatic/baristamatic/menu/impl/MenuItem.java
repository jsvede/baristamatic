package com.loquatic.baristamatic.menu.impl;

import com.loquatic.baristamatic.inventory.interfaces.IInventoryItem;
import com.loquatic.baristamatic.menu.interfaces.IMenuItem;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MenuItem implements IMenuItem {

    private HashMap<IInventoryItem, Float> ingredientQuantities;
    private String menuItemName;
    private int itemNumber;
    private String messageFormat = "{0},{1},{2,number,$#.00},{3}";

    public MenuItem(String name) {
        ingredientQuantities = new HashMap<IInventoryItem, Float>();
        setName(name);
    }

    public MenuItem(int itemNum, String name) {
        itemNumber = itemNum;
        ingredientQuantities = new HashMap<IInventoryItem, Float>();
        setName(name);
    }

    public void addIngredient(IInventoryItem item, float quantity) {
        ingredientQuantities.put(item, quantity);
    }

    public String getName() {
        return menuItemName;
    }

    public void setName(String name) {
        menuItemName = name;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int number) {
        itemNumber = number;
    }

    public float getCost() {
        float total = 0.0f;
        Set keys = ingredientQuantities.keySet();
        Iterator keyIterator = keys.iterator();
        while (keyIterator.hasNext()) {
            IInventoryItem item = (IInventoryItem) keyIterator.next();
            float quantity = ingredientQuantities.get(item);
            float itemCost = item.getCost();
            float totalCostForItem = quantity * itemCost;
            total = total + totalCostForItem;
        }
        return total;
    }

    public String toString(int index, boolean available) {
        return MessageFormat.format(messageFormat, index, getName(), getCost(), available);
    }

    public void setMessageFormat(String format) {
        messageFormat = format;
    }

    public HashMap<IInventoryItem, Float> getIngredients() {
        return ingredientQuantities;
    }

    public int compareTo(Object o) {
        if (o instanceof IMenuItem) {
            IMenuItem objectForComparison = (IMenuItem) o;
            return getName().compareToIgnoreCase(objectForComparison.getName());
        }
        return 0;
    }


}
