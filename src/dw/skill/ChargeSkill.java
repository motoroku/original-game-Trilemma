package dw.skill;

import dw.system.battle.BattleElements;
import dw.system.entity.BattleStatus.ActionStatus;

public class ChargeSkill extends Skill {
	public int chargePoint = 1;

	public ChargeSkill() {
		actionStatus = ActionStatus.�`���[�W;
	}

	public ChargeSkill(String name) {
		actionStatus = ActionStatus.�`���[�W;
		target = name;
	}

	public int getChargePoint() {
		return chargePoint;
	}
}
