package dw.skill;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;

public class AttackSkill extends Skill {

	public int damage;

	public AttackSkill() {
		actionStatus = ActionStatus.�U��;
	}

	public AttackSkill(String actor) {
		actionStatus = ActionStatus.�U��;
		damage = 10;
		necessaryPoint = 1;
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
	}

	public AttackSkill(String actor, int damage) {
		actionStatus = ActionStatus.�U��;
		this.damage = damage;
		necessaryPoint = 1;
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
	}
}
