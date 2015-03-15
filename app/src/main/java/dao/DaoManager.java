package dao;

import java.util.ArrayList;
import java.util.List;

import data.Const_Item;
import data.Const_PlayerStatus;
import de.greenrobot.dao.query.QueryBuilder;
import entity.Armor;
import entity.Weapon;
import android.R.integer;
import android.content.Context;
import android.hardware.TriggerEvent;
import Trilemma.ARMOR;
import Trilemma.ARMOR_INVENTORY;
import Trilemma.MONSTER;
import Trilemma.DUNGEON;
import Trilemma.DUNGEONDao;
import Trilemma.DaoSession;
import Trilemma.LEARNED_SKILL;
import Trilemma.MONSTER;
import Trilemma.PEOPLE;
import Trilemma.PEOPLEDao;
import Trilemma.PLAYER_STATUS;
import Trilemma.SKILL;
import Trilemma.MONSTERDao.Properties;
import Trilemma.TOWN;
import Trilemma.TOWNDao;
import Trilemma.WEAPON;
import Trilemma.WEAPON_INVENTORY;

public class DaoManager {

	private MyOpenHelper dbHelper;

	public DaoSession session;

	public DaoManager(Context context) {
		dbHelper = MyOpenHelper.getInstance(context);
		session = dbHelper.session();
	}

    public DaoSession getsession(Context context){
        dbHelper = MyOpenHelper.getInstance(context);
        return dbHelper.session();
    }



	public List<SKILL> getPlayerSkillList(List<LEARNED_SKILL> learnedSkillList) {
		List<SKILL> list = new ArrayList<SKILL>();

		for (int i = 0; i < learnedSkillList.size(); i++) {
			list.add(session.getSKILLDao().queryBuilder()
					.where(Trilemma.SKILLDao.Properties.Id.eq(learnedSkillList.get(i).getSkill_id())).list().get(0));
		}

		return list;
	}

	// TODO: マジックナンバーになってるので修正するように！
	public List<LEARNED_SKILL> getPlayerLearnedSkill() {
		List<LEARNED_SKILL> list = session.getLEARNED_SKILLDao().queryBuilder()
				.where(Trilemma.LEARNED_SKILLDao.Properties.Character_id.eq((long) 0)).list();
		return list;
	}

	// TODO: マジックナンバーになってるので修正するように！
	public SKILL getDefaultDefenseSkill() {
		SKILL skill = session.getSKILLDao().queryBuilder()
				.where(Trilemma.SKILLDao.Properties.Skill_type_id.eq((long) 2)).list().get(0);
		return skill;
	}

	// TODO: マジックナンバーになってるので修正するように！
	public SKILL getDefaultChargeSkill() {
		SKILL skill = session.getSKILLDao().queryBuilder()
				.where(Trilemma.SKILLDao.Properties.Skill_type_id.eq((long) 3)).list().get(0);
		return skill;
	}

	/**
	 * 装備している武器を変更する
	 * 持っている武器から順番に変えていく
	 */
	public void changeNextWeapon() {
		List<WEAPON_INVENTORY> weaponInventoryList = session.getWEAPON_INVENTORYDao().loadAll();
		PLAYER_STATUS playerStatus = session.getPLAYER_STATUSDao().queryBuilder()
				.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getEquipmentWeapon().getStatus_name())).list()
				.get(0);

		int weaponId = Integer.parseInt(playerStatus.getStatus_value());

		WEAPON nextWeapon = null;

		for (int i = 0; i < weaponInventoryList.size(); i++) {
			if (weaponId == 0) {
				nextWeapon = weaponInventoryList.get(0).getWEAPON();
			} else if (weaponInventoryList.get(i).getWeapon_id() == (long) weaponId) {
				if (i != weaponInventoryList.size() - 1) {
					nextWeapon = weaponInventoryList.get(i + 1).getWEAPON();
				} else {
					nextWeapon = Const_Item.getHand();
				}
			}
		}

		if (nextWeapon == null) {
			playerStatus.setStatus_value("0");
		} else {
			playerStatus.setStatus_value(String.valueOf(nextWeapon.getId()));
		}
		session.getPLAYER_STATUSDao().update(playerStatus);
	}

	/**
	 * 装備している防具を変更する
	 * 持っている防具から順番に変えていく
	 */
	public void changeNextArmor() {
		List<ARMOR_INVENTORY> armorInventoryList = session.getARMOR_INVENTORYDao().loadAll();
		PLAYER_STATUS playerStatus = session.getPLAYER_STATUSDao().queryBuilder()
				.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getEquipmentArmor().getStatus_name())).list()
				.get(0);
		int armorId = Integer.parseInt(playerStatus.getStatus_value());
		ARMOR nextArmor = null;

		for (int i = 0; i < armorInventoryList.size(); i++) {
			if (armorId == 0) {
				nextArmor = armorInventoryList.get(0).getARMOR();
			} else if (armorInventoryList.get(i).getArmor_id() == (long) armorId) {
				if (i != armorInventoryList.size() - 1) {
					nextArmor = armorInventoryList.get(i + 1).getARMOR();
				} else {
					nextArmor = Const_Item.getSkin();
				}
			}
		}

		if (nextArmor == null) {
			playerStatus.setStatus_value("0");
		} else {
			playerStatus.setStatus_value(String.valueOf(nextArmor.getId()));
		}
		session.getPLAYER_STATUSDao().update(playerStatus);

	}

	/**
	 * 所持金を取得する
	 * @return 所持金
	 */
	public int getGold() {
		return Integer.parseInt(session.getPLAYER_STATUSDao().queryBuilder()
				.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getGold().getStatus_name())).list().get(0)
				.getStatus_value());
	}

	/**
	 * 引数分所持金を増やす
	 * @param profitGold 得たお金
	 */
	public void addGold(int profitGold) {
		int currentGold = getGold();
		int result = currentGold + profitGold;

		PLAYER_STATUS gold = session.getPLAYER_STATUSDao().queryBuilder()
				.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getGold().getStatus_name())).list().get(0);

		gold.setStatus_value(String.valueOf(result));

		session.getPLAYER_STATUSDao().update(gold);
	}

	/**
	 * 引数分所持金を減らす。減らした結果、0以下の場合は0になる
	 * @param amountGold 消費したお金
	 */
	public void consumeGold(int amountGold) {
		int currentGold = getGold();
		int result = currentGold - amountGold;

		if (0 > result) {
			result = 0;
		}

		PLAYER_STATUS gold = session.getPLAYER_STATUSDao().queryBuilder()
				.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getGold().getStatus_name())).list().get(0);

		gold.setStatus_value(String.valueOf(result));
		session.getPLAYER_STATUSDao().update(gold);
	}

	/**
	 * 現在のEXPを取得
	 * @return 現在のEXP
	 */
	public int getExp() {
		return Integer.parseInt(session.getPLAYER_STATUSDao().queryBuilder()
				.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getExp().getStatus_name())).list().get(0)
				.getStatus_value());
	}

	/**
	 * 経験値を追加する
	 * @param profitExp 取得した経験値
	 */
	public void addExp(int profitExp) {
		int currentExp = getExp();
		int result = currentExp + profitExp;
		PLAYER_STATUS exp = session.getPLAYER_STATUSDao().queryBuilder()
				.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getExp().getStatus_name())).list().get(0);
		exp.setStatus_value(String.valueOf(result));
		session.getPLAYER_STATUSDao().update(exp);
	}

	public void addWeapon(long weaponId) {
		List<WEAPON_INVENTORY> weaponList = session.getWEAPON_INVENTORYDao().queryBuilder()
				.where(Trilemma.WEAPON_INVENTORYDao.Properties.Weapon_id.eq(weaponId)).list();
		if (weaponList.size() != 0) {
			WEAPON_INVENTORY weapon = weaponList.get(0);
			weapon.setNumber(weapon.getNumber() + 1);
			session.getWEAPON_INVENTORYDao().update(weapon);
		} else {
			WEAPON weapon = session.getWEAPONDao().load(weaponId);
			WEAPON_INVENTORY weaponInventory = new WEAPON_INVENTORY();
			weaponInventory.setNumber(1);
			weaponInventory.setWeapon_id(weaponId);
			weaponInventory.setEnhanced_point(0);
			weaponInventory.setWEAPON(weapon);
			session.getWEAPON_INVENTORYDao().insert(weaponInventory);
		}
	}

	public void addArmor(long armorId) {
		List<ARMOR_INVENTORY> armorList = session.getARMOR_INVENTORYDao().queryBuilder()
				.where(Trilemma.ARMOR_INVENTORYDao.Properties.Armor_id.eq(armorId)).list();
		if (armorList.size() != 0) {
			ARMOR_INVENTORY armor = armorList.get(0);
			armor.setNumber(armor.getNumber() + 1);
			session.getARMOR_INVENTORYDao().update(armor);
		} else {
			ARMOR armor = session.getARMORDao().load(armorId);
			ARMOR_INVENTORY armorInventory = new ARMOR_INVENTORY();
			armorInventory.setNumber(1);
			armorInventory.setArmor_id(armorId);
			armorInventory.setEnhanced_point(0);
			armorInventory.setARMOR(armor);
			session.getARMOR_INVENTORYDao().insert(armorInventory);
		}

	}

	public boolean reduceWeapon(long weaponId) {
		// TODO 減らす武器を持っているかどうかを確認する
		// TODO 持っていたら1つ減らしてtrueを返す
		// TODO持っていなかったらfalseを返す
		return false;
	}

	public boolean reduceArmor(long armorId) {
		// TODO 減らす防具を持っているかどうかを確認する
		// TODO 持っていたら1つ減らしてtrueを返す
		// TODO 持っていなかったらfalseを返す
		return false;
	}

	/**
	 * 有効なダンジョンを全て取得する
	 * @return 有効なダンジョンのリスト
	 */
	// public List<DUNGEON> getDungeonList() {
	// List<DUNGEON> result = session.getDUNGEONDao().queryBuilder()
	// .where(Trilemma.DUNGEONDao.Properties.Flg.eq(true)).list();
	//
	// return result;
	// }

	/**
	 * ダンジョンの有効フラグを操作する
	 * @param dungeon 有効フラグを操作するダンジョン
	 * @param flg 設定するフラグ内容
	 */
	// public void setDungeonFlg(DUNGEON dungeon, boolean flg) {
	// dungeon.setFlg(flg);
	//
	// session.getDUNGEONDao().update(dungeon);
	// }

	/**
	 * 指定したダンジョンを取得する
	 * @param name 取得するダンジョンの名前
	 * @return ダンジョン
	 */
	public DUNGEON getDungeion(String name) {
		DUNGEON result = session.getDUNGEONDao().queryBuilder().where(DUNGEONDao.Properties.Dungeon_name.eq(name))
				.list().get(0);
		return result;
	}

	/**
	 * ダンジョンにいるモンスターを全て取得する
	 * @param dungeonId 対象のダンジョン
	 * @return 引数のダンジョンにいる全てのモンスターのリスト
	 */
	public List<MONSTER> getCharacterList(long dungeonId) {
		List<MONSTER> list = session.getMONSTERDao().queryBuilder().where(Properties.Dungeon_id.eq(dungeonId)).list();

		return list;
	}

	/**
	 * 有効な街を全て取得する
	 * @return 有効な街のリスト
	 */
	// public List<TOWN> getTownList() {
	// List<TOWN> result =
	// session.getTOWNDao().queryBuilder().where(Trilemma.TOWNDao.Properties.Flg.eq(true)).list();
	// return result;
	// }

	/**
	 * 街の有効フラグを操作する
	 * @param town 有効フラグを操作する街
	 * @param flg 設定するフラグの内容
	 */
	// public void setTownFlg(TOWN town, boolean flg) {
	// town.setFlg(flg);
	//
	// session.getTOWNDao().update(town);
	// }

	/**
	 * 指定した街を取得する
	 * @param name 取得する街の名前
	 * @return 街
	 */
	public TOWN getTown(String name) {
		TOWN result = session.getTOWNDao().queryBuilder().where(TOWNDao.Properties.Town_name.eq(name)).list().get(0);
		return result;
	}

	/**
	 * 指定した街の人を全て取得する
	 * @param townId 指定する街のID
	 * @return 指定した街の人のリスト
	 */
	public List<PEOPLE> getPeopleList(long townId) {
		List<PEOPLE> result = session.getPEOPLEDao().queryBuilder().where(PEOPLEDao.Properties.Town_id.eq(townId))
				.list();
		return result;
	}

	/**
	 * 人からその人が存在している街を取得する
	 * @param people 取得したい街にいる人
	 * @return 引数の人が存在している街
	 */
	public TOWN getTownFromPeople(PEOPLE people) {
		Long peopleId = people.getId();
		people = session.getPEOPLEDao().queryBuilder().where(Trilemma.PEOPLEDao.Properties.Id.eq(peopleId)).list()
				.get(0);
		TOWN town = session.getTOWNDao().queryBuilder().where(Trilemma.TOWNDao.Properties.Id.eq(people.getTown_id()))
				.list().get(0);
		return town;
	}

}
