package com.anycomp.android.ageofmythology.model.tile;



public class TileFactory {
	public static int TILE_COUNTER = 0;
	public static Tile newInstance(TileType type) {
		switch(type) {
		case DESERT:
			return new DesertTile();
		case FERTILE:
			return new FertileTile();
		case HILL:
			return new HillTile();
		case MOUNTAIN:
			return new MountainTile();
		case SWAMP:
			return new SwampTile();
		case FOREST:
			return new ForestTile();
		//case BUILDING:
			//return new BuildingTile(number, rType);
			
			default:
				break;
		}
		return null;
	}
}
