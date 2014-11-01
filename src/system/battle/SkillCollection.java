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
		switch (skill.type) {
			case NormalAttack:
				normalAttack(elements, skill);
				break;
			case SpecialAttack:
				break;
			default:
				break;
		}
		return true;
	}

	public boolean actAttackSkillOnClash(BattleElements elements) {
		Skill skill = elements.getActorSkill();
		switch (skill.type) {
			case NormalAttack:
				normalAttackOnClash(elements, skill);
				break;
			case SpecialAttack:
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

		target.hp = target.hp - skill.point;

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
		target.hp = target.hp - (skill.point - targetSkill.point);

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

		actor.sp++;

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
		actor.sp = actor.sp - skill.necessarySkillPoint;

		elements.actor = actor;
	}

	// ----------------------------------------------------------------------------------------
}
