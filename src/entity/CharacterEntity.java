package entity;

import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.SkillType;
import entity.BattleStatus.TargetStatus;
import entity.skill.Skill;

public class CharacterEntity {

	public String name;
	public String characterType;

	public Skill[] skillList;
	public Item[] itemList;

	public Skill usingSkill;

	public int hp;
	public int sp;

	public CharacterEntity() {
	}

	public CharacterEntity(String name) {
		this.name = name;
		skillList = new Skill[7];
		itemList = new Item[4];

		for (int i = 0; i < skillList.length - 2; i++) {
			skillList[i] = new Skill();
		}

		skillList[5] = new Skill(TargetStatus.self, 0, 0, ActionStatus.防御, SkillType.Defense, "通常防御");
		skillList[6] = new Skill(TargetStatus.self, 0, 0, ActionStatus.チャージ, SkillType.Charge, "通常チャージ");

		hp = 100;
		sp = 0;
	}

	public ActionStatus getActionStatus() {
		return usingSkill.actionStatus;
	}

	public boolean isEmptySkillPoint() {
		return sp == 0;
	}
}
