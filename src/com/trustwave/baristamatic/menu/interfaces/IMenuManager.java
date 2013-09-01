package com.trustwave.baristamatic.menu.interfaces;

import java.io.PrintStream;

import com.trustwave.baristamatic.inventory.interfaces.IInventoryManager;

public interface IMenuManager {
	
	public void setInventoryManager( IInventoryManager invManager ) ;
	
	public void buildMenu() ;
	
	public boolean orderItem( int itemNumber ) ;
	
	public boolean isItemAvailable( int itemNumber ) ;
	
	public void listMenu( PrintStream out ) ;
	
	public IMenuItem getMenuItem( int itemNumber ) ;
	
}
