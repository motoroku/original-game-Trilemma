package entity;

import system.battle.EnemyAI;
import system.battle.iEnemyAI;

import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.SkillType;
import entity.skill.Skill;

public class Enemy extends CharacterEntity {
	public iEnemyAI enemyAi;
	public int attackRate = 50;
	public int defenseRate = 30;
	public int chargeRate = 20;

	public Enemy(String name) {
		super(name);

		this.characterType = BattleStatus.ENEMY;
		enemyAi = new EnemyAI();

		skillList[0] = new Skill(name, 10, 1, ActionStatus.UŒ‚, SkillType.NormalAttack);
		skillList[1] = new Skill(name, 15, 2, ActionStatus.UŒ‚, SkillType.NormalAttack);
		skillList[2] = new Skill(name, 25, 3, ActionStatus.UŒ‚, SkillType.NormalAttack);

		sp = 2;
	}

	public int getEnemyAction() {
		return enemyAi.getEnemyAction(attackRate, defenseRate, chargeRate);
	}

}