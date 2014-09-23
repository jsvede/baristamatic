package com.trustwave.baristamatic.inventory.interfaces;

import java.io.PrintStream;

public interface IInventoryManager {
	
	public void initialize() ;
	
	public boolean itemExists( String itemName ) ;
	
	public boolean sufficientItemsOnHand( String itemName, int amountNeeded ) ;
	
	public void deductItemCount( String itemName, int amountDeducted ) ;
	
	public IInventoryItem getItem( String name ) ;
	
	public void restock() ;
	
	public void listInventory( PrintStream out ) ;
	
	public void setInventoryTemplate( String template ) ;
}
