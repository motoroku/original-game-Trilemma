package dw.skill;

import dw.system.entity.BattleStatus.ActionStatus;

public class DefenseSkill extends Skill {
	public DefenseSkill() {
		actionStatus = ActionStatus.�h��;
	}

	public DefenseSkill(String name) {
		actionStatus = ActionStatus.�h��;
		target = name;
		necessaryPoint = 0;
	}
}
