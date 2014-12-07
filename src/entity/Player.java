package entity;

import java.util.List;

import Trilemma.LEARNED_SKILL;
import entity.skill.Skill;

public class Player extends CharacterEntity {

	private static final int SKILL_SIZE_CUSTAMIZE = 5;

	public Player(String name, List<LEARNED_SKILL> skillList) {
		super(name);

		level = 1;
		maxHp = 100;
		maxSp = 10;
		currentHp = maxHp;
		currentSp = 3;

		this.characterType = BattleStatus.PLAYER;

		for (int i = 0; i < SKILL_SIZE_CUSTAMIZE; i++) {
			for (int j = 0; j < skillList.size(); j++) {
				if (skillList.get(i).getIs_set_flg() && skillList.get(i).getPosition_no() == i) {
					this.skillList[i] = new Skill(skillList.get(i).getSKILL());
				}
			}
		}
		this.skillList[CharacterEntity.SKILL_SIZE - SKILL_SIZE_CUSTAMIZE - 1] = getDefenseSkill();
		this.skillList[CharacterEntity.SKILL_SIZE - SKILL_SIZE_CUSTAMIZE] = getChargeSkill();
	}
}
