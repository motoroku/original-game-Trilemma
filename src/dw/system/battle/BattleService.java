package dw.system.battle;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.BattleResult;
import dw.system.entity.CharacterEntity;
import dw.system.entity.Enemy;

public class BattleService {

	public BattleElements decideActionResult(BattleElements elements) {

		ActionStatus playerAction = elements.mPlayer.usingSkill.mActionStatus;
		ActionStatus npcAction = elements.mNpc.usingSkill.mActionStatus;

		if (playerAction == ActionStatus.攻撃 && npcAction == ActionStatus.防御) {
			elements.mResult = BattleResult.lose;
		} else if (playerAction == ActionStatus.攻撃 && npcAction == ActionStatus.チャージ) {
			elements.mResult = BattleResult.win;
		} else if (playerAction == ActionStatus.攻撃 && npcAction == ActionStatus.攻撃) {
			elements.mResult = BattleResult.clash;
		} else if (playerAction == ActionStatus.チャージ && npcAction == ActionStatus.攻撃) {
			elements.mResult = BattleResult.lose;
		} else if (playerAction == ActionStatus.チャージ && npcAction == ActionStatus.防御) {
			elements.mResult = BattleResult.win;
		} else if (playerAction == ActionStatus.防御 && npcAction == ActionStatus.攻撃) {
			elements.mResult = BattleResult.win;
		} else if (playerAction == ActionStatus.防御 && npcAction == ActionStatus.チャージ) {
			elements.mResult = BattleResult.lose;
		} else {
			elements.mResult = BattleResult.draw;
		}

		return elements;
	}

	public BattleElements transactBattleAction(BattleElements elements) {
		// Playerのスキルを発動させて、結果を取得する
		elements = elements.mPlayer.usingSkill.actSkill(elements, BattleStatus.PLAYER, BattleStatus.NPC);
		// NPCのスキルを発動させて、結果を取得する
		elements = elements.mNpc.usingSkill.actSkill(elements, BattleStatus.NPC, BattleStatus.PLAYER);
		return elements;
	}
}
