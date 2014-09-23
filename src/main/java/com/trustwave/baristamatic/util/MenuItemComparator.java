package com.trustwave.baristamatic.util;

import java.util.Comparator;

import com.trustwave.baristamatic.menu.interfaces.IMenuItem;

public class MenuItemComparator implements Comparator<IMenuItem> {

	public int compare(IMenuItem iMenuItem1, IMenuItem iMenuItem2) {
		return iMenuItem1.getName().compareTo( iMenuItem2.getName() ) ;
	}

}
