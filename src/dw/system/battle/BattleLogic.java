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

		if (playerAction == ActionStatus.�U�� && npcAction == ActionStatus.�h��) {
			result = BattleResult.lose;
		} else if (playerAction == ActionStatus.�U�� && npcAction == ActionStatus.�`���[�W) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.�U�� && npcAction == ActionStatus.�U��) {
			result = BattleResult.clash;
		} else if (playerAction == ActionStatus.�`���[�W && npcAction == ActionStatus.�U��) {
			result = BattleResult.lose;
		} else if (playerAction == ActionStatus.�`���[�W && npcAction == ActionStatus.�h��) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.�h�� && npcAction == ActionStatus.�U��) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.�h�� && npcAction == ActionStatus.�`���[�W) {
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
