package data;

import java.util.ArrayList;
import java.util.List;

import Trilemma.DUNGEON;
import Trilemma.TOWN;

public class Const_PLACE {

	private static final DUNGEON NO1_BASEMENT = new DUNGEON((long) 1, "地下室");
	private static final DUNGEON NO2_GREEN_FIELD = new DUNGEON((long) 2, "草原");
	private static final DUNGEON NO3_CAVE = new DUNGEON((long) 3, "近場の洞窟");
	private static final DUNGEON NO4_ROCK_MOUNTAIN = new DUNGEON((long) 4, "荒れた岩山");
	private static final DUNGEON NO5_SEA = new DUNGEON((long) 5, "暗い海");
	private static final DUNGEON NO6_CASTLE = new DUNGEON((long) 6, "怪しい城");

	private static final TOWN BAMBOO_TOWN = new TOWN((long) 1, "バンブータウン");
	private static final TOWN FLAG_TOWN = new TOWN((long) 2, "フラッグタウン");
	private static final TOWN NEWS_TOWN = new TOWN((long) 3, "ニュースタウン");
	private static final TOWN BIG_INN_TOWN = new TOWN((long) 4, "ビッグインタウン");

	public static List<DUNGEON> getAllDungeon() {
		List<DUNGEON> result = new ArrayList<DUNGEON>();

		// TODO 抜け漏れなく全て取得できるようにしたい
		result.add(getNo1Basement());
		result.add(getNo2GreenField());
		result.add(getNo3Cave());
		result.add(getNo4RockMountain());
		result.add(getNo5Sea());
		result.add(getNo6Castle());

		return result;
	}

	public static List<TOWN> getAllTown() {
		List<TOWN> result = new ArrayList<TOWN>();

		// TODO 抜け漏れなく全て取得できるようにしたい
		result.add(getBambooTown());
		result.add(getFlagTown());
		result.add(getNewsTown());
		result.add(getBigInnTown());

		return result;
	}

	public static DUNGEON getNo1Basement() {
		return NO1_BASEMENT;
	}

	public static DUNGEON getNo2GreenField() {
		return NO2_GREEN_FIELD;
	}

	public static DUNGEON getNo3Cave() {
		return NO3_CAVE;
	}

	public static DUNGEON getNo4RockMountain() {
		return NO4_ROCK_MOUNTAIN;
	}

	public static DUNGEON getNo5Sea() {
		return NO5_SEA;
	}

	public static DUNGEON getNo6Castle() {
		return NO6_CASTLE;
	}

	public static TOWN getBambooTown() {
		return BAMBOO_TOWN;
	}

	public static TOWN getFlagTown() {
		return FLAG_TOWN;
	}

	public static TOWN getNewsTown() {
		return NEWS_TOWN;
	}

	public static TOWN getBigInnTown() {
		return BIG_INN_TOWN;
	}

}
