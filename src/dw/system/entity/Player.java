package dw.system.entity;

import dw.skill.AttackSkill;
import dw.system.entity.BattleStatus.AttackSkillType;

public class Player extends CharacterEntity {

	public Player(String name) {
		super(name);

		mSkillList[0] = new AttackSkill(name, 20, 1, AttackSkillType.Normal);
		mSkillList[1] = new AttackSkill(name, 30, 2, AttackSkillType.Normal);
		mSkillList[2] = new AttackSkill(name, 40, 3, AttackSkillType.Normal);
	}
}
