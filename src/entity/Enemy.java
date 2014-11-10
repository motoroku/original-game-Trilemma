package entity;

import system.battle.BattleElements;
import system.battle.EnemyAI;
import system.battle.iEnemyAI;

import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.SkillType;
import entity.BattleStatus.TargetStatus;
import entity.skill.Skill;

public class Enemy extends CharacterEntity {
	public iEnemyAI enemyAi;
	public final int baseAttackRate = 80;
	public final int baseDefenseRate = 5;
	public final int baseChargeRate = 15;
	public int attackRate = 80;
	public int defenseRate = 5;
	public int chargeRate = 15;

	public Enemy(String name) {
		super(name);

		this.characterType = BattleStatus.ENEMY;
		enemyAi = new EnemyAI();

		skillList[0] = new Skill(TargetStatus.enemy, 10, 1, ActionStatus.UŒ‚, SkillType.NormalAttack, "ƒ‚ƒ“ƒXƒ^[‚Ì’ÊíUŒ‚1");
		skillList[1] = new Skill(TargetStatus.enemy, 15, 2, ActionStatus.UŒ‚, SkillType.NormalAttack, "ƒ‚ƒ“ƒXƒ^[‚Ì’ÊíUŒ‚2");
		skillList[2] = new Skill(TargetStatus.enemy, 25, 3, ActionStatus.UŒ‚, SkillType.NormalAttack, "ƒ‚ƒ“ƒXƒ^[‚Ì’ÊíUŒ‚3");

		sp = 2;
	}

	public int getEnemyAction(BattleElements elements) {
		return enemyAi.getEnemyAction(attackRate, defenseRate, chargeRate, elements);
	}

	public void resetRate(BattleElements elements) {
		Enemy temp = enemyAi.resetRate(this, elements);

		this.attackRate = temp.attackRate;
		this.defenseRate = temp.defenseRate;
		this.chargeRate = temp.chargeRate;
	}
}