package com.anycomp.android.ageofmythology;

import com.anycomp.android.ageofmythology.model.unit.Unit;

import java.util.ArrayList;

/**
 * Created by mike on 4/3/15.
 */
public class RecruitSelectionController {

    private PlayerController pc;
    private ArrayList<Unit> unitList;

    private static RecruitSelectionController instance;

    public static RecruitSelectionController getInstance(PlayerController pc) {
        if(instance == null) {
            instance = new RecruitSelectionController(pc);
        }
        return instance;
    }

    private RecruitSelectionController(PlayerController pc) {
        this.pc = pc;
        this.unitList = new ArrayList<>();
    }

}
