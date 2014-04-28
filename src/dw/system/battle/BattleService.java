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

		if (playerAction == ActionStatus.�U�� && npcAction == ActionStatus.�h��) {
			elements.mResult = BattleResult.lose;
		} else if (playerAction == ActionStatus.�U�� && npcAction == ActionStatus.�`���[�W) {
			elements.mResult = BattleResult.win;
		} else if (playerAction == ActionStatus.�U�� && npcAction == ActionStatus.�U��) {
			elements.mResult = BattleResult.clash;
		} else if (playerAction == ActionStatus.�`���[�W && npcAction == ActionStatus.�U��) {
			elements.mResult = BattleResult.lose;
		} else if (playerAction == ActionStatus.�`���[�W && npcAction == ActionStatus.�h��) {
			elements.mResult = BattleResult.win;
		} else if (playerAction == ActionStatus.�h�� && npcAction == ActionStatus.�U��) {
			elements.mResult = BattleResult.win;
		} else if (playerAction == ActionStatus.�h�� && npcAction == ActionStatus.�`���[�W) {
			elements.mResult = BattleResult.lose;
		} else {
			elements.mResult = BattleResult.draw;
		}

		return elements;
	}

	public BattleElements transactBattleAction(BattleElements elements) {
		// Player�̃X�L���𔭓������āA���ʂ��擾����
		elements = elements.mPlayer.usingSkill.actSkill(elements, BattleStatus.PLAYER, BattleStatus.NPC);
		// NPC�̃X�L���𔭓������āA���ʂ��擾����
		elements = elements.mNpc.usingSkill.actSkill(elements, BattleStatus.NPC, BattleStatus.PLAYER);
		return elements;
	}
}
