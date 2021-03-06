package entity;

import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.SkillType;
import entity.BattleStatus.TargetStatus;
import Trilemma.SKILL;

public class Skill {

	public ActionStatus actionStatus;
	public TargetStatus target;
	public int skillPoint;
	public double effectPoint;
	public String skillName;
	public SkillType skillType;
	public int skillActionStatusId;

	public Skill() {
	}

	public Skill(SKILL skill) {
		this.actionStatus = ActionStatus.fromValue(skill.getM_SKILL_TYPE().getM_ACTION_STATUS().getAction_status());
		this.effectPoint = skill.getEffect_point().doubleValue() / 10;
		this.skillPoint = skill.getSkill_point();
		this.skillName = skill.getSkill_name();
		this.skillType = SkillType.getType(skill.getM_SKILL_TYPE().getSkill_type_name());
		this.skillActionStatusId = (int) (long) skill.getM_SKILL_TYPE().getAction_status_id();
		if (this.actionStatus.getValue().equals(ActionStatus.攻撃.getValue())) {
			this.target = TargetStatus.enemy;
		} else {
			this.target = TargetStatus.self;
		}
	}
}
