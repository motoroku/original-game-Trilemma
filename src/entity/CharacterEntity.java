package entity;

import entity.BattleStatus.ActionStatus;
import entity.skill.Skill;

public class CharacterEntity {

	public String name;
	public String characterType;

	public Skill[] skillList;
	public Item[] itemList;

	public Skill usingSkill;

	public int level;
	public int maxHp;
	public int maxSp;
	public int currentHp;
	public int currentSp;

	public static final int SKILL_SIZE = 7;

	public CharacterEntity() {
	}

	public CharacterEntity(String name) {
		this.name = name;
		skillList = new Skill[SKILL_SIZE];
		itemList = new Item[4];
	}

	public ActionStatus getActionStatus() {
		return usingSkill.actionStatus;
	}

	public boolean isEmptySkillPoint() {
		return maxSp == 0;
	}

	public Skill getDefenseSkill() {
		Skill defense = null;

		for (int i = 0; i < skillList.length; i++) {
			if (skillList[i].type.getId() == 2) {
				defense = skillList[i];
			}
		}

		return defense;
	}

	public Skill getChargeSkill() {
		Skill charge = null;

		for (int i = 0; i < skillList.length; i++) {
			if (skillList[i].type.getId() == 3) {
				charge = skillList[i];
			}
		}

		return charge;
	}
}
