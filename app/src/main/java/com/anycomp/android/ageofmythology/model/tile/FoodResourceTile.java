package com.anycomp.android.ageofmythology.model.tile;


import com.anycomp.android.ageofmythology.R;
import com.anycomp.android.ageofmythology.model.resource.ResourceType;

public class FoodResourceTile extends ResourceProductionTile {

	public FoodResourceTile(Tile basicTile) {
		super(basicTile);
		this.setResourceType(ResourceType.FOOD);
		System.out.println(getTileType());
		switch(getTileType()) {
		case FOREST:
			this.setResourceNumber(1);
            this.setImagePath(R.drawable.forest1food);
			break;
		case FERTILE:
			this.setResourceNumber(2);
            this.setImagePath(R.drawable.fertile2food);
			break;
		case SWAMP:
			this.setResourceNumber(1);
            this.setImagePath(R.drawable.swamp1favor);
            break;
		case HILL:
			this.setResourceNumber(1);
            this.setImagePath(R.drawable.hills1food);
			break;
//		default:
//			throw new RuntimeException("Not vaild tile type on this tile");
		}
	}

}
