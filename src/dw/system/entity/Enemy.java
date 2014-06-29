package dw.system.entity;

import java.util.Random;

import dw.skill.AttackSkill;
import dw.skill.ChargeSkill;
import dw.skill.DefenseSkill;
import dw.skill.Skill;

public class Enemy extends CharacterEntity {

	public Enemy(String name) {
		super(name);
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
