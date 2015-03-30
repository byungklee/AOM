package com.anycomp.android.ageofmythology.model.culture;

import com.anycomp.android.ageofmythology.R;

public class Egyptian extends Culture {
	public Egyptian() {
		this.setImagePath(R.drawable.egyptboard9x7);
        this.setPermanentAttackCardImage(R.drawable.card_perm_egypt_attack);
        this.setPermanentBuildCardImage(R.drawable.card_perm_egypt_build);
        this.setPermanentExploreCardImage(R.drawable.card_perm_egypt_explore);
        this.setPermanentGatherCardImage(R.drawable.card_perm_egypt_gather);
        this.setPermanentNextCardImage(R.drawable.card_perm_egypt_age);
        this.setPermanentRecruitCardImage(R.drawable.card_perm_egypt_recruit);
        this.setPermanentTradeCardImage(R.drawable.card_perm_egypt_trade);
		this.setName("Egypt");
	}
}
