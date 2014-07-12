package dw.system.battle;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.CharacterEntity;
import dw.system.entity.Enemy;

public class BattleService {
	BattleLogic logic = new BattleLogic();
	SkillManager mSkillManager = new SkillManager();

	/**
	 * 戦闘処理を行う
	 * @param elements
	 * @return
	 */
	public BattleElements transactBattleTurn(BattleElements elements) {
		ActionStatus playerAction = elements.getPlayer().usingSkill.actionStatus;
		ActionStatus npcAction = elements.getEnemy().usingSkill.actionStatus;
		// Playerのスキルを発動させて、結果を取得する
		elements.result = logic.decideActionResult(playerAction, npcAction);
		elements = logic.setTurn(elements, BattleStatus.PLAYER);
		elements = mSkillManager.transactSkill(elements);
		elements.setCharacters();
		// NPCのスキルを発動させて、結果を取得する
		elements.result = logic.decideActionResult(npcAction, playerAction);
		elements = logic.setTurn(elements, BattleStatus.NPC);
		elements = mSkillManager.transactSkill(elements);
		elements.setCharacters();
		return elements;
	}

	/**
	 * ターン終了時の処理を行う 。使用スキルを初期化する
	 * @param elements
	 * @return
	 */
	public BattleElements turnEnd(BattleElements elements) {
		elements.characterMap.get(BattleStatus.PLAYER).usingSkill = null;
		elements.characterMap.get(BattleStatus.NPC).usingSkill = null;

		elements.turnCount++;
		return elements;
	}

	/**
	 * 使用スキルを設定する
	 * @param elements
	 * @param actor
	 * @return
	 */
	public BattleElements getAction(BattleElements elements, String actor) {
		if (actor == BattleStatus.PLAYER) {
			elements.characterMap.get(BattleStatus.PLAYER).usingSkill = elements.characterMap.get(BattleStatus.PLAYER).mSkillList[elements.inputButton];
		} else if (actor == BattleStatus.NPC) {
			Enemy enemy = (Enemy) elements.characterMap.get(BattleStatus.NPC);
			int num = enemy.getEnemyAction();
			elements.getEnemy().usingSkill = enemy.mSkillList[num];
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
