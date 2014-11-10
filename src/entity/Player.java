package entity;

import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.SkillType;
import entity.BattleStatus.TargetStatus;
import entity.skill.Skill;

public class Player extends CharacterEntity {

	public Player(String name) {
		super(name);

		this.characterType = BattleStatus.PLAYER;

		skillList[0] = new Skill(TargetStatus.enemy, 40, 1, ActionStatus.�U��, SkillType.NormalAttack, "�ʏ�U��1");
		skillList[1] = new Skill(TargetStatus.enemy, 70, 2, ActionStatus.�U��, SkillType.NormalAttack, "�ʏ�U��2");
		skillList[2] = new Skill(TargetStatus.enemy, 90, 3, ActionStatus.�U��, SkillType.NormalAttack, "�ʏ�U��3");
		skillList[3] = new Skill(TargetStatus.enemy, 200, 6, ActionStatus.�U��, SkillType.NormalAttack, "�ʏ�U��4");
		skillList[4] = new Skill(TargetStatus.enemy, 500, 10, ActionStatus.�U��, SkillType.NormalAttack, "�ʏ�U��5");

		sp = 3;
	}
}
