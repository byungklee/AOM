package com.anycomp.android.ageofmythology.model.area;

import com.anycomp.android.ageofmythology.Observable;
import com.anycomp.android.ageofmythology.Observer;
import com.anycomp.android.ageofmythology.model.tile.ResourceProductionTile;
import com.anycomp.android.ageofmythology.model.tile.Tile;

import java.util.ArrayList;


public abstract class  Area implements Observable {
	protected ArrayList<Tile> tiles;
	private int maxSize;
	protected ArrayList<Observer> observers;
	public Area(int maxSize) {
		this.maxSize = maxSize;
		//tiles = new Tile[maxSize];
		tiles = new ArrayList<Tile>();
		observers = new ArrayList<Observer>();
	}
	
	public int getMaxSize() {
		return maxSize;
	}
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	@Override
	public void attachObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.add(o);
		
	}
	@Override
	public void detachObserver(Observer o) {
		// TODO Auto-generated method stub
		observers.remove(o);
	}
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for(Observer obs : observers) {
			obs.update(this);
		}
	}
	
	public Tile getTileAt(int index) {
		return tiles.get(index);
	}
	public void addTile(Tile tile) {
		this.getTiles().add(tile);
		notifyObservers();
	}
	public int getTileSize() {
		return tiles.size();
	}
	public void clearTiles() {
		tiles.clear();
	}
	public void setTileAt(int index, Tile tile) {
		tiles.set(index, tile);
		notifyObservers();
	}
	public void printTile() {
		for(Tile tile:tiles) {
			//if(tile instanceof TileDecorator)
				
			//System.out.print(tile.getTileType() +" ");
			tile.print();
			
		}
		System.out.println();
	}
	public Tile getFirstAvailableTile() {
		for(Tile tile:tiles) {
			if(!(tile instanceof ResourceProductionTile)) {
				return tile;
			}
		}
		return null;
	}

//	public Tile[] setTiles() {
//		
//	}
}
