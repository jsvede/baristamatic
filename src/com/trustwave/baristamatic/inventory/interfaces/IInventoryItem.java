package com.trustwave.baristamatic.inventory.interfaces;

public interface IInventoryItem {
	
	public String getName() ;
	public void setName( String name ) ;
	
	public float getCost() ;
	public void setCost( float cost ) ;

	public int getInventory() ;
	public void setInventory( int inventory ) ;
	
}
