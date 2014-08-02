package dw.skill;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.SkillType;

public class Skill {

	public ActionStatus actionStatus;
	public String target;
	public int necessarySkillPoint;
	public int point;
	public SkillType type;

	public Skill() {
	}

	public Skill(String actor, int point, int skillPoint, ActionStatus status, SkillType type) {
		actionStatus = status;
		this.point = point;
		necessarySkillPoint = skillPoint;
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
		this.type = type;
	}

}
