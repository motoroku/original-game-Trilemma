package dw.system.entity;

import dw.system.battle.BattleElements;
import dw.system.entity.BattleStatus.ActionStatus;

public class ChargeSkill extends Skill {

	public ChargeSkill() {
		mActionStatus = ActionStatus.É`ÉÉÅ[ÉW;
	}

	@Override
	public BattleElements actSkill(BattleElements elements, String actor, String receiver) {
		// TODO Auto-generated method stub

		CharacterEntity actorEntity = elements.mCharacterMap.get(actor);

		actorEntity.mSp++;

		elements.mCharacterMap.put(actor, actorEntity);

		return elements;
	}
}
