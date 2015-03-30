package com.anycomp.android.ageofmythology.model.board;

import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.tile.TileFactory;
import com.anycomp.android.ageofmythology.model.tile.TileType;

public class GreekBoard extends PlayerBoard {
	public GreekBoard(Culture culture) {
		super();
		this.setCulture(culture);
		
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.FERTILE));
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.FERTILE));
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.FOREST));
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.SWAMP));
		
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.HILL));
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.MOUNTAIN));
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.FERTILE));
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.FOREST));
		
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.HILL));
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.HILL));
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.HILL));
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.HILL));
		
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.DESERT));
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.HILL));
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.HILL));
		this.getProductionArea().addTile(TileFactory.newInstance(TileType.HILL));
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	void initializeTile() {
		// TODO Auto-generated method stub
		
	}
}
