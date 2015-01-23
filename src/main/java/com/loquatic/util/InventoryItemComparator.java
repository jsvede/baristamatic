package com.loquatic.baristamatic.util;

import com.loquatic.baristamatic.inventory.interfaces.IInventoryItem;

import java.util.Comparator;

public class InventoryItemComparator implements Comparator<IInventoryItem> {

    public int compare(IInventoryItem iInvItem1, IInventoryItem iInvItem2) {
        return iInvItem1.getName().compareTo(iInvItem2.getName());
    }

}
