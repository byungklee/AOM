package com.anycomp.android.ageofmythology.model.unit;

public abstract class Unit {

	private String name;
    private String culture;
	private int imagePath;
    private int age = 1;
    private boolean doesNegateWallAndTower = false;

    private int favorCost;
    private int foodCost;
    private int goldCost;
    private int woodCost;

    //VS Effect
    private int vsMortal;
    private int vsGiantKiller;
    private int vsArchers;
    private int vsFlyers;
    private int vsWarriors;
    private int vsCavarlys;
    private int vsHeroes;
    private int vsGiant;
    private int vsMyth;

    //TO DO: just putting it as String for temporarily.
    private String specialEffect;

    private int dice;

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

    public void setCost(int favorCost, int foodCost, int goldCost, int woodCost) {
        this.favorCost = favorCost;
        this.foodCost = foodCost;
        this.goldCost = goldCost;
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

    public int getVsMortal() {
        return vsMortal;
    }

    public void setVsMortal(int vsMortal) {
        this.vsMortal = vsMortal;
    }

    public int getVsGiantKiller() {
        return vsGiantKiller;
    }

    public void setVsGiantKiller(int vsGiantKiller) {
        this.vsGiantKiller = vsGiantKiller;
    }

    public int getVsArchers() {
        return vsArchers;
    }

    public void setVsArchers(int vsArchers) {
        this.vsArchers = vsArchers;
    }

    public int getVsFlyers() {
        return vsFlyers;
    }

    public void setVsFlyers(int vsFlyers) {
        this.vsFlyers = vsFlyers;
    }

    public int getVsWarriors() {
        return vsWarriors;
    }

    public void setVsWarriors(int vsWarriors) {
        this.vsWarriors = vsWarriors;
    }

    public int getVsCavarlys() {
        return vsCavarlys;
    }

    public void setVsCavarlys(int vsCavarlys) {
        this.vsCavarlys = vsCavarlys;
    }

    public int getVsHeroes() {
        return vsHeroes;
    }

    public void setVsHeroes(int vsHeroes) {
        this.vsHeroes = vsHeroes;
    }

    public int getVsGiant() {
        return vsGiant;
    }

    public void setVsGiant(int vsGiant) {
        this.vsGiant = vsGiant;
    }

    public int getVsMyth() {
        return vsMyth;
    }

    public void setVsMyth(int vsMyth) {
        this.vsMyth = vsMyth;
    }

    public String getSpecialEffect() {
        return specialEffect;
    }

    public void setSpecialEffect(String specialEffect) {
        this.specialEffect = specialEffect;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public boolean isDoesNegateWallAndTower() {
        return doesNegateWallAndTower;
    }

    public void setDoesNegateWallAndTower(boolean doesNegateWallAndTower) {
        this.doesNegateWallAndTower = doesNegateWallAndTower;
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAdditionalDice(Unit unit) {
        int sum =0;
        if(unit instanceof Mortal) {
            sum += getVsMortal();
        }
        if(unit instanceof Myth) {
            sum += getVsMyth();
        }
        if(unit instanceof Hero) {
            sum += getVsHeroes();
        }
        if(unit instanceof Warrior) {
            sum += getVsWarriors();
        }
        if(unit instanceof Archer) {
            sum += getVsArchers();
        }
        if(unit instanceof Flyer) {
            sum += getVsFlyers();
        }
        if(unit instanceof Cavalry) {
            sum += getVsCavarlys();
        }
        if(unit instanceof Giant) {
            sum += getVsGiant();
        }
        if(unit instanceof GiantKiller) {
            sum += getVsGiantKiller();
        }

        return sum;
    }

}
