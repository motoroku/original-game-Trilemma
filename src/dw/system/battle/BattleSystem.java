package dw.system.battle;

import dw.skill.Skill;
import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.BattleResult;
import dw.system.entity.CharacterEntity;
import dw.system.entity.Enemy;

public class BattleSystem {

	BattleService battleService;
	public BattleElements battleElements;

	public String playerAction;
	public String enemyAction;

	public BattleSystem() {
		battleService = new BattleService();
		battleElements = new BattleElements();
	}

	/**
	 * 画面で決定された行動に基いて 1ターンの一連の処理を開始する
	 * @param buttonNum タップされたボタン
	 */
	public void StartBattle(int buttonNum) {
		battleElements.inputButton = buttonNum;
		// NPCの行動を決定するAIメソッドこのへんで実行する
		// NPCの行動を決定する
		battleElements = battleService.getAction(battleElements, BattleStatus.NPC);
		// プレイヤーの行動を設定する
		battleElements = battleService.getAction(battleElements, BattleStatus.PLAYER);
		// スキルの処理を行う
		battleElements = battleService.transactBattleTurn(battleElements);

		playerAction = battleElements.getPlayer().usingSkill.actionStatus.getActionStatusName();
		enemyAction = battleElements.getEnemy().usingSkill.actionStatus.getActionStatusName();

		// 1ターン終了時の初期化処理
		battleElements = battleService.turnEnd(battleElements);
	}

	public void EndTurn() {

	}

	public boolean isBattleEnd(boolean isEnd) {
		return isEnd;
	}

	public boolean isHaveNecessaryPoint(int buttonNum, CharacterEntity character) {
		return battleService.logic.isHaveNecessaryPoint(buttonNum, character);
	}
}
