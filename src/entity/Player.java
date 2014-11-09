package entity;

import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.SkillType;
import entity.skill.Skill;

public class Player extends CharacterEntity {

	public Player(String name) {
		super(name);

		this.characterType = BattleStatus.PLAYER;

		skillList[0] = new Skill(name, 20, 1, ActionStatus.UŒ‚, SkillType.NormalAttack, "’ÊíUŒ‚1");
		skillList[1] = new Skill(name, 30, 2, ActionStatus.UŒ‚, SkillType.NormalAttack, "’ÊíUŒ‚2");
		skillList[2] = new Skill(name, 40, 3, ActionStatus.UŒ‚, SkillType.NormalAttack, "’ÊíUŒ‚3");
		skillList[3] = new Skill(name, 50, 6, ActionStatus.UŒ‚, SkillType.NormalAttack, "’ÊíUŒ‚4");
		skillList[4] = new Skill(name, 100, 10, ActionStatus.UŒ‚, SkillType.NormalAttack, "’ÊíUŒ‚5");

		sp = 3;
	}
}
