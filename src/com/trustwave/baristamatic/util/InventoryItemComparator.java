package com.trustwave.baristamatic.util;

import java.util.Comparator;

import com.trustwave.baristamatic.inventory.interfaces.IInventoryItem;

public class InventoryItemComparator implements Comparator<IInventoryItem> {

	public int compare(IInventoryItem iInvItem1, IInventoryItem iInvItem2) {
		return iInvItem1.getName().compareTo( iInvItem2.getName() ) ;
	}

}
