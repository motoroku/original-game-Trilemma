package system.battle;

import utility.Cal;
import entity.CharacterEntity;
import entity.Skill;

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

		int attackPoint = Cal.calSkillMight(actor, skill);
		int effect = Cal.calEffectPoint(attackPoint, target.defense);
		target.currentHp = target.currentHp - effect;

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

		int attackPoint1 = Cal.calSkillMight(actor, skill);
		int attackPoint2 = Cal.calSkillMight(target, targetSkill);
		int effect = Cal.calEffectPoint(attackPoint1, attackPoint2);
		target.currentHp = target.currentHp - effect;

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

		if (actor.maxSp > actor.currentSp) {
			actor.currentSp++;
		}

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
		actor.currentSp = actor.currentSp - skill.skillPoint;

		elements.actor = actor;
	}

	// ----------------------------------------------------------------------------------------
}
