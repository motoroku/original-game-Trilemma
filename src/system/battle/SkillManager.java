package system.battle;

import utility.Cal;
import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.BattleResult;

public class SkillManager {
	public SkillCollection skillCollection = new SkillCollection();

	public BattleElements transactSkill(BattleElements elements) {
		// スキル使用者のスキルの種類で分岐
		// 攻撃スキルの場合
		if (elements.getActorSkill().actionStatus == ActionStatus.攻撃) {
			// スキル使用者の行動が成功している場合
			if (elements.result == BattleResult.win) {
				skillCollection.actAttackSkill(elements);
			}
			// 行動結果が相打ちだった場合
			else if (elements.result == BattleResult.clash) {
				// スキル使用者が勝利してる場合
				if (getClashResult(elements)) {
					skillCollection.actAttackSkillOnClash(elements);
				}
			}
			// スキルに設定されているポイントを消費する
			skillCollection.consumeSkillPoint(elements);
		}
		// 防御のスキルの場合
		else if (elements.getActorSkill().actionStatus == ActionStatus.防御) {
			// Skill skill = elements.getActorSkill();
		}
		// チャージスキルの場合
		else if (elements.getActorSkill().actionStatus == ActionStatus.チャージ) {
			// Skill skill = elements.getActorSkill();
			skillCollection.chargeSkillPoint(elements);
		}

		return elements;
	}

	private boolean getClashResult(BattleElements elements) {
		boolean result = false;

		// TODO: アクターとターゲットのスキルタイプによって判定方法が変わる予定
		result = Cal.calSkillMight(elements.actor, elements.getActorSkill()) > Cal.calSkillMight(elements.target,
				elements.getTargetSkill());

		return result;
	}
}