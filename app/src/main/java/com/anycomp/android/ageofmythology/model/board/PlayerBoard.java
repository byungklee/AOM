package com.anycomp.android.ageofmythology.model.board;

import com.anycomp.android.ageofmythology.model.area.Area;
import com.anycomp.android.ageofmythology.model.area.AreaFactory;
import com.anycomp.android.ageofmythology.model.area.AreaType;
import com.anycomp.android.ageofmythology.model.culture.Culture;

public abstract class PlayerBoard {
	private Area holdingArea;
	private Area productionArea;
	private Area cityArea;
	private Culture culture;
	public PlayerBoard() {
		setHoldingArea(AreaFactory.newInstance(AreaType.HOLDING));
		setProductionArea(AreaFactory.newInstance(AreaType.PRODUCTION));
		setCityArea(AreaFactory.newInstance(AreaType.CITY));
		//this.setCulture(culture);
	}
	
	public Area getHoldingArea() {
		return holdingArea;
	}

	public void setHoldingArea(Area holdingArea) {
		this.holdingArea = holdingArea;
	}

	public Area getProductionArea() {
		return productionArea;
	}

	public void setProductionArea(Area productionArea) {
		this.productionArea = productionArea;
	}

	public Area getCityArea() {
		return cityArea;
	}

	public void setCityArea(Area cityArea) {
		this.cityArea = cityArea;
	}

	public Culture getCulture() {
		return culture;
	}

	public void setCulture(Culture culture) {
		this.culture = culture;
	}
	
	abstract void initializeTile();
	

}
