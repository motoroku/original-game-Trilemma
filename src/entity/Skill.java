package entity;

import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.TargetStatus;
import Trilemma.M_SKILLTYPE;
import Trilemma.SKILL;

public class Skill {

	public ActionStatus actionStatus;
	public TargetStatus target;
	public int skillPoint;
	public double effectPoint;
	public String skillName;
	public M_SKILLTYPE type;

	public Skill() {
	}

	public Skill(SKILL skill) {
		this.actionStatus = ActionStatus.fromValue(skill.getM_SKILLTYPE().getM_ACTIONSTATUS().getAction_status());
		this.effectPoint = skill.getEffect_point().doubleValue() / 10;
		this.skillPoint = skill.getSkill_point();
		this.skillName = skill.getSkill_name();
		this.type = skill.getM_SKILLTYPE();
		if (this.actionStatus.getValue().equals(ActionStatus.攻撃.getValue())) {
			this.target = TargetStatus.enemy;
		} else {
			this.target = TargetStatus.self;
		}
	}
}
