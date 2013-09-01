package com.trustwave.baristamatic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.trustwave.baristamatic.constants.Constants;
import com.trustwave.baristamatic.inventory.impl.InventoryManager;
import com.trustwave.baristamatic.inventory.interfaces.IInventoryManager;
import com.trustwave.baristamatic.menu.impl.MenuManager;
import com.trustwave.baristamatic.menu.interfaces.IMenuItem;
import com.trustwave.baristamatic.menu.interfaces.IMenuManager;

public class Baristamatic {

	public static final Logger log = Logger.getLogger( Baristamatic.class ) ;
	
	private static IInventoryManager invManager = InventoryManager.getInstance() ;
	private static IMenuManager menuManager = MenuManager.getInstance( invManager ) ;

	public static void main( String[] args ) {
		
		InputStreamReader inStream = new InputStreamReader( System.in ) ;
		BufferedReader bufReader = new BufferedReader( inStream ) ;
		
		String cmd = null ;

		displayInventoryAndMenu() ;
		
		try {
			do {
				cmd = bufReader.readLine() ;
				if( cmd != null ) {
					int cmdInt = -1 ;
					try {
						cmdInt = Integer.parseInt( cmd ) ;
					} catch( NumberFormatException nfe ) {
						// ignore this since it only matters if the entry
						// is a number 
					}
					
					if( cmd.toLowerCase().equals( "r" ) ) {
						restockInventory() ;
						displayInventoryAndMenu() ;
					} else if( cmd.toLowerCase().equals( "q" ) ) {
						System.exit( 0 ) ;
					} else if( cmdInt > 0 && cmdInt < 7 ) {
						orderDrink( cmdInt ) ;
						displayInventoryAndMenu() ;
					} else if( cmd != null && cmd.length() == 0 ) {
						// ignore the blank entries
					} else {
						System.out.println( Constants.INVALID_SELECTION + cmd ) ; 
					}			
				}
			} while( true ) ;			
		} catch( IOException ioe ) {
			// fail quitely
			log.error( ioe.getMessage(), ioe ) ;
		}
		
		System.exit( 0 ) ;
		
	}
	
	public static void displayInventoryAndMenu() {
		displayInventory() ;
		displayMenu() ;
	}
	
	public static void displayMenu() {
		menuManager.listMenu( System.out ) ;
	}
	
	public static void displayInventory() {
		invManager.listInventory( System.out ) ;
	}
	
	public static void orderDrink( int command ) {
		IMenuItem menuItem = menuManager.getMenuItem( command ) ;
		
		if( menuManager.orderItem( command ) ) {
			System.out.println( Constants.DISPENSING + menuItem.getName() + Constants.LINE_FEED ) ;
		} else {
			System.out.println(  Constants.OUT_OF_STOCK + menuItem.getName() + Constants.LINE_FEED ) ;
		}			
	}
	
	public static void restockInventory() {
		invManager.restock() ;
	}

}
