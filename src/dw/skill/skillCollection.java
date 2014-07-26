package dw.skill;

import dw.system.battle.BattleElements;
import dw.system.entity.BattleStatus.AttackSkillType;
import dw.system.entity.CharacterEntity;

/**
 * Skillの処理メソッドを持つクラス
 * @author mori_yu
 * 
 */
public class skillCollection {
	public AttackSkillType attackSkillType;

	public skillCollection() {
	}

	public boolean actAttackSkill(BattleElements elements) {
		AttackSkill skill = (AttackSkill) elements.getActorSkill();
		switch (skill.type) {
			case Normal:
				normalAttack(elements, skill);
				break;
			case Special:
				break;
			default:
				break;
		}
		return true;
	}

	public boolean actAttackSkillOnClash(BattleElements elements) {
		AttackSkill skill = (AttackSkill) elements.getActorSkill();
		switch (skill.type) {
			case Normal:
				normalAttackOnClash(elements, skill);
				break;
			case Special:
				break;
			default:
				break;
		}
		return true;
	}

	public boolean normalAttack(BattleElements elements, AttackSkill skill) {
		CharacterEntity target = elements.target;
		CharacterEntity actor = elements.actor;

		target.mHp = target.mHp - skill.damage;

		elements.target = target;
		elements.actor = actor;
		return true;
	}

	public boolean normalAttackOnClash(BattleElements elements, AttackSkill skill) {
		CharacterEntity target = elements.target;
		CharacterEntity actor = elements.actor;

		AttackSkill targetSkill = (AttackSkill) target.usingSkill;
		target.mHp = target.mHp - (skill.damage - targetSkill.damage);

		elements.target = target;
		elements.actor = actor;
		return true;
	}

	public void consumeSkillPoint(BattleElements elements) {
		CharacterEntity actor = elements.actor;
		Skill skill = elements.getActorSkill();
		actor.mSp = actor.mSp - skill.necessaryPoint;
		elements.actor = actor;
	}

	public void chargeSkillPoint(BattleElements elements) {
		CharacterEntity actor = elements.actor;
		actor.mSp++;
		elements.actor = actor;
	}
}
