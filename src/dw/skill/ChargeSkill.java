package dw.skill;

import dw.system.battle.BattleElements;
import dw.system.entity.BattleStatus.ActionStatus;

public class ChargeSkill extends Skill {
	public int chargePoint = 1;

	public ChargeSkill() {
		actionStatus = ActionStatus.チャージ;
	}

	public ChargeSkill(String name) {
		actionStatus = ActionStatus.チャージ;
		target = name;
		necessaryPoint = 0;
	}

	public int getChargePoint() {
		return chargePoint;
	}
}
