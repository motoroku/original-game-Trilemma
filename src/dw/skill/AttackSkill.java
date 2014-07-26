package dw.skill;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.AttackSkillType;

public class AttackSkill extends Skill {

	public int damage;
	public AttackSkillType type;

	/**
	 * コンストラクタ。
	 */
	public AttackSkill() {
		actionStatus = ActionStatus.攻撃;
	}

	/**
	 * コンストラクタ
	 * @param actor スキルの使用者
	 */
	public AttackSkill(String actor) {
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
		damage = 10;
		type = AttackSkillType.Normal;
		necessaryPoint = 1;
	}

	/**
	 * コンストラクタ。ターゲット、ダメージ、必要スキルポイントを設定できる。
	 * @param actor スキルを使うキャラクター。ターゲットはスキル使用者ではない方に設定される。
	 * @param damage このスキルダメージポイントに設定される。
	 * @param point 必要なスキルポイントに設定される。
	 */
	public AttackSkill(String actor, int damage, int point, AttackSkillType type) {
		actionStatus = ActionStatus.攻撃;
		this.damage = damage;
		necessaryPoint = point;
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
		this.type = type;
	}

}
