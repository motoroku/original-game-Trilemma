package dw.skill;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.CharacterEntity;

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

	public AttackSkill(String actor, int damage, int point) {
		actionStatus = ActionStatus.�U��;
		this.damage = damage;
		necessaryPoint = point;
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
	}

	public CharacterEntity actAtackSkill(CharacterEntity target) {
		target.mHp = target.mHp - damage;
		return target;
	}

	public CharacterEntity actAttackSkillOnClash(CharacterEntity target) {
		AttackSkill skill = (AttackSkill) target.usingSkill;
		target.mHp = target.mHp - (damage - skill.damage);
		return target;
	}
}
