package entity;

import java.util.Random;


import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.SelectActionList;
import entity.BattleStatus.SkillType;
import entity.skill.Skill;

public class Enemy extends CharacterEntity {

	private Random random = new Random();

	public Enemy(String name) {
		super(name);

		skillList[0] = new Skill(name, 10, 1, ActionStatus.çUåÇ, SkillType.NormalAttack);
		skillList[1] = new Skill(name, 15, 2, ActionStatus.çUåÇ, SkillType.NormalAttack);
		skillList[2] = new Skill(name, 25, 3, ActionStatus.çUåÇ, SkillType.NormalAttack);

		sp = 2;
	}

	public int getEnemyAction() {
		int num = getRandomNum(3, false);
		if (num == 1) {
			num = getRandomNum(3, true);
		} else if (num == 2) {
			num = SelectActionList.defense.getActionNo();
		} else if (num == 3) {
			num = SelectActionList.charge.getActionNo();
		}

		return num;
	}

	private int getRandomNum(int max, boolean isZero) {
		int num;
		if (isZero) {
			num = random.nextInt(max);
		} else {
			num = random.nextInt(max) + 1;
		}
		return num;
	}
}