package system.battle;

import entity.CharacterEntity;
import entity.skill.Skill;

/**
 * Skillの処理メソッドを持つクラス
 * @author mori_yu
 * 
 */
public class SkillCollection {
	public SkillCollection() {
	}

	// ----------------------------------------------------------------------------------------
	// 使用スキルの分岐処理
	public boolean actAttackSkill(BattleElements elements) {
		Skill skill = elements.getActorSkill();
		switch (skill.type.getSkill_type_name()) {
			case "通常攻撃":
				normalAttack(elements, skill);
				break;
			case "強攻撃":
				break;
			default:
				break;
		}
		return true;
	}

	public boolean actAttackSkillOnClash(BattleElements elements) {
		Skill skill = elements.getActorSkill();
		switch (skill.type.getSkill_type_name()) {
			case "通常攻撃":
				normalAttackOnClash(elements, skill);
				break;
			case "強攻撃":
				break;
			default:
				break;
		}
		return true;
	}

	// ----------------------------------------------------------------------------------------
	/**
	 * 基本攻撃スキル
	 * @param elements
	 * @param skill
	 * @return
	 */
	public boolean normalAttack(BattleElements elements, Skill skill) {
		CharacterEntity target = elements.target;
		CharacterEntity actor = elements.actor;

		target.maxHp = target.maxHp - skill.effetPoint;

		elements.target = target;
		elements.actor = actor;
		return true;
	}

	/**
	 * 基本攻撃スキル。Clash時に利用する。
	 * @param elements
	 * @param skill
	 * @return
	 */
	public boolean normalAttackOnClash(BattleElements elements, Skill skill) {
		CharacterEntity target = elements.target;
		CharacterEntity actor = elements.actor;

		Skill targetSkill = target.usingSkill;
		target.maxHp = target.maxHp - (skill.effetPoint - targetSkill.effetPoint);

		elements.target = target;
		elements.actor = actor;
		return true;
	}

	// ----------------------------------------------------------------------------------------
	/**
	 * 基本チャージスキル
	 * @param elements
	 */
	public void chargeSkillPoint(BattleElements elements) {
		CharacterEntity actor = elements.actor;

		actor.maxSp++;

		elements.actor = actor;
	}

	// ----------------------------------------------------------------------------------------
	/**
	 * スキルポイントの消費処理
	 * @param elements
	 */
	public void consumeSkillPoint(BattleElements elements) {
		CharacterEntity actor = elements.actor;

		Skill skill = elements.getActorSkill();
		actor.maxSp = actor.maxSp - skill.skillPoint;

		elements.actor = actor;
	}

	// ----------------------------------------------------------------------------------------
}
