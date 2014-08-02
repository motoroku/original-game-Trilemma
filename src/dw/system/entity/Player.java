package dw.system.entity;

import dw.skill.Skill;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.SkillType;

public class Player extends CharacterEntity {

	public Player(String name) {
		super(name);

		skillList[0] = new Skill(name, 20, 1, ActionStatus.UŒ‚, SkillType.NormalAttack);
		skillList[1] = new Skill(name, 30, 2, ActionStatus.UŒ‚, SkillType.NormalAttack);
		skillList[2] = new Skill(name, 40, 3, ActionStatus.UŒ‚, SkillType.NormalAttack);

		sp = 5;
	}
}
