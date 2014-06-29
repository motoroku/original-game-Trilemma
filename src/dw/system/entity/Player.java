package dw.system.entity;

import dw.skill.AttackSkill;

public class Player extends CharacterEntity {

	public Player(String name) {
		super(name);

		mSkillList[0] = new AttackSkill(name, 30);
	}
}
