package com.anycomp.android.ageofmythology;

import android.util.Log;

import com.anycomp.android.ageofmythology.model.card.Card;
import com.anycomp.android.ageofmythology.model.culture.Culture;
import com.anycomp.android.ageofmythology.model.culture.Egyptian;
import com.anycomp.android.ageofmythology.model.culture.Greek;
import com.anycomp.android.ageofmythology.model.culture.Norse;
import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.unit.MortalUnit;
import com.anycomp.android.ageofmythology.model.unit.MortalUnitType;
import com.anycomp.android.ageofmythology.model.unit.Unit;

import java.util.ArrayList;

/**
 * Created by mike on 4/3/15.
 */
public class RecruitSelectionController {

    private PlayerController pc;

    // These ArrayLists should always have 3 elements. (Might put size enforcement in later)
    private static ArrayList<Unit> mortalRecruitList;
    private static ArrayList<Unit> mythicRecruitList;
    private static ArrayList<Unit> heroicRecruitList;

    private static RecruitSelectionController instance;

    private Card card;

    public static RecruitSelectionController getInstance(PlayerController pc) {
        if(instance == null) {
            instance = new RecruitSelectionController(pc);
        }

        // fill the recruit unit lists with culture-specific units
        initRecruitList(pc.getCurrentPlayer().getCulture());

        return instance;
    }

    private RecruitSelectionController(PlayerController pc) {
        this.pc = pc;
    }

    /**
     * Initialize the recruit list based on player's culture so that the proper units are
     * added to the list.
     */
    private static void initRecruitList(Culture culture) {
        // Initialize the lists
        mortalRecruitList = new ArrayList<>();
        mythicRecruitList = new ArrayList<>();
        heroicRecruitList = new ArrayList<>();

        // TODO: add appropriate units here (mythic and heroic)

        // Add units to the lists based on the current player's culture
        if (culture instanceof Egyptian) {
            // MORTAL
            mortalRecruitList.add(new MortalUnit(MortalUnitType.SPEARMAN));
            mortalRecruitList.add(new MortalUnit(MortalUnitType.ELEPHANT));
            mortalRecruitList.add(new MortalUnit(MortalUnitType.CHARIOT_ARCHER));

            //MYTHIC
            // nothing here yet

            //HEROIC
            // nothing here yet
        }
        else if (culture instanceof Greek) {
            mortalRecruitList.add(new MortalUnit(MortalUnitType.TOXOTE));
            mortalRecruitList.add(new MortalUnit(MortalUnitType.HOPLITE));
            mortalRecruitList.add(new MortalUnit(MortalUnitType.HIPPOKON));

            //MYTHIC
            // nothing here yet

            //HEROIC
            // nothing here yet
        }
        else if (culture instanceof Norse) {
            mortalRecruitList.add(new MortalUnit(MortalUnitType.JARL));
            mortalRecruitList.add(new MortalUnit(MortalUnitType.HUSKARL));
            mortalRecruitList.add(new MortalUnit(MortalUnitType.THROWING_AXEMAN));

            //MYTHIC
            // nothing here yet

            //HEROIC
            // nothing here yet
        }
    }

    public void playRecruitCard(Card card) {
        this.card = card;
    }

    /**
     * Adds the selected recruit to the player's army.
     * @param index The index of the selected recruit in the GridView Layout.
     * @return boolean indicating success.
     */
    public boolean addRecruit(int index) {
        boolean success = false;

        Player player = pc.getCurrentPlayer();
        Unit chosen = null;

        // add the unit to the player's army
        switch (index) {
            case 0:
                chosen = mortalRecruitList.get(0);
                break;
            case 1:
                chosen = mortalRecruitList.get(1);
                break;
            case 2:
                chosen = mortalRecruitList.get(2);
                break;
            case 3:
                chosen = mythicRecruitList.get(0);
                break;
            case 4:
                chosen = mythicRecruitList.get(1);
                break;
            case 5:
                chosen = mythicRecruitList.get(2);
                break;
            case 6:
                chosen = heroicRecruitList.get(0);
                break;
            case 7:
                chosen = heroicRecruitList.get(1);
                break;
            case 8:
                chosen = heroicRecruitList.get(2);
                break;
            default:
                break;

        }

        // check that the player has sufficient resources
        if ( chosen != null &&
             player.getFavorCube().getValue() >= chosen.getFavorCost() &&
             player.getFoodCube().getValue() >= chosen.getFoodCost() &&
             player.getGoldCube().getValue() >= chosen.getGoldCost() &&
             player.getWoodCube().getValue() >= chosen.getWoodCost()) {

            // deduct the cost of the unit from the player's resources
            player.getFavorCube().setValue(player.getFavorCube().getValue() - chosen.getFavorCost());
            player.getFoodCube().setValue(player.getFoodCube().getValue() - chosen.getFoodCost());
            player.getGoldCube().setValue(player.getGoldCube().getValue() - chosen.getGoldCost());
            player.getWoodCube().setValue(player.getWoodCube().getValue() - chosen.getWoodCost());

            // add the unit to the player's army
            player.getArmy().add(chosen);


            for (Unit unit : player.getArmy()) {
                Log.d("RecruitCard", unit.toString());
            }

            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<Unit> getMortalRecruitList() {
        return mortalRecruitList;
    }

    public ArrayList<Unit> getMythicRecruitList() {
        return mythicRecruitList;
    }

    public ArrayList<Unit> getHeroicRecruitList() {
        return heroicRecruitList;
    }

}
