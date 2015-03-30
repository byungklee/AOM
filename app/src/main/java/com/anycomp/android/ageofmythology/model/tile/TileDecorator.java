package com.anycomp.android.ageofmythology.model.tile;

public abstract class TileDecorator extends Tile {
	private Tile basicTile; //Holding the basic tile.
	public TileDecorator(Tile basicTile) {
		this.basicTile = basicTile;
	}
	
	@Override
	public TileType getTileType() {
		return basicTile.getTileType();
	}
	
}
