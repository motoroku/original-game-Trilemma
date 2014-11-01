package entity;

import entity.BattleStatus.ActionStatus;
import entity.skill.Skill;

public class TurnAction {
	public ActionStatus action;
	public Skill skill;

	public void setTurnAction(Skill skill) {
		this.skill = skill;
		this.action = skill.actionStatus;
	}

}
