package dw.system.battle;

import dw.skill.Skill;
import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.BattleResult;
import dw.system.entity.CharacterEntity;

public class BattleLogic {

	/**
	 * 使用スキルの勝敗判定を行う
	 * @param playerAction
	 * @param npcAction
	 * @return
	 */
	public BattleResult decideActionResult(ActionStatus playerAction, ActionStatus npcAction) {
		BattleResult result;

		if (playerAction == ActionStatus.攻撃 && npcAction == ActionStatus.防御) {
			result = BattleResult.lose;
		} else if (playerAction == ActionStatus.攻撃 && npcAction == ActionStatus.チャージ) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.攻撃 && npcAction == ActionStatus.攻撃) {
			result = BattleResult.clash;
		} else if (playerAction == ActionStatus.チャージ && npcAction == ActionStatus.攻撃) {
			result = BattleResult.lose;
		} else if (playerAction == ActionStatus.チャージ && npcAction == ActionStatus.防御) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.防御 && npcAction == ActionStatus.攻撃) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.防御 && npcAction == ActionStatus.チャージ) {
			result = BattleResult.lose;
		} else {
			result = BattleResult.draw;
		}

		return result;
	}

	/**
	 * 行動するキャラクターとスキルの対象を設定する
	 * @param elements
	 * @param actor
	 * @return
	 */
	public BattleElements setSkillActor(BattleElements elements, String actor) {
		if (actor == BattleStatus.PLAYER) {
			elements.setPlayerTurn();
		} else if (actor == BattleStatus.NPC) {
			elements.setEnemyTurn();
		}
		elements.target = setTarget(elements);
		return elements;
	}

	/**
	 * 使用スキルの対象を設定する
	 * @param elements
	 * @return
	 */
	private CharacterEntity setTarget(BattleElements elements) {
		Skill usingSkill = elements.actor.usingSkill;
		if (usingSkill.target == BattleStatus.PLAYER) {
			elements.setTargetPlayer();
		} else if (usingSkill.target == BattleStatus.NPC) {
			elements.setTargetEnemy();
		}
		return elements.target;
	}

	/**
	 * 使用スキルの必要スキルポイントを持っているかどうかを判定する
	 * @param buttonNum
	 * @param character
	 * @return
	 */
	public boolean isHaveNecessaryPoint(int buttonNum, CharacterEntity character) {
		Skill selectedSkill = character.mSkillList[buttonNum];
		if (character.mSp >= selectedSkill.necessaryPoint) {
			return true;
		}
		return false;
	}
}
