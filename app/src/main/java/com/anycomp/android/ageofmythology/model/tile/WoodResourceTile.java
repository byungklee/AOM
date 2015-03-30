package com.anycomp.android.ageofmythology.model.tile;

import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.resource.ResourceType;

public class WoodResourceTile extends ResourceProductionTile {

	public WoodResourceTile(Tile basicTile) {
		super(basicTile);
		this.setResourceType(ResourceType.WOOD);
		switch(this.getTileType()) {
		case FOREST:
			this.setResourceNumber(2);

            this.setImagePath(R.drawable.forest2wood);
			break;
		case FERTILE:
			this.setResourceNumber(1);

            this.setImagePath(R.drawable.fertile1wood);
			break;
		case MOUNTAIN:
			this.setResourceNumber(1);
            this.setImagePath(R.drawable.mountain1wood);
			break;
		case HILL:
			this.setResourceNumber(1);
            this.setImagePath(R.drawable.hills1wood);
			break;
		case SWAMP:
			this.setResourceNumber(1);
			//this.setImagePath("assets/Textures/ColoredTex/Swamp1Wood.png");
            this.setImagePath(R.drawable.swamp1wood);
			break;
//		default:
//			throw new RuntimeException("Not vaild tile type on this tile");
		}
	}

}
