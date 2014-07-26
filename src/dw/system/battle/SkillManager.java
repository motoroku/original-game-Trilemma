package dw.system.battle;

import dw.skill.AttackSkill;
import dw.skill.skillCollection;
import dw.skill.ChargeSkill;
import dw.skill.DefenseSkill;
import dw.system.entity.CharacterEntity;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.BattleResult;

public class SkillManager {
	public skillCollection skillCollection = new skillCollection();

	public BattleElements transactSkill(BattleElements elements) {
		// スキル使用者のスキルの種類で分岐
		// 攻撃スキルの場合
		if (elements.getActorSkill().actionStatus == ActionStatus.攻撃) {
			AttackSkill actorSkill = (AttackSkill) elements.getActorSkill();
			// スキル使用者の行動が成功している場合
			if (elements.result == BattleResult.win) {
				skillCollection.actAttackSkill(elements);
			}
			// 行動結果が相打ちだった場合
			else if (elements.result == BattleResult.clash) {
				AttackSkill targetSkill = (AttackSkill) elements.getTargetSkill();
				// スキル使用者が勝利してる場合
				if (actorSkill.damage > targetSkill.damage) {
					skillCollection.actAttackSkillOnClash(elements);
				}
			}
			// スキルに設定されているポイントを消費する
			skillCollection.consumeSkillPoint(elements);
		}
		// 防御のスキルの場合
		else if (elements.getActorSkill().actionStatus == ActionStatus.防御) {
			DefenseSkill skill = (DefenseSkill) elements.getActorSkill();
		}
		// チャージスキルの場合
		else if (elements.getActorSkill().actionStatus == ActionStatus.チャージ) {
			ChargeSkill skill = (ChargeSkill) elements.getActorSkill();
			skillCollection.chargeSkillPoint(elements);
		}

		return elements;
	}
}
