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
		skillList = new Skill[12];
		itemList = new Item[4];

		skillList[0] = new Skill();

		skillList[10] = new Skill(name, 0, 0, ActionStatus.ñhå‰, null);
		skillList[11] = new Skill(name, 0, 0, ActionStatus.É`ÉÉÅ[ÉW, null);

		hp = 100;
		sp = 0;
	}

	public ActionStatus getActionStatus() {
		return usingSkill.actionStatus;
	}

}
