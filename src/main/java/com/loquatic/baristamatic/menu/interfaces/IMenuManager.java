package com.loquatic.baristamatic.menu.interfaces;

import com.loquatic.baristamatic.inventory.interfaces.IInventoryManager;

import java.io.PrintStream;

public interface IMenuManager {

    public void setInventoryManager(IInventoryManager invManager);

    public void buildMenu();

    public boolean orderItem(int itemNumber);

    public boolean isItemAvailable(int itemNumber);

    public void listMenu(PrintStream out);

    public IMenuItem getMenuItem(int itemNumber);

}
