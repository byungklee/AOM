package com.anycomp.android.ageofmythology.model.building;

/**
 * Factory Pattern for Building
 *
 */
public class BuildingFactory {
	
		public static Building newInstance(BuildingType type) {
			
			switch(type) {
			case HOUSE:
                            return new HouseBuilding();
			case WALL:
                            return new WallBuilding();
			case TOWER:
                            return new TowerBuilding();
			case STOREHOUSE:
                            return new StoreHouseBuilding();
			case ARMORY:
                            return new ArmoryBuilding();
			case MARKET:
                            return new MarketBuilding();
			case QUARRY:
                            return new QuarryBuilding();
			case MONUMENT:
                            return new MonumentBuilding();
			case GRANARY:
                            return new GranaryBuilding();
			case GOLD_MINT:
                            return new GoldMintBuilding();
			case WOOD_WORKSHOP:
                            return new WoodWorkshopBuilding();
			case SIEGE_ENGINE_WORKSHOP:
                            return new SiegeEngineWorkshopBuilding();
			case GREAT_TEMPLE:
                            return new GreatTempleBuilding();
			case THE_WONDER:
                            return new TheWonderBuilding();
			default:
                            break;
				
			}
			return null;
		}
}