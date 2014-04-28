package dw.system.entity;

import java.util.Map;

import dw.system.battle.BattleElements;
import dw.system.entity.BattleStatus.ActionStatus;

public class AttackSkill extends Skill {

	public AttackSkill() {
		mActionStatus = ActionStatus.çUåÇ;
	}

	@Override
	public BattleElements actSkill(BattleElements elements, String actor, String receiver) {
		// TODO Auto-generated method stub

		CharacterEntity actorEntity = elements.mCharacterMap.get(actor);
		CharacterEntity receiverEntity = elements.mCharacterMap.get(receiver);

		if (elements.mResult == BattleStatus.BattleResult.win) {
			receiverEntity.mHp = receiverEntity.mHp - 10;
		}

		elements.mCharacterMap.put(actor, actorEntity);
		elements.mCharacterMap.put(receiver, receiverEntity);
		return elements;
	}
}
