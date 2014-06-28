package dw.system.battle;

import java.util.Map;

import dw.skill.Skill;
import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.BattleResult;
import dw.system.entity.CharacterEntity;

public class BattleLogic {

	/**
	 * 
	 * @param playerAction
	 * @param npcAction
	 * @return
	 */
	public BattleResult decideActionResult(ActionStatus playerAction, ActionStatus npcAction) {
		BattleResult result;

		if (playerAction == ActionStatus.çUåÇ && npcAction == ActionStatus.ñhå‰) {
			result = BattleResult.lose;
		} else if (playerAction == ActionStatus.çUåÇ && npcAction == ActionStatus.É`ÉÉÅ[ÉW) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.çUåÇ && npcAction == ActionStatus.çUåÇ) {
			result = BattleResult.clash;
		} else if (playerAction == ActionStatus.É`ÉÉÅ[ÉW && npcAction == ActionStatus.çUåÇ) {
			result = BattleResult.lose;
		} else if (playerAction == ActionStatus.É`ÉÉÅ[ÉW && npcAction == ActionStatus.ñhå‰) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.ñhå‰ && npcAction == ActionStatus.çUåÇ) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.ñhå‰ && npcAction == ActionStatus.É`ÉÉÅ[ÉW) {
			result = BattleResult.lose;
		} else {
			result = BattleResult.draw;
		}

		return result;
	}

	public BattleElements setTurn(BattleElements elements, String actor) {
		if (actor == BattleStatus.PLAYER) {
			elements.setPlayerTurn();
		} else if (actor == BattleStatus.NPC) {
			elements.setEnemyTurn();
		}
		elements.target = setTarget(elements);
		return elements;
	}

	private CharacterEntity setTarget(BattleElements elements) {
		Skill usingSkill = elements.actor.usingSkill;
		if (usingSkill.target == BattleStatus.PLAYER) {
			elements.setTargetPlayer();
		} else if (usingSkill.target == BattleStatus.NPC) {
			elements.setTargetPlayer();
		}
		return elements.target;
	}
}
