package com.loquatic.baristamatic.util;

import com.loquatic.baristamatic.menu.interfaces.IMenuItem;

import java.util.Comparator;

public class MenuItemComparator implements Comparator<IMenuItem> {

    public int compare(IMenuItem iMenuItem1, IMenuItem iMenuItem2) {
        return iMenuItem1.getName().compareTo(iMenuItem2.getName());
    }

}
