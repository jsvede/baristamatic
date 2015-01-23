package com.loquatic.baristamatic.inventory.impl;

import com.loquatic.baristamatic.constants.Constants;
import com.loquatic.baristamatic.inventory.interfaces.IInventoryItem;
import com.loquatic.baristamatic.inventory.interfaces.IInventoryManager;
import com.loquatic.baristamatic.util.InventoryItemComparator;
import org.apache.log4j.Logger;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class InventoryManager implements IInventoryManager {

    private Logger log = Logger.getLogger(InventoryManager.class);

    private static InventoryManager invManager;

    private ArrayList<IInventoryItem> inventory;

    private String messageForInventory = "{0},{1}";

    private InventoryManager() {
        inventory = new ArrayList<IInventoryItem>();
    }

    public static IInventoryManager getInstance() {
        if (invManager == null) {
            invManager = new InventoryManager();
            invManager.initialize();
        }
        return invManager;
    }

    public void initialize() {
        if (inventory == null) inventory = new ArrayList<IInventoryItem>();
        inventory.add(new InventoryItem(Constants.COFFEE, 0.75f, 10));
        inventory.add(new InventoryItem(Constants.DECAF_COFFEE, 0.75f, 10));
        inventory.add(new InventoryItem(Constants.SUGAR, 0.25f, 10));
        inventory.add(new InventoryItem(Constants.CREAM, 0.25f, 10));
        inventory.add(new InventoryItem(Constants.STEAMED_MILK, 0.35f, 10));
        inventory.add(new InventoryItem(Constants.FOAMED_MILK, 0.35f, 10));
        inventory.add(new InventoryItem(Constants.ESPRESSO, 1.10f, 10));
        inventory.add(new InventoryItem(Constants.COCOA, 0.90f, 10));
        inventory.add(new InventoryItem(Constants.WHIPPED_CREAM, 1.0f, 10));
    }

    public boolean itemExists(String itemName) {
        if (getItem(itemName) != null) {
            return true;
        } else {
            return false;
        }

    }

    public IInventoryItem getItem(String itemName) {
        for (IInventoryItem item : inventory) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public boolean sufficientItemsOnHand(String itemName, int amountNeeded) {
        IInventoryItem item = getItem(itemName);
        if (item != null) {
            int currentOnHand = item.getInventory();

            if (amountNeeded > 0 && currentOnHand >= amountNeeded) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void deductItemCount(String itemName, int amountDeducted) {
        IInventoryItem itemToBeDecremented = getItem(itemName);
        if (itemToBeDecremented != null) {
            int currentStock = itemToBeDecremented.getInventory();

            if (currentStock > 0 && currentStock >= amountDeducted) {
                itemToBeDecremented.setInventory(currentStock - amountDeducted);
            }
        }

    }

    public void restock() {
        inventory = null;
        initialize();
    }

    public void listInventory(PrintStream out) {
        Collections.sort(inventory, new InventoryItemComparator());
        out.println(Constants.INVENTORY + Constants.LINE_FEED);
        if (inventory != null && inventory.size() > 0) {
            Iterator listIt = inventory.iterator();
            while (listIt.hasNext()) {
                IInventoryItem item = (IInventoryItem) listIt.next();
                out.println(MessageFormat.format(messageForInventory, item.getName(),
                        item.getInventory()) + Constants.LINE_FEED);
            }
        }
    }

    public void setInventoryTemplate(String template) {
        messageForInventory = template;
    }
}
