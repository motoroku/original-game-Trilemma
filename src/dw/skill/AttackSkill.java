package dw.skill;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.AttackSkillType;
import dw.system.entity.CharacterEntity;

public class AttackSkill extends Skill {

	public int damage;
	public static AttackSkillType type = AttackSkillType.Normal;

	/**
	 * コンストラクタ。
	 */
	public AttackSkill() {
		actionStatus = ActionStatus.攻撃;
	}

	/**
	 * コンストラクタ。ターゲットを設定できる。
	 * @param actor スキルを使うキャラクター。ターゲットはスキル使用者ではない方に設定される。
	 */
	public AttackSkill(String actor) {
		actionStatus = ActionStatus.攻撃;
		damage = 10;
		necessaryPoint = 1;
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
	}

	/**
	 * コンストラクタ。ターゲット、ダメージを設定できる。
	 * @param actor スキルを使うキャラクター。ターゲットはスキル使用者ではない方に設定される。
	 * @param damage このスキルダメージポイントに設定される。
	 */
	public AttackSkill(String actor, int damage) {
		actionStatus = ActionStatus.攻撃;
		this.damage = damage;
		necessaryPoint = 1;
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
	}

	/**
	 * コンストラクタ。ターゲット、ダメージ、必要スキルポイントを設定できる。
	 * @param actor スキルを使うキャラクター。ターゲットはスキル使用者ではない方に設定される。
	 * @param damage このスキルダメージポイントに設定される。
	 * @param point 必要なスキルポイントに設定される。
	 */
	public AttackSkill(String actor, int damage, int point) {
		actionStatus = ActionStatus.攻撃;
		this.damage = damage;
		necessaryPoint = point;
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
	}

	/**
	 * スキルの処理を行う。設定されているダメージ量を引数のターゲットのHPからマイナスする。
	 * @param target スキルの効果を適用するキャラクター
	 * @return スキルの効果が適用されたキャラクターが返される
	 */
	public CharacterEntity actAtackSkill(CharacterEntity target) {
		target.mHp = target.mHp - damage;
		return target;
	}

	/**
	 * 行動判定の結果がClashだった場合に利用することを想定したスキルの処理を行う。
	 * このスキルのダメージからターゲットの使うスキルのダメージを引いた分のダメージをターゲットに与える
	 * @param target スキルの効果を適用する
	 * @return　スキルの効果が適用されたキャラクターが返される
	 */
	public CharacterEntity actAttackSkillOnClash(CharacterEntity target) {
		AttackSkill skill = (AttackSkill) target.usingSkill;
		target.mHp = target.mHp - (damage - skill.damage);
		return target;
	}
}
