package dw.system.battle;

import dw.skill.AttackSkill;
import dw.system.entity.CharacterEntity;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.BattleResult;

public class SkillManager {

	public BattleElements transactSkill(BattleElements elements) {
		CharacterEntity actor = elements.actor;
		CharacterEntity target = elements.target;

		if (actor.usingSkill.actionStatus == ActionStatus.çUåÇ) {
			if (elements.result == BattleResult.win) {
				AttackSkill skill = (AttackSkill) actor.usingSkill;
				target.mHp = target.mHp - skill.damage;
			}
			actor.mSp = actor.mSp - 1;
		} else if (actor.usingSkill.actionStatus == ActionStatus.ñhå‰) {
		} else if (actor.usingSkill.actionStatus == ActionStatus.É`ÉÉÅ[ÉW) {
			actor.mSp++;
		}

		elements.actor = actor;
		elements.target = target;

		return elements;
	}
}
