package dw.system.battle;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.CharacterEntity;
import dw.system.entity.Enemy;

public class BattleService {
	BattleLogic logic = new BattleLogic();
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
		elements.result = logic.decideActionResult(playerAction, npcAction);
		elements = logic.setSkillActor(elements, BattleStatus.PLAYER);
		elements = mSkillManager.transactSkill(elements);
		elements.setCharacters();
		// NPCのスキルを発動させて、結果を取得する
		elements.result = logic.decideActionResult(npcAction, playerAction);
		elements = logic.setSkillActor(elements, BattleStatus.NPC);
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

		elements.result = logic.decideActionResult(actorAction, receiverAction);
		// スキルの対象を設定する
		elements = logic.setSkillActor(elements, actor);
		// スキルを発動する処理を行う
		elements = mSkillManager.transactSkill(elements);
		// スキルの効果を適用した結果をエレメントのキャラクターに反映させる
		elements.setCharacters();

		return true;
	}

	/**
	 * ターン終了時の処理を行う 。使用スキルを初期化する
	 * @param elements
	 * @return
	 */
	public boolean turnEnd(BattleElements elements) {
		elements.characterMap.get(BattleStatus.PLAYER).usingSkill = null;
		elements.characterMap.get(BattleStatus.NPC).usingSkill = null;

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
		} else if (actor == BattleStatus.NPC) {
			int num = ((Enemy) elements.getEnemy()).getEnemyAction();
			elements.getEnemy().usingSkill = elements.getEnemy().skillList[num];
			if (!logic.isHaveNecessaryPoint(num, elements.getEnemy())) {
				elements = getAction(elements, BattleStatus.NPC);
			}
		}
		return elements;
	}

	/**
	 * スキルを使用するのに必要なスキルポイントを持っているかどうかを判定する
	 * @param buttonNum
	 * @param character
	 * @return
	 */
	public boolean isHaveNecessaryPoint(int buttonNum, CharacterEntity character) {
		return logic.isHaveNecessaryPoint(buttonNum, character);
	}
}
