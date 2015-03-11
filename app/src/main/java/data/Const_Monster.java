package data;

import java.util.ArrayList;
import java.util.List;

import com.games.Trilemma.R;

import Trilemma.MONSTER;

public class Const_Monster {

	private static final int IMAGE_SURAIMU = R.drawable.mon_001;
	private static final int IMAGE_KARASU = R.drawable.mon_050;
	private static final int IMAGE_INOSHISHI = R.drawable.mon_022;
	private static final int IMAGE_PANDA = R.drawable.mon_275;
	private static final int IMAGE_MATANGO = R.drawable.mon_046;
	private static final int IMAGE_DORAGON = R.drawable.mon_013;

	private static final MONSTER 地下室のスライム = new MONSTER((long) 1, "地下室のスライム", IMAGE_SURAIMU, level(1), maxHp(30),
			maxSp(3), baseSp(0), attack(3), defense(1), rateAttack(80), rateDefense(5), rateCharge(30), gold(3),
			exp(2), Const_PLACE.getNo1Basement().getId());
	private static final MONSTER 凶暴なカラス = new MONSTER((long) 2, "凶暴なカラス", IMAGE_KARASU, level(2), maxHp(25), maxSp(3),
			baseSp(1), attack(4), defense(1), rateAttack(80), rateDefense(5), rateCharge(30), gold(3), exp(3),
			Const_PLACE.getNo2GreenField().getId());
	private static final MONSTER 空腹のイノシシ = new MONSTER((long) 3, "空腹のイノシシ", IMAGE_INOSHISHI, level(3), maxHp(40),
			maxSp(3), baseSp(0), attack(5), defense(2), rateAttack(80), rateDefense(5), rateCharge(40), gold(5),
			exp(3), Const_PLACE.getNo2GreenField().getId());
	private static final MONSTER 洞窟のスライム = new MONSTER((long) 4, "洞窟のスライム", IMAGE_SURAIMU, level(2), maxHp(40),
			maxSp(3), baseSp(0), attack(4), defense(2), rateAttack(70), rateDefense(15), rateCharge(35), gold(10),
			exp(5), Const_PLACE.getNo3Cave().getId());
	private static final MONSTER 野生のパンダ = new MONSTER((long) 5, "野生のパンダ", IMAGE_PANDA, level(3), maxHp(50), maxSp(3),
			baseSp(0), attack(6), defense(2), rateAttack(80), rateDefense(10), rateCharge(30), gold(13), exp(7),
			Const_PLACE.getNo3Cave().getId());
	private static final MONSTER 育ちすぎたマタンゴ = new MONSTER((long) 6, "育ちすぎたマタンゴ", IMAGE_MATANGO, level(4), maxHp(45),
			maxSp(5), baseSp(1), attack(5), defense(5), rateAttack(70), rateDefense(20), rateCharge(40), gold(25),
			exp(10), Const_PLACE.getNo3Cave().getId());
	private static final MONSTER 野良ドラゴン = new MONSTER((long) 7, "野良ドラゴン", IMAGE_DORAGON, level(10), maxHp(120),
			maxSp(6), baseSp(2), attack(12), defense(8), rateAttack(90), rateDefense(10), rateCharge(40), gold(100),
			exp(46), Const_PLACE.getNo4RockMoutain().getId());

	public static List<MONSTER> getAllMonster() {
		List<MONSTER> result = new ArrayList<MONSTER>();

		result.add(get地下室のスライム());
		result.add(get凶暴なカラス());
		result.add(get空腹のイノシシ());
		result.add(get洞窟のスライム());
		result.add(get野生のパンダ());
		result.add(get育ちすぎたマタンゴ());
		result.add(get野良ドラゴン());

		return result;
	}

	public static int getImageSuraimu() {
		return IMAGE_SURAIMU;
	}

	public static int getImageKarasu() {
		return IMAGE_KARASU;
	}

	public static int getImageInoshishi() {
		return IMAGE_INOSHISHI;
	}

	public static int getImagePanda() {
		return IMAGE_PANDA;
	}

	public static int getImageMatango() {
		return IMAGE_MATANGO;
	}

	public static int getImageDoragon() {
		return IMAGE_DORAGON;
	}

	public static MONSTER get地下室のスライム() {
		return 地下室のスライム;
	}

	public static MONSTER get凶暴なカラス() {
		return 凶暴なカラス;
	}

	public static MONSTER get空腹のイノシシ() {
		return 空腹のイノシシ;
	}

	public static MONSTER get洞窟のスライム() {
		return 洞窟のスライム;
	}

	public static MONSTER get野生のパンダ() {
		return 野生のパンダ;
	}

	public static MONSTER get育ちすぎたマタンゴ() {
		return 育ちすぎたマタンゴ;
	}

	public static MONSTER get野良ドラゴン() {
		return 野良ドラゴン;
	}

	private static int level(int level) {
		return level;
	}

	private static int maxHp(int a) {
		return a;
	}

	private static int maxSp(int a) {
		return a;
	}

	private static int baseSp(int a) {
		return a;
	}

	private static int attack(int a) {
		return a;
	}

	private static int defense(int a) {
		return a;
	}

	private static int rateAttack(int a) {
		return a;
	}

	private static int rateDefense(int a) {
		return a;
	}

	private static int rateCharge(int a) {
		return a;
	}

	private static int gold(int a) {
		return a;
	}

	private static int exp(int a) {
		return a;
	}

}
