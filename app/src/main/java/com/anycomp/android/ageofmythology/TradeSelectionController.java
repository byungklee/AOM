package com.anycomp.android.ageofmythology;

/**
 * Created by mike on 4/5/15.
 */
public class TradeSelectionController {

    private PlayerController pc;

    /** Resource counts for player (current player). */
    private int favor1, food1, gold1, wood1;

    /** Resource counts for bank (bank). */
    private int favor2, food2, gold2, wood2;

    public TradeSelectionController(PlayerController pc) {
        this.pc = pc;

        favor1 = pc.getCurrentPlayer().getFavorCube().getValue();
        food1 = pc.getCurrentPlayer().getFoodCube().getValue();
        gold1 = pc.getCurrentPlayer().getGoldCube().getValue();
        wood1 = pc.getCurrentPlayer().getWoodCube().getValue();
    }

}
