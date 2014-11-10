package system.battle;

import entity.BattleStatus;
import entity.CharacterEntity;
import entity.Enemy;
import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.BattleResult;
import entity.BattleStatus.TargetStatus;
import entity.skill.Skill;

public class BattleService {
	SkillManager mSkillManager = new SkillManager();

	/**
	 * 使用スキルを設定する
	 * @param elements
	 * @param actor
	 * @return
	 */
	public void getAction(BattleElements elements, String actor) {
		if (actor == BattleStatus.PLAYER) {
			elements.getPlayer().usingSkill = elements.getPlayer().skillList[elements.inputButton];
		} else if (actor == BattleStatus.ENEMY) {
			int num = ((Enemy) elements.getEnemy()).getEnemyAction(elements);
			elements.getEnemy().usingSkill = elements.getEnemy().skillList[num];
			if (!isEnoughSkillPoint(elements.getEnemy().usingSkill, elements.getEnemy())) {
				getAction(elements, BattleStatus.ENEMY);
			}
		}
	}

	/**
	 * 使用スキルの必要スキルポイントを持っているかどうかを判定する
	 * @param buttonNum
	 * @param character
	 * @return
	 */
	public boolean isEnoughSkillPoint(Skill skill, CharacterEntity character) {
		return character.sp >= skill.necessarySkillPoint;
	}

	/**
	 * 戦闘処理を行う。
	 * @param elements
	 * @param actor 行動を行うキャラクター
	 * @return
	 */
	public boolean processBattleAction(BattleElements elements, String actor) {
		ActionStatus actorAction;
		ActionStatus receiverAction;

		if (actor == BattleStatus.PLAYER) {
			actorAction = elements.getPlayer().usingSkill.actionStatus;
			receiverAction = elements.getEnemy().usingSkill.actionStatus;
		} else {
			actorAction = elements.getEnemy().usingSkill.actionStatus;
			receiverAction = elements.getPlayer().usingSkill.actionStatus;
		}

		// 使用スキルの勝敗判定を行う
		elements.result = getActionResult(actorAction, receiverAction);
		// スキル使用者とその対象を設定する
		setSkillActor(elements, actor);
		// スキルを発動する処理を行う
		elements = mSkillManager.transactSkill(elements);
		// スキルの効果を適用した結果をエレメントのキャラクターに反映させる
		elements.applyBattleResult();
		// スキルの使用履歴を保存
		elements.setTurnHistory();

		return true;
	}

	/**
	 * ターン終了時の処理を行う 。戦闘要素の初期化。
	 * @param elements
	 * @return
	 */
	public boolean endProcessing(BattleElements elements) {
		elements.characterMap.get(BattleStatus.PLAYER).usingSkill = null;
		elements.characterMap.get(BattleStatus.ENEMY).usingSkill = null;

		elements.actor = null;
		elements.target = null;

		((Enemy) elements.getEnemy()).resetRate(elements);

		elements.turnCount++;
		return true;
	}

	// ------------------------------------------------------------------------------------------------------
	// Private Method

	/**
	 * 使用スキルの勝敗判定を行う
	 * @param playerAction
	 * @param npcAction
	 * @return
	 */
	private BattleResult getActionResult(ActionStatus playerAction, ActionStatus npcAction) {
		BattleResult result;

		if (playerAction == ActionStatus.攻撃 && npcAction == ActionStatus.防御) {
			result = BattleResult.lose;
		} else if (playerAction == ActionStatus.攻撃 && npcAction == ActionStatus.チャージ) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.攻撃 && npcAction == ActionStatus.攻撃) {
			result = BattleResult.clash;
		} else if (playerAction == ActionStatus.チャージ && npcAction == ActionStatus.攻撃) {
			result = BattleResult.lose;
		} else if (playerAction == ActionStatus.チャージ && npcAction == ActionStatus.防御) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.防御 && npcAction == ActionStatus.攻撃) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.防御 && npcAction == ActionStatus.チャージ) {
			result = BattleResult.lose;
		} else {
			result = BattleResult.draw;
		}

		return result;
	}

	/**
	 * 行動するキャラクターとスキルの対象を設定する
	 * @param elements
	 * @param actor
	 * @return
	 */
	private void setSkillActor(BattleElements elements, String actor) {
		if (actor == BattleStatus.PLAYER) {
			elements.setPlayerTurn();
		} else if (actor == BattleStatus.ENEMY) {
			elements.setEnemyTurn();
		}
		setTarget(elements);
	}

	/**
	 * 使用スキルの対象を設定する
	 * @param elements
	 * @return
	 */
	private void setTarget(BattleElements elements) {
		Skill usingSkill = elements.actor.usingSkill;
		String actor = elements.actor.characterType;

		if (actor == BattleStatus.PLAYER) {
			if (usingSkill.target == TargetStatus.self) {
				elements.setTargetPlayer();
			} else if (usingSkill.target == TargetStatus.enemy) {
				elements.setTargetEnemy();
			}
		} else if (actor == BattleStatus.ENEMY) {
			if (usingSkill.target == TargetStatus.self) {
				elements.setTargetEnemy();
			} else if (usingSkill.target == TargetStatus.enemy) {
				elements.setTargetPlayer();
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
}
