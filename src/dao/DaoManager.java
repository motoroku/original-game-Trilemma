package dao;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import Trilemma.CHARACTER;
import Trilemma.DaoSession;
import Trilemma.LEARNED_SKILL;
import Trilemma.SKILL;
import Trilemma.CHARACTERDao.Properties;

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
		return player;
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
}
