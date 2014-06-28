package dw.skill;

import java.util.Map;

import dw.system.battle.BattleElements;
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
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
	}
}
