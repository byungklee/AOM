package com.anycomp.android.ageofmythology.model.board;

import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.tile.TileFactory;
import com.anycomp.android.ageofmythology.model.tile.TileType;

public class EgyptBoard extends PlayerBoard {
		public EgyptBoard(Culture culture) {
			this.setCulture(culture);
			this.initializeTile();
		}

		@Override
		void initializeTile() {
			// TODO Auto-generated method stub
			this.getProductionArea().clearTiles();
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.DESERT));
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.DESERT));
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.SWAMP));
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.SWAMP));
			
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.FOREST));
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.DESERT));
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.FERTILE));
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.FERTILE));
			
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.DESERT));
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.DESERT));
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.FERTILE));
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.FERTILE));
			
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.DESERT));
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.HILL));
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.FERTILE));
			this.getProductionArea().addTile(TileFactory.newInstance(TileType.HILL));
		}
		
}
