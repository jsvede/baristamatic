package com.loquatic.tests;


import com.loquatic.baristamatic.inventory.impl.InventoryManager;
import com.loquatic.baristamatic.inventory.interfaces.IInventoryManager;
import com.loquatic.baristamatic.menu.impl.MenuManager;
import com.loquatic.baristamatic.menu.interfaces.IMenuManager;
import junit.framework.TestCase;

import org.apache.log4j.Logger;

public class MenuManagerTest extends TestCase {

    private final Logger log = Logger.getLogger(MenuManagerTest.class);

    public void testMenuList() {
        IInventoryManager invManager = InventoryManager.getInstance();
        invManager.initialize();

        IMenuManager menuManager = MenuManager.getInstance(invManager);

        menuManager.listMenu(System.out);
    }

    public void testSellingBeverages() {
        IInventoryManager invManager = InventoryManager.getInstance();
        invManager.initialize();

        IMenuManager menuManager = MenuManager.getInstance(invManager);

        menuManager.isItemAvailable(5);
    }

    public void testOrderingBeverage() {
        IInventoryManager invManager = InventoryManager.getInstance();
        invManager.initialize();

        IMenuManager menuManager = MenuManager.getInstance(invManager);

        menuManager.listMenu(System.out);

        invManager.listInventory(System.out);
        if (menuManager.isItemAvailable(5)) {
            menuManager.orderItem(5);
        }
        invManager.listInventory(System.out);
    }
}
