package com.anycomp.android.ageofmythology.model.tile;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.resource.ResourceType;

public class FavorResourceTile extends ResourceProductionTile {

	public FavorResourceTile(Tile basicTile) {
		super(basicTile);
		this.setResourceType(ResourceType.FAVOR);
		switch(getTileType()) {
		case DESERT:
			this.setResourceNumber(2);

            this.setImagePath(R.drawable.desert2favor);
			break;
		case FOREST:
			this.setResourceNumber(1);
            this.setImagePath(R.drawable.forest1favor);
			break;
		case FERTILE:
			this.setResourceNumber(1);
            this.setImagePath(R.drawable.forest1favor);
			break;
		case MOUNTAIN:
			this.setResourceNumber(1);
            this.setImagePath(R.drawable.mountain1favor);
			break;
		case HILL:
			this.setResourceNumber(1);
            this.setImagePath(R.drawable.hills1favor);
			break;
		case SWAMP:
			this.setResourceNumber(1);
            this.setImagePath(R.drawable.swamp1favor);
			break;

//		default:
//			throw new RuntimeException("Not vaild tile type on this tile");
		}
		// TODO Auto-generated constructor stub
	}

}
