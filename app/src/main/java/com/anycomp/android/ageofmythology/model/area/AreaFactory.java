package com.anycomp.android.ageofmythology.model.area;

public class AreaFactory {
	public static Area newInstance(AreaType type) {
		switch(type) {
		case CITY:
			return new CityArea(16);
		case HOLDING:
			return new HoldingArea(5);
		case PRODUCTION:
			return new ProductionArea(16);
		default:
			break;
		}
		return null;
	}
}
