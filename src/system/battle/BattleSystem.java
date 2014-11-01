package system.battle;

import entity.BattleStatus;
import entity.CharacterEntity;
import entity.BattleStatus.SelectedActionList;

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
	public void StartBattle(SelectedActionList selectedAction) {
		// 画面から入力されたボタンを設定する
		battleElements.inputButton = selectedAction.getActionNo();
		// NPCの行動を決定する
		battleService.getAction(battleElements, BattleStatus.ENEMY);
		// プレイヤーの行動を設定する
		battleService.getAction(battleElements, BattleStatus.PLAYER);
		// スキルの処理を行う
		battleService.processBattleAction(battleElements, BattleStatus.PLAYER);
		battleService.processBattleAction(battleElements, BattleStatus.ENEMY);

		// -------------------------------------------------------------------------------------------
		// 画面出力用
		playerAction = battleElements.getPlayer().usingSkill.actionStatus.getActionStatusName();
		enemyAction = battleElements.getEnemy().usingSkill.actionStatus.getActionStatusName();
		// -------------------------------------------------------------------------------------------

		// 1ターン終了時の初期化処理
		battleService.endProcessing(battleElements);
	}

	public void EndTurn() {

	}

	public boolean isBattleEnd(boolean isEnd) {
		return isEnd;
	}

	public boolean isHaveNecessaryPoint(SelectedActionList skill, CharacterEntity character) {
		return battleService.isHaveNecessaryPoint(skill.getActionNo(), character);
	}
}
