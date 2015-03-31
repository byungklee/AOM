package com.anycomp.android.ageofmythology;


import com.anycomp.android.ageofmythology.model.tile.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class TileManager implements Observable {
	//Tiles that are in selection
	private static TileManager tileManager;
	private ArrayList<Tile> tileSelectionDeck;
	//Total Tile Deck;
	private LinkedList<Tile> tileDeck;
	private ArrayList<Observer> observers;
    private int numberOfCardsToRefresh;
	
	public static TileManager getInstance() {
		if(tileManager == null) {
			tileManager = new TileManager();
		}
		return tileManager;
	}
	
	private TileManager() {
		setTileSelectionDeck(new ArrayList<Tile>());
		tileDeck = new LinkedList<Tile>();
		observers = new ArrayList<Observer>();
		generateResourceTiles();
		shuffleResourceTiles();

	}
	
	public void resetDeck() {
		tileSelectionDeck.clear();
		tileDeck.clear();
		generateResourceTiles();
		shuffleResourceTiles();
	}
	
	public void refreshTileSelectionDeck() {
		backToTileDeck();
		setTileSelectionDeck();
		notifyObservers();
	}
	
	private void setTileSelectionDeck() {
		for(int i=0;i<numberOfCardsToRefresh;i++) {
			tileSelectionDeck.add(tileDeck.removeFirst());
		}
	}
	
	private void backToTileDeck() {
		while(getTileSelectionDeck().size() > 0) {
			tileDeck.addLast(getTileSelectionDeck().remove(getTileSelectionDeck().size()-1));
		}
	}
	
	private void shuffleResourceTiles() {
		Collections.shuffle(tileDeck);
	}
	
	private void generateResourceTiles() {
		generateFertileTiles();
		generateForestTiles();
		generateHillTiles();
		generateMountainTiles();
		generateDesertTiles();
		generateSwampTiles();
	}
	
	private void generateFertileTiles() {
		for(int i=0;i<12;i++) {
			//tileDeck.add(TileFactory.newInstance(TileType.FERTILE, ResourceType.FOOD));
			tileDeck.add(new FoodResourceTile(TileFactory.newInstance(TileType.FERTILE)));
		}
		for(int i=0;i<3;i++) {
			tileDeck.add(new GoldResourceTile(TileFactory.newInstance(TileType.FERTILE)));
		}
		for(int i=0;i<3;i++) {
			tileDeck.add(new WoodResourceTile(TileFactory.newInstance(TileType.FERTILE)));
		}
		for(int i=0;i<3;i++) {
			tileDeck.add(new FavorResourceTile(TileFactory.newInstance(TileType.FERTILE)));
		}
	}
	private void generateForestTiles() {
		for(int i=0;i<2;i++) {
			tileDeck.add(new FoodResourceTile(TileFactory.newInstance(TileType.FOREST)));
		}
		for(int i=0;i<2;i++) {
			tileDeck.add(new GoldResourceTile(TileFactory.newInstance(TileType.FOREST)));
		}
		for(int i=0;i<9;i++) {
			tileDeck.add(new WoodResourceTile(TileFactory.newInstance(TileType.FOREST)));
		}
		for(int i=0;i<2;i++) {
			tileDeck.add(new FavorResourceTile(TileFactory.newInstance(TileType.FOREST)));
		}
	}
	private void generateHillTiles() {
		for(int i=0;i<4;i++) {
			tileDeck.add(new FoodResourceTile(TileFactory.newInstance(TileType.HILL)));
		}
		for(int i=0;i<4;i++) {
			tileDeck.add(new GoldResourceTile(TileFactory.newInstance(TileType.HILL)));
		}
		for(int i=0;i<4;i++) {
			tileDeck.add(new WoodResourceTile(TileFactory.newInstance(TileType.HILL)));
		}
		for(int i=0;i<4;i++) {
			tileDeck.add(new FavorResourceTile(TileFactory.newInstance(TileType.HILL)));
		}
	}
	private void generateMountainTiles() {

		for(int i=0;i<6;i++) {
			tileDeck.add(new GoldResourceTile(TileFactory.newInstance(TileType.MOUNTAIN)));
		}
		for(int i=0;i<3;i++) {
			tileDeck.add(new WoodResourceTile(TileFactory.newInstance(TileType.MOUNTAIN)));
		}
		for(int i=0;i<3;i++) {
			tileDeck.add(new FavorResourceTile(TileFactory.newInstance(TileType.MOUNTAIN)));
		}
	}
	private void generateDesertTiles() {
		for(int i=0;i<7;i++) {
			tileDeck.add(new GoldResourceTile(TileFactory.newInstance(TileType.DESERT)));
		}
		for(int i=0;i<7;i++) {
			tileDeck.add(new FavorResourceTile(TileFactory.newInstance(TileType.DESERT)));
		}
	}
	private void generateSwampTiles() {
		for(int i=0;i<4;i++) {
			tileDeck.add(new FoodResourceTile(TileFactory.newInstance(TileType.SWAMP)));
		}
		for(int i=0;i<4;i++) {
			tileDeck.add(new WoodResourceTile(TileFactory.newInstance(TileType.SWAMP)));
		}
		for(int i=0;i<4;i++) {
			tileDeck.add(new FavorResourceTile(TileFactory.newInstance(TileType.SWAMP)));
		}
	}
	public ArrayList<Tile> getTileSelectionDeck() {
		return tileSelectionDeck;
	}
	public void setTileSelectionDeck(ArrayList<Tile> tileSelectionDeck) {
		this.tileSelectionDeck = tileSelectionDeck;
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
		for(Observer o : observers) {
			o.update(this);
		}
	}
	
	public Tile takeTile(String name) {
		for(int i=0;i<tileSelectionDeck.size();i++) {
			if(tileSelectionDeck.get(i).getName().equals(name)) {
				Tile tile = tileSelectionDeck.remove(i);
				notifyObservers();
				return tile;
			}
		}
		return null;
	}
	
	public Tile getFirstTileFromTileSelectionDeck() {
		return tileSelectionDeck.get(0);
	}
	
	public void removeTileFromTileSelectionDeck(Tile tile) {
		tileSelectionDeck.remove(tile);
		notifyObservers();
	}
	
	public Tile getTileByNameFromTileSelectionDeck(String name) {
		for(Tile tile:tileSelectionDeck) {
			if(tile.getName().equals(name)){
				return tile;
			}
		}
		return null;
	}
	
	public Tile getFirstAvailableTileByTileType(TileType type) {
		for(Tile tile:tileSelectionDeck) {
			if(tile.getTileType() == type) {
				return tile;
			}
		}
		return null;
	}

    public int getNumberOfCardsToRefresh() {
        return numberOfCardsToRefresh;
    }

    public void setNumberOfCardsToRefresh(int numberOfCardsToRefresh) {
        this.numberOfCardsToRefresh = numberOfCardsToRefresh;
    }
}
