package dao;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

import entity.Armor;
import entity.Weapon;

import android.R.integer;
import android.content.Context;
import android.hardware.TriggerEvent;
import Trilemma.ARMOR;
import Trilemma.CHARACTER;
import Trilemma.DaoSession;
import Trilemma.LEARNED_SKILL;
import Trilemma.PEOPLE;
import Trilemma.PLAYER_STATUS;
import Trilemma.SKILL;
import Trilemma.CHARACTERDao.Properties;
import Trilemma.TOWN;
import Trilemma.WEAPON;

public class DaoManager {

	private MyOpenHelper dbHelper;

	public DaoSession session;

	public DaoManager(Context context) {
		dbHelper = MyOpenHelper.getInstance(context);
		session = dbHelper.session();
	}

	public PlayerDto getPlayerDto() {
		PlayerDto player = new PlayerDto();

		player.name = session.getPLAYER_STATUSDao().queryBuilder()
				.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq("name")).list().get(0).getStatus_value();
		player.hp = Integer.parseInt(session.getPLAYER_STATUSDao().queryBuilder()
				.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq("hp")).list().get(0).getStatus_value());
		player.maxSp = Integer.parseInt(session.getPLAYER_STATUSDao().queryBuilder()
				.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq("max_sp")).list().get(0).getStatus_value());
		player.baseSp = Integer.parseInt(session.getPLAYER_STATUSDao().queryBuilder()
				.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq("base_sp")).list().get(0).getStatus_value());
		player.level = Integer.parseInt(session.getPLAYER_STATUSDao().queryBuilder()
				.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq("level")).list().get(0).getStatus_value());
		try {
			player.attack = Integer.parseInt(session.getPLAYER_STATUSDao().queryBuilder()
					.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq("attack")).list().get(0)
					.getStatus_value());
		} catch (Exception e) {
			player.attack = 1;
		}
		try {
			player.defense = Integer.parseInt(session.getPLAYER_STATUSDao().queryBuilder()
					.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq("defense")).list().get(0)
					.getStatus_value());
		} catch (Exception e) {
			player.defense = 1;
		}
		int weaponId = Integer
				.parseInt(session.getPLAYER_STATUSDao().queryBuilder()
						.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq("weaponId")).list().get(0)
						.getStatus_value());
		int armorId = Integer.parseInt(session.getPLAYER_STATUSDao().queryBuilder()
				.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq("armorId")).list().get(0).getStatus_value());
		player.weapon = new Weapon(getWeapon(weaponId));
		player.armor = new Armor(getArmor(armorId));

		return player;
	}

	public WEAPON getWeapon(long weaponId) {
		QueryBuilder<WEAPON> weapons = session.getWEAPONDao().queryBuilder()
				.where(Trilemma.WEAPONDao.Properties.Id.eq(weaponId));
		WEAPON weapon;
		if (weapons == null) {
			weapon = new WEAPON((long) 0, "素手", 1);
		} else {
			weapon = weapons.list().get(0);
		}
		return weapon;
	}

	public ARMOR getArmor(long armorId) {
		ARMOR armor = session.getARMORDao().queryBuilder().where(Trilemma.ARMORDao.Properties.Id.eq(armorId)).list()
				.get(0);
		if (armor == null) {
			armor = new ARMOR((long) 0, "寝間着", 1);
		}
		return armor;
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

	public List<CHARACTER> getCharacterList(long dungeonId) {
		List<CHARACTER> list = session.getCHARACTERDao().queryBuilder().where(Properties.Dungeon_id.eq(dungeonId))
				.list();

		return list;
	}

	public TOWN getTownFromPeople(PEOPLE people) {
		Long peopleId = people.getId();
		people = session.getPEOPLEDao().queryBuilder().where(Trilemma.PEOPLEDao.Properties.Id.eq(peopleId)).list()
				.get(0);
		TOWN town = session.getTOWNDao().queryBuilder().where(Trilemma.TOWNDao.Properties.Id.eq(people.getTown_id()))
				.list().get(0);
		return town;
	}

	/**
	 * お試し用
	 */
	public void changeNextWeapon() {
		List<WEAPON> weaponList = session.getWEAPONDao().loadAll();
		PLAYER_STATUS playerStatus = session.getPLAYER_STATUSDao().queryBuilder()
				.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq("weaponId")).list().get(0);
		int weaponId = Integer.parseInt(playerStatus.getStatus_value());
		WEAPON nextWeapon = null;
		for (int i = 0; i < weaponList.size(); i++) {
			if (weaponList.get(i).getId() == (long) weaponId) {
				if (i != weaponList.size() - 1) {
					nextWeapon = weaponList.get(i + 1);
				} else {
					nextWeapon = weaponList.get(0);
				}
			}
		}
		if (nextWeapon != null) {
			playerStatus.setStatus_value(String.valueOf(nextWeapon.getId()));
		}
		session.getPLAYER_STATUSDao().update(playerStatus);
	}

	/**
	 * お試し用
	 */
	public void changeNextArmor() {
		List<ARMOR> armorList = session.getARMORDao().loadAll();
		PLAYER_STATUS playerStatus = session.getPLAYER_STATUSDao().queryBuilder()
				.where(Trilemma.PLAYER_STATUSDao.Properties.Status_name.eq("armorId")).list().get(0);
		int armorId = Integer.parseInt(playerStatus.getStatus_value());
		ARMOR nextArmor = null;
		for (int i = 0; i < armorList.size(); i++) {
			if (armorList.get(i).getId() == (long) armorId) {
				if (i != armorList.size() - 1) {
					nextArmor = armorList.get(i + 1);
				} else {
					nextArmor = armorList.get(0);
				}
			}
		}
		if (nextArmor != null) {
			playerStatus.setStatus_value(String.valueOf(nextArmor.getId()));
		}
		session.getPLAYER_STATUSDao().update(playerStatus);

	}
}
