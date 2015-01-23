package com.loquatic.baristamatic.menu.impl;

import com.loquatic.baristamatic.constants.Constants;
import com.loquatic.baristamatic.inventory.interfaces.IInventoryItem;
import com.loquatic.baristamatic.inventory.interfaces.IInventoryManager;
import com.loquatic.baristamatic.menu.interfaces.IMenuItem;
import com.loquatic.baristamatic.menu.interfaces.IMenuManager;
import com.loquatic.baristamatic.util.MenuItemComparator;
import org.apache.log4j.Logger;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MenuManager implements IMenuManager {

    private static MenuManager menuManager;
    private ArrayList<MenuItem> menuItems;
    private IInventoryManager inventoryManager;

    private Logger log = Logger.getLogger(MenuManager.class);

    private MenuManager() {
        menuItems = new ArrayList<MenuItem>();
    }

    public static IMenuManager getInstance(IInventoryManager invMgr) {
        if (menuManager == null) {
            menuManager = new MenuManager();
            menuManager.setInventoryManager(invMgr);
            menuManager.buildMenu();
        }
        return menuManager;
    }

    public void buildMenu() {

        MenuItem caffeAmericano = new MenuItem(Constants.CAFFE_AMERICANO);
        caffeAmericano.addIngredient(inventoryManager.getItem(Constants.ESPRESSO), 3);
        menuItems.add(caffeAmericano);

        MenuItem caffeLatte = new MenuItem(Constants.CAFFE_LATTE);
        caffeLatte.addIngredient(inventoryManager.getItem(Constants.ESPRESSO), 2);
        caffeLatte.addIngredient(inventoryManager.getItem(Constants.STEAMED_MILK), 1);
        menuItems.add(caffeLatte);

        MenuItem caffeMocha = new MenuItem(Constants.CAFFE_MOCHA);
        caffeMocha.addIngredient(inventoryManager.getItem(Constants.ESPRESSO), 1);
        caffeMocha.addIngredient(inventoryManager.getItem(Constants.COCOA), 1);
        caffeMocha.addIngredient(inventoryManager.getItem(Constants.STEAMED_MILK), 1);
        caffeMocha.addIngredient(inventoryManager.getItem(Constants.WHIPPED_CREAM), 1);
        menuItems.add(caffeMocha);

        MenuItem cappuccino = new MenuItem(Constants.CAPPUCCINO);
        cappuccino.addIngredient(inventoryManager.getItem(Constants.ESPRESSO), 2);
        cappuccino.addIngredient(inventoryManager.getItem(Constants.STEAMED_MILK), 1);
        cappuccino.addIngredient(inventoryManager.getItem(Constants.FOAMED_MILK), 1);
        menuItems.add(cappuccino);

        MenuItem coffee = new MenuItem(Constants.COFFEE);
        coffee.addIngredient(inventoryManager.getItem(Constants.COFFEE), 3);
        coffee.addIngredient(inventoryManager.getItem(Constants.SUGAR), 1);
        coffee.addIngredient(inventoryManager.getItem(Constants.CREAM), 1);
        menuItems.add(coffee);

        MenuItem decafCoffee = new MenuItem(Constants.DECAF_COFFEE);
        decafCoffee.addIngredient(inventoryManager.getItem(Constants.DECAF_COFFEE), 3);
        decafCoffee.addIngredient(inventoryManager.getItem(Constants.SUGAR), 1);
        decafCoffee.addIngredient(inventoryManager.getItem(Constants.CREAM), 1);
        menuItems.add(decafCoffee);

        Collections.sort(menuItems, new MenuItemComparator());

    }

    public boolean isItemAvailable(String itemName) {
        IMenuItem menuItem = getMenuItem(itemName);

        HashMap<IInventoryItem, Float> ingredients = menuItem.getIngredients();
        Set keys = ingredients.keySet();
        Iterator keysIterator = keys.iterator();

        while (keysIterator.hasNext()) {
            IInventoryItem iItem = (IInventoryItem) keysIterator.next();
            float quantityRequired = ingredients.get(iItem);
            boolean enough = inventoryManager.sufficientItemsOnHand(iItem.getName(), (int) quantityRequired);
            if (!enough) {
                return false;
            }
        }

        return true;
    }

    public boolean isItemAvailable(int itemNumber) {
        IMenuItem menuItem = getMenuItem(itemNumber);

        HashMap<IInventoryItem, Float> ingredients = menuItem.getIngredients();
        Set keys = ingredients.keySet();
        Iterator keysIterator = keys.iterator();

        while (keysIterator.hasNext()) {
            IInventoryItem iItem = (IInventoryItem) keysIterator.next();
            float quantityRequired = ingredients.get(iItem);
            boolean enough = inventoryManager.sufficientItemsOnHand(iItem.getName(), (int) quantityRequired);
            if (!enough) {
                return false;
            }
        }

        return true;
    }

    public void listMenu(PrintStream out) {
        if (menuItems != null && menuItems.size() > 0) {
            out.println(Constants.MENU + Constants.LINE_FEED);
            int index = 1;
            for (MenuItem mItem : menuItems) {
                out.println(mItem.toString(index, isItemAvailable(mItem.getName()))
                        + Constants.LINE_FEED);
                index++;
            }
        }
    }

    public boolean orderItem(int itemNumber) {
        IMenuItem menuItem = null;

        if (isItemAvailable(itemNumber)) {
            menuItem = getMenuItem(itemNumber);

            HashMap<IInventoryItem, Float> ingredients = menuItem.getIngredients();
            Set keys = ingredients.keySet();
            Iterator keysIterator = keys.iterator();

            while (keysIterator.hasNext()) {
                IInventoryItem iItem = (IInventoryItem) keysIterator.next();

                float quantityRequired = ingredients.get(iItem);
                inventoryManager.deductItemCount(iItem.getName(), (int) quantityRequired);
            }

            return true;
        }

        return false;
    }

    public void setInventoryManager(IInventoryManager invManager) {
        inventoryManager = invManager;
    }

    public IMenuItem getMenuItem(int itemNumber) {
        return menuItems.get(itemNumber - 1);
    }

    public IMenuItem getMenuItem(String itemName) {
        IMenuItem menuItem = null;
        for (IMenuItem mItem : menuItems) {
            if (mItem.getName().equals(itemName)) {
                menuItem = mItem;
                break;
            }
        }
        return menuItem;
    }
}
