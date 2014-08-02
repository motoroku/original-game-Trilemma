package dw.system.entity;

import java.util.Random;

import dw.skill.Skill;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.SkillType;

public class Enemy extends CharacterEntity {

	public Enemy(String name) {
		super(name);

		skillList[0] = new Skill(name, 10, 1, ActionStatus.UŒ‚, SkillType.NormalAttack);
		skillList[1] = new Skill(name, 15, 2, ActionStatus.UŒ‚, SkillType.NormalAttack);
		skillList[2] = new Skill(name, 25, 3, ActionStatus.UŒ‚, SkillType.NormalAttack);

		sp = 2;
	}

	public int getEnemyAction() {
		Random random = new Random();
		int num = random.nextInt(3) + 1;
		if (num == 1) {
			num = 0;
		} else if (num == 2) {
			num = 10;
		} else if (num == 3) {
			num = 11;
		}

		return num;
	}

}