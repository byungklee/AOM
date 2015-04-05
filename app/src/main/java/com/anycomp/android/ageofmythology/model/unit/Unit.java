package com.anycomp.android.ageofmythology.model.unit;

public abstract class Unit {

	private String name;
	private int imagePath;

    private int favorCost;
    private int foodCost;
    private int goldCost;
    private int woodCost;

    public Unit() { }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public int getFavorCost() { return favorCost; }

    public void setFavorCost(int favorCost) {
        this.favorCost = favorCost;
    }

    public int getFoodCost() {
        return foodCost;
    }

    public void setFoodCost(int foodCost) {
        this.foodCost = foodCost;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public void setGoldCost(int goldCost) {
        this.goldCost = goldCost;
    }

    public int getWoodCost() {
        return woodCost;
    }

    public void setWoodCost(int woodCost) {
        this.woodCost = woodCost;
    }

    public String toString() {
        String str = "";
        str += name + ":\n";
        str += "  favorCost = " + favorCost + "\n";
        str += "   foodCost = " + foodCost + "\n";
        str += "   goldCost = " + goldCost + "\n";
        str += "   woodCost = " + woodCost + "\n";
        return str;
    }
}
