package entity.skill;

import Trilemma.M_SKILLTYPE;
import Trilemma.SKILL;
import entity.BattleStatus;
import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.BattleResult;
import entity.BattleStatus.TargetStatus;

public class Skill {

	public ActionStatus actionStatus;
	public TargetStatus target;
	public int skillPoint;
	public int effetPoint;
	public String skillName;
	public M_SKILLTYPE type;

	public Skill() {
	}

	public Skill(SKILL skill) {
		this.actionStatus = ActionStatus.fromValue(skill.getM_SKILLTYPE().getM_ACTIONSTATUS().getAction_status());
		this.effetPoint = skill.getEffect_point();
		this.skillPoint = skill.getSkill_point();
		this.skillName = skill.getSkill_name();
		this.type = skill.getM_SKILLTYPE();
		// TODO: targetを設定するように
		if (this.actionStatus.getValue().equals(ActionStatus.攻撃.getValue())) {
			this.target = TargetStatus.enemy;
		} else {
			this.target = TargetStatus.self;
		}
	}
}
