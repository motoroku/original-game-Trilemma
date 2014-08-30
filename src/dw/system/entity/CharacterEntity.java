package dw.system.entity;

import dw.skill.Skill;
import dw.system.entity.BattleStatus.ActionStatus;

public class CharacterEntity {

	public String name;

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

		skillList[5] = new Skill(name, 0, 0, ActionStatus.–hŒä, null);
		skillList[6] = new Skill(name, 0, 0, ActionStatus.ƒ`ƒƒ[ƒW, null);

		hp = 100;
		sp = 0;
	}

	public ActionStatus getActionStatus() {
		return usingSkill.actionStatus;
	}

}
