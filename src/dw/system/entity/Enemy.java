package dw.system.entity;

import java.util.Random;

import dw.skill.AttackSkill;
import dw.skill.ChargeSkill;
import dw.skill.DefenseSkill;
import dw.skill.Skill;
import dw.system.entity.BattleStatus.AttackSkillType;

public class Enemy extends CharacterEntity {

	public Enemy(String name) {
		super(name);

		mSkillList[0] = new AttackSkill(name, 10, 1, AttackSkillType.Normal);
		mSkillList[1] = new AttackSkill(name, 15, 2, AttackSkillType.Normal);
		mSkillList[2] = new AttackSkill(name, 25, 3, AttackSkillType.Normal);
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
