package com.anycomp.android.ageofmythology.model.culture;

import com.anycomp.android.ageofmythology.R;

public class Norse extends Culture {
	public Norse() {
		this.setImagePath(R.drawable.norseboard9x7);
        this.setPermanentAttackCardImage(R.drawable.card_perm_norse_attack);
        this.setPermanentRecruitCardImage(R.drawable.card_perm_norse_recruit);
        this.setPermanentTradeCardImage(R.drawable.card_perm_norse_trade);
        this.setPermanentNextCardImage(R.drawable.card_perm_norse_age);
        this.setPermanentGatherCardImage(R.drawable.card_perm_norse_gather);
        this.setPermanentExploreCardImage(R.drawable.card_perm_norse_explore);
        this.setPermanentBuildCardImage(R.drawable.card_perm_norse_build);
		this.setName("Norse");
	}
}
