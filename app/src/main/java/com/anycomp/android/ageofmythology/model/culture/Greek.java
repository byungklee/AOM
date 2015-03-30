package com.anycomp.android.ageofmythology.model.culture;

import com.anycomp.android.ageofmythology.R;

public class Greek extends Culture {
	public Greek() {
		this.setImagePath(R.drawable.greekboard9x7);
        this.setPermanentAttackCardImage(R.drawable.card_perm_greek_attack);
        this.setPermanentRecruitCardImage(R.drawable.card_perm_greek_recruit);
        this.setPermanentTradeCardImage(R.drawable.card_perm_greek_trade);
        this.setPermanentNextCardImage(R.drawable.card_perm_greek_age);
        this.setPermanentGatherCardImage(R.drawable.card_perm_egypt_gather);
        this.setPermanentExploreCardImage(R.drawable.card_perm_greek_explore);
        this.setPermanentBuildCardImage(R.drawable.card_perm_greek_build);
		this.setName("Greek");
	}
}
