package com.anycomp.android.ageofmythology.model.card;

import com.anycomp.android.ageofmythology.model.culture.Culture;

public class CardFactory {
//	public static Card newInstance() {
//		return null;
//		
//	}
    public static Card newPermanentCard(CardType type, Culture culture) {
        switch(type) {
            case ATTACK_CARD:
                return new PermanentAttackCard(culture);
            case BUILD_CARD:
                return new PermanentBuildCard(culture);
            case EXPLORE_CARD:
                return new PermanentExploreCard(culture);
            case GATHER_CARD:
                return new PermanentGatherCard(culture);
            case RECRUIT_CARD:
                return new PermanentRecruitCard(culture);
            case TRADE_CARD:
                return new PermanentTradeCard(culture);
            case NEXT_AGE_CARD:
                return new PermanentNextAge(culture);
            default:
             
        }
        return null;
    }
    
    public static Card newRandomCard(CardType type) {
        switch(type) {
            case ATTACK_CARD:
                return new RandomAttackCard();
            case BUILD_CARD:
                return new RandomBuildCard();
            case EXPLORE_CARD:
                return new RandomExploreCard();
            case GATHER_CARD:
                return new RandomGatherCard();
            case RECRUIT_CARD:
                return new RandomRecruitCard();
            case TRADE_CARD:
                return new RandomTradeCard();
            case NEXT_AGE_CARD:
                //return new RandomNextAge();
            default:
             
        }
        return null;
    }
}
