package entity.skill;

import entity.BattleStatus;
import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.SkillType;
import entity.BattleStatus.TargetStatus;

public class Skill {

	public ActionStatus actionStatus;
	public TargetStatus target;
	public int necessarySkillPoint;
	public int point;
	public SkillType type;
	public String skillName;

	public Skill() {
	}

	public Skill(TargetStatus target, int point, int skillPoint, ActionStatus status, SkillType type, String skillName) {
		this.actionStatus = status;
		this.point = point;
		this.necessarySkillPoint = skillPoint;
		this.target = target;
		this.type = type;
		this.skillName = skillName;
	}

}
