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
		necessaryPoint = 0;
	}

	public int getChargePoint() {
		return chargePoint;
	}
}
