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

		if (playerAction == ActionStatus.UŒ‚ && npcAction == ActionStatus.–hŒä) {
			result = BattleResult.lose;
		} else if (playerAction == ActionStatus.UŒ‚ && npcAction == ActionStatus.ƒ`ƒƒ[ƒW) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.UŒ‚ && npcAction == ActionStatus.UŒ‚) {
			result = BattleResult.clash;
		} else if (playerAction == ActionStatus.ƒ`ƒƒ[ƒW && npcAction == ActionStatus.UŒ‚) {
			result = BattleResult.lose;
		} else if (playerAction == ActionStatus.ƒ`ƒƒ[ƒW && npcAction == ActionStatus.–hŒä) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.–hŒä && npcAction == ActionStatus.UŒ‚) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.–hŒä && npcAction == ActionStatus.ƒ`ƒƒ[ƒW) {
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
			elements.setTargetEnemy();
		}
		return elements.target;
	}

	public boolean isHaveNecessaryPoint(int buttonNum, CharacterEntity character) {
		Skill selectedSkill = character.mSkillList[buttonNum];
		if (character.mSp >= selectedSkill.necessaryPoint) {
			return true;
		}
		return false;
	}
}
