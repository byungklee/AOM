package com.anycomp.android.ageofmythology.model.unit;

public class MortalUnit extends Unit {

    /** The type of mortal unit as defined by the culture of the player that owns this unit. */
    private MortalUnitType type;

    /** The R file id for the image resource to use for the unit. */
    private int imagePath;

    /**
     * Construct by type of mortal unit
     * @param type
     */
    public MortalUnit(MortalUnitType type) {
        this.type = type;
        setImagePath(type.imageID);
        setFavorCost(type.favorCost);
        setFoodCost(type.foodCost);
        setGoldCost(type.goldCost);
        setWoodCost(type.woodCost);
    }

}
