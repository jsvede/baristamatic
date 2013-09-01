package com.trustwave.baristamatic.menu.interfaces;

import java.util.Comparator;
import java.util.HashMap;

import com.trustwave.baristamatic.inventory.interfaces.IInventoryItem;

public interface IMenuItem extends Comparable<Object> {
	
	public void setItemNumber( int itemNum ) ;
	
	public int getItemNumber() ;
	
	public void addIngredient( IInventoryItem item, float quantity ) ;
	
	public void setName( String name ) ;
	
	public String getName() ;
	
	public float getCost() ;
	
	public void setMessageFormat( String format ) ;
	
	public HashMap<IInventoryItem, Float> getIngredients() ;
	
	public String toString( int index, boolean available ) ;
	
}
