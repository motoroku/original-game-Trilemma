package system.battle;

import entity.BattleStatus;
import entity.CharacterEntity;
import entity.Enemy;
import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.BattleResult;
import entity.skill.Skill;

public class BattleService {
	SkillManager mSkillManager = new SkillManager();

	/**
	 * プレイヤーとNPCの戦闘処理をまとめて行う
	 * @param elements
	 * @return
	 */
	public boolean ActBattleActions(BattleElements elements) {
		ActionStatus playerAction = elements.getPlayer().usingSkill.actionStatus;
		ActionStatus npcAction = elements.getEnemy().usingSkill.actionStatus;
		// Playerのスキルを発動させて、結果を取得する
		elements.result = decideActionResult(playerAction, npcAction);
		elements = setSkillActor(elements, BattleStatus.PLAYER);
		elements = mSkillManager.transactSkill(elements);
		elements.setCharacters();
		// NPCのスキルを発動させて、結果を取得する
		elements.result = decideActionResult(npcAction, playerAction);
		elements = setSkillActor(elements, BattleStatus.ENEMY);
		elements = mSkillManager.transactSkill(elements);
		elements.setCharacters();
		return true;
	}

	/**
	 * 戦闘処理を行う。
	 * @param elements
	 * @param actor 行動を行うキャラクター
	 * @return
	 */
	public boolean ActBattleAction(BattleElements elements, String actor) {
		ActionStatus actorAction;
		ActionStatus receiverAction;

		if (actor == BattleStatus.PLAYER) {
			actorAction = elements.getPlayer().usingSkill.actionStatus;
			receiverAction = elements.getEnemy().usingSkill.actionStatus;
		} else {
			actorAction = elements.getEnemy().usingSkill.actionStatus;
			receiverAction = elements.getPlayer().usingSkill.actionStatus;
		}

		elements.result = decideActionResult(actorAction, receiverAction);
		// スキル使用者とその対象を設定する
		elements = setSkillActor(elements, actor);
		// スキルを発動する処理を行う
		elements = mSkillManager.transactSkill(elements);
		// スキルの効果を適用した結果をエレメントのキャラクターに反映させる
		elements.setCharacters();
		// スキルの使用履歴を保存
		elements.setTurnHistory();

		return true;
	}

	/**
	 * ターン終了時の処理を行う 。使用スキルを初期化する
	 * @param elements
	 * @return
	 */
	public boolean turnEnd(BattleElements elements) {
		elements.characterMap.get(BattleStatus.PLAYER).usingSkill = null;
		elements.characterMap.get(BattleStatus.ENEMY).usingSkill = null;

		elements.turnCount++;
		return true;
	}

	/**
	 * 使用スキルを設定する
	 * @param elements
	 * @param actor
	 * @return
	 */
	public BattleElements getAction(BattleElements elements, String actor) {
		if (actor == BattleStatus.PLAYER) {
			elements.getPlayer().usingSkill = elements.getPlayer().skillList[elements.inputButton];
		} else if (actor == BattleStatus.ENEMY) {
			int num = ((Enemy) elements.getEnemy()).getEnemyAction();
			elements.getEnemy().usingSkill = elements.getEnemy().skillList[num];
			if (!isHaveNecessaryPoint(num, elements.getEnemy())) {
				elements = getAction(elements, BattleStatus.ENEMY);
			}
		}
		return elements;
	}

	/**
	 * 使用スキルの勝敗判定を行う
	 * @param playerAction
	 * @param npcAction
	 * @return
	 */
	public BattleResult decideActionResult(ActionStatus playerAction, ActionStatus npcAction) {
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
	public BattleElements setSkillActor(BattleElements elements, String actor) {
		if (actor == BattleStatus.PLAYER) {
			elements.setPlayerTurn();
		} else if (actor == BattleStatus.ENEMY) {
			elements.setEnemyTurn();
		}
		elements.target = setTarget(elements);
		return elements;
	}

	/**
	 * 使用スキルの対象を設定する
	 * @param elements
	 * @return
	 */
	private CharacterEntity setTarget(BattleElements elements) {
		Skill usingSkill = elements.actor.usingSkill;
		if (usingSkill.target == BattleStatus.PLAYER) {
			elements.setTargetPlayer();
		} else if (usingSkill.target == BattleStatus.ENEMY) {
			elements.setTargetEnemy();
		}
		return elements.target;
	}

	/**
	 * 使用スキルの必要スキルポイントを持っているかどうかを判定する
	 * @param buttonNum
	 * @param character
	 * @return
	 */
	public boolean isHaveNecessaryPoint(int buttonNum, CharacterEntity character) {
		Skill selectedSkill = character.skillList[buttonNum];
		if (character.sp >= selectedSkill.necessarySkillPoint) {
			return true;
		}
		return false;
	}
}
