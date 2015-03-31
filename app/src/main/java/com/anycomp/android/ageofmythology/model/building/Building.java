package com.anycomp.android.ageofmythology.model.building;

/**
 * Abstract class for building. 
 * It includes basic functionality of buildings and fields.
 * 
 */
public abstract class Building {
	private String mNameOfBuilding;
	private int image;
    private int woodCost;
    private int foodCost;
    private int favorCost;
    private int goldCost;
        public Building() {
            
        }
        
        public int getImagePath() {
            return image;
        }
        public void setImagePath(int path) {
            this.image = path;
        }
        public String getBuildingName() {
            return mNameOfBuilding;
        }
        public void setBuildingName(String name) {
            this.mNameOfBuilding =name;
        }

    public int getWoodCost() {
        return woodCost;
    }

    protected void setWoodCost(int woodCost) {
        this.woodCost = woodCost;
    }

    public int getFoodCost() {
        return foodCost;
    }

    protected void setFoodCost(int foodCost) {
        this.foodCost = foodCost;
    }

    public int getFavorCost() {
        return favorCost;
    }

    protected void setFavorCost(int favorCost) {
        this.favorCost = favorCost;
    }

    public int getGoldCost() {
        return goldCost;
    }

    protected void setGoldCost(int goldCost) {
        this.goldCost = goldCost;
    }
}
