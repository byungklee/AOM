package com.anycomp.android.ageofmythology.model.tile;


public class MountainTile extends Tile {
	public static TileType type = TileType.MOUNTAIN;
	public MountainTile() {
		this.setTileType(TileType.MOUNTAIN);
//		this.setResourceType(type);
//		switch(type) {
//		case GOLD:
//			this.setResourceNumber(2);
//			this.setImagePath("assets/Textures/ColoredTex/Mountain2Gold.png");
//			break;
//		case FAVOR:
//			this.setResourceNumber(1);
//			this.setImagePath("assets/Textures/ColoredTex/Mountain1Favor.png");
//			break;
//		case WOOD:
//			this.setResourceNumber(1);
//			this.setImagePath("assets/Textures/ColoredTex/Mountain1Wood.png");
//			break;
//		default:
//			throw new RuntimeException("Not vaild resource type on this tile");
//		}
	}
}
