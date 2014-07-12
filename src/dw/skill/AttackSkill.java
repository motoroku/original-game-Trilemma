package dw.skill;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;

public class AttackSkill extends Skill {

	public int damage;

	public AttackSkill() {
		actionStatus = ActionStatus.çUåÇ;
	}

	public AttackSkill(String actor) {
		actionStatus = ActionStatus.çUåÇ;
		damage = 10;
		necessaryPoint = 1;
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
	}

	public AttackSkill(String actor, int damage) {
		actionStatus = ActionStatus.çUåÇ;
		this.damage = damage;
		necessaryPoint = 1;
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
	}
}
