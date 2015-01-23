package com.loquatic.tests;

import com.loquatic.baristamatic.inventory.impl.InventoryManager;
import com.loquatic.baristamatic.inventory.interfaces.IInventoryManager;
import junit.framework.TestCase;
import org.apache.log4j.Logger;

public class InventoryManagerTest extends TestCase {

    private Logger log = Logger.getLogger(InventoryManagerTest.class);

    public void testList() {
        IInventoryManager iim = InventoryManager.getInstance();
    }

    public void testInventoryExists() {
        IInventoryManager iim = InventoryManager.getInstance();
        assertTrue(iim.itemExists("Coffee"));
        assertFalse(iim.itemExists("Coffee Cake"));
    }

    public void testInventoryAmount() {
        IInventoryManager iim = InventoryManager.getInstance();
        assertTrue(iim.sufficientItemsOnHand("Coffee", 9));
        assertFalse(iim.sufficientItemsOnHand("Coffee", 0));
    }

    public void testDecrementInventory() {
        IInventoryManager iim = InventoryManager.getInstance();
        iim.deductItemCount("Coffee", 3);
        assertTrue(iim.sufficientItemsOnHand("Coffee", 3));
        iim.deductItemCount("Coffee", 3);
        assertTrue(iim.sufficientItemsOnHand("Coffee", 3));
        iim.deductItemCount("Coffee", 3);
        assertFalse(iim.sufficientItemsOnHand("Coffee", 3));
    }

    public void testLoggingToPrintStream() {
        IInventoryManager iim = InventoryManager.getInstance();
        iim.listInventory(System.out);
    }
}
