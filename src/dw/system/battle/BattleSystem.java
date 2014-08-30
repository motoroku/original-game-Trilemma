package dw.system.battle;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.SelectActionList;
import dw.system.entity.CharacterEntity;

public class BattleSystem {
	BattleService battleService;
	public BattleElements battleElements;

	// -------------------------------------------------------------------------------------------
	// 画面出力用
	public String playerAction;
	public String enemyAction;

	// -------------------------------------------------------------------------------------------

	public BattleSystem() {
		battleService = new BattleService();
		battleElements = new BattleElements();
	}

	/**
	 * 画面で決定された行動に基いて 1ターンの一連の処理を開始する
	 * @param buttonNum タップされたボタン
	 */
	public void StartBattle(SelectActionList selectedAction) {
		// 画面から入力されたボタンを設定する
		battleElements.inputButton = selectedAction.getActionNo();
		// NPCの行動を決定する
		battleElements = battleService.getAction(battleElements, BattleStatus.NPC);
		// プレイヤーの行動を設定する
		battleElements = battleService.getAction(battleElements, BattleStatus.PLAYER);
		// スキルの処理を行う
		battleService.ActBattleAction(battleElements, BattleStatus.PLAYER);
		battleService.ActBattleAction(battleElements, BattleStatus.NPC);

		// -------------------------------------------------------------------------------------------
		// 画面出力用
		playerAction = battleElements.getPlayer().usingSkill.actionStatus.getActionStatusName();
		enemyAction = battleElements.getEnemy().usingSkill.actionStatus.getActionStatusName();
		// -------------------------------------------------------------------------------------------

		// 1ターン終了時の初期化処理
		battleService.turnEnd(battleElements);
	}

	public void EndTurn() {

	}

	public boolean isBattleEnd(boolean isEnd) {
		return isEnd;
	}

	public boolean isHaveNecessaryPoint(SelectActionList skill, CharacterEntity character) {
		return battleService.logic.isHaveNecessaryPoint(skill.getActionNo(), character);
	}
}
