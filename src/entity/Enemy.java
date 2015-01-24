package entity;

import java.util.List;

import Trilemma.CHARACTER;
import Trilemma.LEARNED_SKILL;
import Trilemma.SKILL;
import system.battle.BattleElements;
import system.battle.EnemyAI;
import system.battle.iEnemyAI;

public class Enemy extends CharacterEntity {
	public iEnemyAI enemyAi;
	public int baseAttackRate;
	public int baseDefenseRate;
	public int baseChargeRate;
	public int attackRate;
	public int defenseRate;
	public int chargeRate;

	// TODO: デフェンスとチャージ引数の順番間違えたらアウトなので、対策するべし
	/**
	 * 
	 * @param characterEntity
	 * @param skillList
	 * @param defenseSkill
	 * @param chargeSkill
	 */
	public Enemy(CHARACTER characterEntity, List<SKILL> skillList, SKILL defenseSkill, SKILL chargeSkill) {
		super(characterEntity.getCharacter_name());

		level = characterEntity.getLevel();

		hp = characterEntity.getMax_hp();
		maxSp = characterEntity.getMax_sp();

		currentHp = hp;
		currentSp = characterEntity.getBase_sp();

		attack = characterEntity.getAttack_power();
		defense = characterEntity.getDefense_power();

		baseAttackRate = characterEntity.getBase_attack_rate();
		baseDefenseRate = characterEntity.getBase_defense_rate();
		baseChargeRate = characterEntity.getBase_charge_rate();

		attackRate = baseAttackRate;
		defenseRate = baseDefenseRate;
		chargeRate = baseChargeRate;

		gold = characterEntity.getProfit_gold();
		exp = characterEntity.getProfit_exp();

		this.characterType = BattleStatus.ENEMY;
		enemyAi = new EnemyAI();

		this.skillList = new Skill[skillList.size() + 2];

		this.skillList[0] = new Skill(defenseSkill);
		this.skillList[1] = new Skill(chargeSkill);

		for (int i = 0; i < skillList.size(); i++) {
			this.skillList[i + 2] = new Skill(skillList.get(i));
		}
	}

	public int getEnemyAction(BattleElements elements) {
		return enemyAi.getEnemyAction(attackRate, defenseRate, chargeRate, elements);
	}

	public void resetRate() {
		this.attackRate = baseAttackRate;
		this.defenseRate = baseDefenseRate;
		this.chargeRate = baseChargeRate;
	}
}