package dw.system.battle;

import dw.skill.AttackSkill;
import dw.skill.ChargeSkill;
import dw.skill.DefenseSkill;
import dw.system.entity.CharacterEntity;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.BattleResult;

public class SkillManager {

	public BattleElements transactSkill(BattleElements elements) {
		CharacterEntity actor = elements.actor;
		CharacterEntity target = elements.target;

		if (actor.usingSkill.actionStatus == ActionStatus.çUåÇ) {
			AttackSkill skill = (AttackSkill) actor.usingSkill;
			if (elements.result == BattleResult.win) {
				skill.actAtackSkill(target);
			} else if (elements.result == BattleResult.clash) {
				AttackSkill skill2 = (AttackSkill) target.usingSkill;
				if (skill.damage > skill2.damage) {
					skill.actAttackSkillOnClash(target);
				}
			}
			actor.mSp = actor.mSp - skill.necessaryPoint;
		} else if (actor.usingSkill.actionStatus == ActionStatus.ñhå‰) {
			DefenseSkill skill = (DefenseSkill) actor.usingSkill;
		} else if (actor.usingSkill.actionStatus == ActionStatus.É`ÉÉÅ[ÉW) {
			ChargeSkill skill = (ChargeSkill) actor.usingSkill;
			actor.mSp++;
		}

		elements.actor = actor;
		elements.target = target;

		return elements;
	}
}
