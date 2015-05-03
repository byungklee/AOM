package com.anycomp.android.ageofmythology;

import android.util.Log;

import com.anycomp.android.ageofmythology.model.card.Card;
import com.anycomp.android.ageofmythology.model.player.Player;
import com.anycomp.android.ageofmythology.model.unit.Unit;
import com.anycomp.android.ageofmythology.model.unit.specific.Anubite;
import com.anycomp.android.ageofmythology.model.unit.specific.Centaur;
import com.anycomp.android.ageofmythology.model.unit.specific.ChariotArcher;
import com.anycomp.android.ageofmythology.model.unit.specific.ClassicalGreekHero;
import com.anycomp.android.ageofmythology.model.unit.specific.ClassicalNorseHero;
import com.anycomp.android.ageofmythology.model.unit.specific.Cyclops;
import com.anycomp.android.ageofmythology.model.unit.specific.Dwarf;
import com.anycomp.android.ageofmythology.model.unit.specific.Elephant;
import com.anycomp.android.ageofmythology.model.unit.specific.FrostGiant;
import com.anycomp.android.ageofmythology.model.unit.specific.HeroicGreekHero;
import com.anycomp.android.ageofmythology.model.unit.specific.HeroicNorseHero;
import com.anycomp.android.ageofmythology.model.unit.specific.Hippokon;
import com.anycomp.android.ageofmythology.model.unit.specific.Hoplite;
import com.anycomp.android.ageofmythology.model.unit.specific.Huskarl;
import com.anycomp.android.ageofmythology.model.unit.specific.Hydra;
import com.anycomp.android.ageofmythology.model.unit.specific.Jarl;
import com.anycomp.android.ageofmythology.model.unit.specific.Manticore;
import com.anycomp.android.ageofmythology.model.unit.specific.Medusa;
import com.anycomp.android.ageofmythology.model.unit.specific.Minotaur;
import com.anycomp.android.ageofmythology.model.unit.specific.Mummy;
import com.anycomp.android.ageofmythology.model.unit.specific.MythicGreekHero;
import com.anycomp.android.ageofmythology.model.unit.specific.MythicNorseHero;
import com.anycomp.android.ageofmythology.model.unit.specific.NidHogg;
import com.anycomp.android.ageofmythology.model.unit.specific.Pharaoh;
import com.anycomp.android.ageofmythology.model.unit.specific.Phoenix;
import com.anycomp.android.ageofmythology.model.unit.specific.Priest;
import com.anycomp.android.ageofmythology.model.unit.specific.ScorpionMan;
import com.anycomp.android.ageofmythology.model.unit.specific.SonOfOsiris;
import com.anycomp.android.ageofmythology.model.unit.specific.Spearman;
import com.anycomp.android.ageofmythology.model.unit.specific.Sphinx;
import com.anycomp.android.ageofmythology.model.unit.specific.ThrowingAxeman;
import com.anycomp.android.ageofmythology.model.unit.specific.Toxotes;
import com.anycomp.android.ageofmythology.model.unit.specific.Troll;
import com.anycomp.android.ageofmythology.model.unit.specific.Valkyrie;
import com.anycomp.android.ageofmythology.model.unit.specific.Wadjet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by mike on 4/3/15.
 */
public class RecruitSelectionController {

    private PlayerController pc;

    // These ArrayLists should always have 3 elements. (Might put size enforcement in later)
//    private static ArrayList<Unit> mortalRecruitList;
//    private static ArrayList<Unit> mythicRecruitList;
//    private static ArrayList<Unit> heroicRecruitList;
    private HashMap<String, ArrayList<Unit>> cultureUnitMap;
    private ArrayList<Unit> norseRecruitList;
    private ArrayList<Unit> egyptRecruitList;
    private ArrayList<Unit> greekRecruitList;
    private int counter = 0;

    private static RecruitSelectionController instance;

    private Card card;

    private RecruitSelectionController(PlayerController pc) {
        this.pc = pc;
        egyptRecruitList = new ArrayList<>();
        greekRecruitList = new ArrayList<>();
        norseRecruitList = new ArrayList<>();
        // fill the recruit unit lists with culture-specific units
        initRecruitList();
        cultureUnitMap = new HashMap<>();
        cultureUnitMap.put("Norse", norseRecruitList);
        cultureUnitMap.put("Greek", greekRecruitList);
        cultureUnitMap.put("Egypt", egyptRecruitList);
    }

    public static RecruitSelectionController getInstance(PlayerController pc) {
        if(instance == null) {
            instance = new RecruitSelectionController(pc);
        }
        instance.setPlayerController(pc);
        return instance;
    }

    //Temporary compartor
    private Comparator<Unit> comp = new Comparator<Unit>() {
        @Override
        public int compare(Unit lhs, Unit rhs) {
            if(lhs.getAge() > rhs.getAge()) {
                return 1;
            } else if(lhs.getAge() == rhs.getAge()) {
                return 0;
            } else
                return -1;
        }
    };
    public void setPlayerController(PlayerController pc) {
        this.pc = pc;
    }

    public void initRecruitList() {
        initGreekRecruitList();
        initNorseRecruitList();
        initEgyptRecruitList();
    }

    public void initNorseRecruitList() {
        //norseRecruitList.
        norseRecruitList.add(new Troll());
        norseRecruitList.add(new Valkyrie());
        norseRecruitList.add(new NidHogg());
        norseRecruitList.add(new Dwarf());
        norseRecruitList.add(new FrostGiant());
        norseRecruitList.add(new Jarl());
        norseRecruitList.add(new Huskarl());
        norseRecruitList.add(new ThrowingAxeman());
        norseRecruitList.add(new ClassicalNorseHero());
        norseRecruitList.add(new HeroicNorseHero());
        norseRecruitList.add(new MythicNorseHero());

    }

    public void initGreekRecruitList() {
        greekRecruitList.add(new Cyclops());
        greekRecruitList.add(new Manticore());
        greekRecruitList.add(new Centaur());
        greekRecruitList.add(new Hydra());
        greekRecruitList.add(new Minotaur());

        greekRecruitList.add(new Medusa());
        greekRecruitList.add(new Toxotes());
        greekRecruitList.add(new Hippokon());
        greekRecruitList.add(new Hoplite());
        greekRecruitList.add(new ClassicalGreekHero());

        greekRecruitList.add(new HeroicGreekHero());
        greekRecruitList.add(new MythicGreekHero());
    }


    public void initEgyptRecruitList() {
        egyptRecruitList.add(new Wadjet());
        egyptRecruitList.add(new Phoenix());
        egyptRecruitList.add(new Mummy());
        egyptRecruitList.add(new Anubite());
        egyptRecruitList.add(new Sphinx());
        egyptRecruitList.add(new ScorpionMan());
        egyptRecruitList.add(new Spearman());
        egyptRecruitList.add(new Elephant());
        egyptRecruitList.add(new ChariotArcher());
        egyptRecruitList.add(new Priest());
        egyptRecruitList.add(new SonOfOsiris());
        egyptRecruitList.add(new Pharaoh());
    }


    /**
     * Initialize the recruit list based on player's culture so that the proper units are
     * added to the list.
     */
//    private static void initRecruitList(Culture culture) {
//        // Initialize the lists
//        mortalRecruitList = new ArrayList<>();
//        mythicRecruitList = new ArrayList<>();
//        heroicRecruitList = new ArrayList<>();
//
//        // TODO: add appropriate units here (mythic and heroic)
//
//        // Add units to the lists based on the current player's culture
//        if (culture instanceof Egyptian) {
//            // MORTAL
//            mortalRecruitList.add(new MortalUnit(MortalUnitType.SPEARMAN));
//            mortalRecruitList.add(new MortalUnit(MortalUnitType.ELEPHANT));
//            mortalRecruitList.add(new MortalUnit(MortalUnitType.CHARIOT_ARCHER));
//
//            //MYTHIC
//            // nothing here yet
//
//            //HEROIC
//            // nothing here yet
//        }
//        else if (culture instanceof Greek) {
//            mortalRecruitList.add(new MortalUnit(MortalUnitType.TOXOTE));
//            mortalRecruitList.add(new MortalUnit(MortalUnitType.HOPLITE));
//            mortalRecruitList.add(new MortalUnit(MortalUnitType.HIPPOKON));
//
//            //MYTHIC
//            // nothing here yet
//
//            //HEROIC
//            // nothing here yet
//        }
//        else if (culture instanceof Norse) {
//            mortalRecruitList.add(new MortalUnit(MortalUnitType.JARL));
//            mortalRecruitList.add(new MortalUnit(MortalUnitType.HUSKARL));
//            mortalRecruitList.add(new MortalUnit(MortalUnitType.THROWING_AXEMAN));
//
//            //MYTHIC
//            // nothing here yet
//
//            //HEROIC
//            // nothing here yet
//        }
//    }
//
    public void setRecruitCard(Card card) {
        this.card = card;
    }
//
    /**
     * Adds the selected recruit to the player's army.
     * @param chosen The Unit that was selected in the GridView Layout.
     * @return boolean indicating success.
     */
    public boolean addRecruit(Unit chosen) {

        Player player = pc.getCurrentPlayer();

        // add the unit to the player's army
        if(counter >= card.getValue()) {
            return false;
        }
        if(chosen == null)
            System.out.println("Unit chosen is null " + pc.getCurrentPlayerID());
        // check that the player has sufficient resources
        if ( chosen != null &&
             player.getFavorCube().getValue() >= chosen.getFavorCost() &&
             player.getFoodCube().getValue() >= chosen.getFoodCost() &&
             player.getGoldCube().getValue() >= chosen.getGoldCost() &&
             player.getWoodCube().getValue() >= chosen.getWoodCost() &&
                player.getAge().getOrder() >= chosen.getAge()) {

            // deduct the cost of the unit from the player's resources
            player.spendFavor(chosen.getFavorCost());
            player.spendFood(chosen.getFoodCost());
            player.spendGold(chosen.getGoldCost());
            player.spendWood(chosen.getWoodCost());

            // add the unit to the player's army
            player.getArmy().add(chosen);

            for (Unit unit : player.getArmy()) {
                Log.d("RecruitCard", unit.toString());
            }
            counter++;
            return true;
        }
        else {
            return false;
        }
    }

    public ArrayList<Unit> getRecruitListByCulture(String culture) {
        return cultureUnitMap.get(culture);
    }
}
