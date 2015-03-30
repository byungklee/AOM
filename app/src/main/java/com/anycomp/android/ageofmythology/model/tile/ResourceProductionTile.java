package com.anycomp.android.ageofmythology.model.tile;


import com.anycomp.android.ageofmythology.model.resource.ResourceType;

public abstract class ResourceProductionTile extends TileDecorator {
	
	private int resourceNumber;
    private ResourceType resourceType;
	public int getResourceNumber() {
		return resourceNumber;
	}
	public ResourceProductionTile(Tile basicTile) {
		super(basicTile);
		// TODO Auto-generated constructor stub
	}
	public void setResourceNumber(int resourceNumber) {
		this.resourceNumber = resourceNumber;
	}
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	public void print() {
		System.out.println(this.getName() + " " + this.getTileType() + " " + resourceType);
	}
    
}
