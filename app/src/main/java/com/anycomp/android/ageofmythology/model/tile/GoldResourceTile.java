package com.anycomp.android.ageofmythology.model.tile;


import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.resource.ResourceType;

public class GoldResourceTile extends ResourceProductionTile {
	public GoldResourceTile(Tile tile) {
		super(tile);
		this.setResourceType(ResourceType.GOLD);
		switch(this.getTileType()) {
		case DESERT:
			this.setResourceNumber(1);
            this.setImagePath(R.drawable.desert1gold);
			break;
		case FOREST:
			this.setResourceNumber(1);
            this.setImagePath(R.drawable.forest1gold);
			break;
		case FERTILE:
			this.setResourceNumber(1);
            this.setImagePath(R.drawable.fertile1gold);
			break;
		case MOUNTAIN:
			this.setResourceNumber(2);
            this.setImagePath(R.drawable.mountain2gold);
			break;
		case HILL:
			this.setResourceNumber(2);
            this.setImagePath(R.drawable.hills2gold);
			break;
//
//		default:
//			throw new RuntimeException("Not vaild tile type on this tile");
		}
	}
}
