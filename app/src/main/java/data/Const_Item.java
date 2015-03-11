package data;

import java.util.ArrayList;
import java.util.List;

import Trilemma.ARMOR;
import Trilemma.WEAPON;

public class Const_Item {
	private static final WEAPON HAND = new WEAPON((long) 0, "素手", 1);
	private static final WEAPON WOOD_WAND = new WEAPON((long) 1, "木の杖", 5);
	private static final WEAPON WIZARD_WAND = new WEAPON((long) 2, "魔法使いの杖", 10);
	private static final WEAPON FIRE_WAND = new WEAPON((long) 3, "ファイアーワンド", 13);
	private static final WEAPON THUNDER_WAND = new WEAPON((long) 4, "サンダーワンド", 14);
	private static final WEAPON ICE_WAND = new WEAPON((long) 5, "アイスワンド", 16);
	private static final WEAPON DAEMON_STICK = new WEAPON((long) 6, "悪魔のステッキ", 25);
	private static final WEAPON BRANCH_OF_YGGDRASIL = new WEAPON((long) 7, "ユグドラシルの枝", 35);
	private static final WEAPON DRAGON_BONE = new WEAPON((long) 8, "ドラゴンの杖", 38);
	private static final WEAPON PHILOSOPHERS_STONE = new WEAPON((long) 9, "賢者の石", 60);

	private static final ARMOR SKIN = new ARMOR((long) 0, "寝間着", 1);
	private static final ARMOR ROBE = new ARMOR((long) 1, "ローブ", 3);
	private static final ARMOR LEATHER_ARMOR = new ARMOR((long) 2, "皮の鎧", 6);
	private static final ARMOR FEATHER_ROBE = new ARMOR((long) 3, "フェザーローブ", 10);
	private static final ARMOR LIZARD_MAIL = new ARMOR((long) 4, "リザードメイル", 12);
	private static final ARMOR WIZARD_ROBE = new ARMOR((long) 5, "魔導師のローブ", 19);
	private static final ARMOR PALADIN_SHIELD = new ARMOR((long) 6, "聖騎士の盾", 25);
	private static final ARMOR DAEMON_MAIL = new ARMOR((long) 7, "魔神の鎧", 35);
	private static final ARMOR ELEMENTAL_ROBE = new ARMOR((long) 8, "エレメンタルローブ", 43);
	private static final ARMOR PHILOSOPHERS_HAT = new ARMOR((long) 9, "賢者の帽子", 65);

	public static List<WEAPON> getAllWeapon() {
		List<WEAPON> result = new ArrayList<WEAPON>();

		// TODO 抜け漏れなく全て取得できるようにしたい
		result.add(getWoodWand());
		result.add(getWizardWand());
		result.add(getFireWand());
		result.add(getThunderWand());
		result.add(getIceWand());
		result.add(getDaemonStick());
		result.add(getBranchOfYggdrasil());
		result.add(getDragonBone());
		result.add(getPhilosophersStone());

		return result;
	}

	public static List<ARMOR> getAllArmor() {
		List<ARMOR> result = new ArrayList<ARMOR>();

		// TODO 抜け漏れなく全て取得できるようにしたい
		result.add(getRobe());
		result.add(getLeatherArmor());
		result.add(getFeatherRobe());
		result.add(getLizardMail());
		result.add(getWizardRobe());
		result.add(getPaladinShield());
		result.add(getDaemonMail());
		result.add(getElementalRobe());
		result.add(getPhilosophersHat());

		return result;
	}

	public static WEAPON getHand() {
		return HAND;
	}

	public static WEAPON getWoodWand() {
		return WOOD_WAND;
	}

	public static WEAPON getWizardWand() {
		return WIZARD_WAND;
	}

	public static WEAPON getFireWand() {
		return FIRE_WAND;
	}

	public static WEAPON getThunderWand() {
		return THUNDER_WAND;
	}

	public static WEAPON getIceWand() {
		return ICE_WAND;
	}

	public static WEAPON getDaemonStick() {
		return DAEMON_STICK;
	}

	public static WEAPON getBranchOfYggdrasil() {
		return BRANCH_OF_YGGDRASIL;
	}

	public static WEAPON getDragonBone() {
		return DRAGON_BONE;
	}

	public static WEAPON getPhilosophersStone() {
		return PHILOSOPHERS_STONE;
	}

	public static ARMOR getSkin() {
		return SKIN;
	}

	public static ARMOR getRobe() {
		return ROBE;
	}

	public static ARMOR getLeatherArmor() {
		return LEATHER_ARMOR;
	}

	public static ARMOR getFeatherRobe() {
		return FEATHER_ROBE;
	}

	public static ARMOR getLizardMail() {
		return LIZARD_MAIL;
	}

	public static ARMOR getWizardRobe() {
		return WIZARD_ROBE;
	}

	public static ARMOR getPaladinShield() {
		return PALADIN_SHIELD;
	}

	public static ARMOR getDaemonMail() {
		return DAEMON_MAIL;
	}

	public static ARMOR getElementalRobe() {
		return ELEMENTAL_ROBE;
	}

	public static ARMOR getPhilosophersHat() {
		return PHILOSOPHERS_HAT;
	}

}
