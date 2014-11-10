package system.battle;

import entity.BattleStatus;
import entity.CharacterEntity;
import entity.BattleStatus.SelectedActionList;
import entity.Enemy;

public class BattleSystem {
	BattleService battleService;
	public BattleElements battleElements;

	// -------------------------------------------------------------------------------------------
	// 画面出力用
	public String playerAction;
	public String enemyAction;
	public String enemyActionRate;

	// -------------------------------------------------------------------------------------------

	public BattleSystem() {
		battleService = new BattleService();
		battleElements = new BattleElements();
		setEnemyActionRate();
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
		// 1ターン終了時の初期化処理
		battleService.endProcessing(battleElements);

		// -------------------------------------------------------------------------------------------
		// 画面出力用
		playerAction = battleElements.playerTrunHistoryList.get(battleElements.playerTrunHistoryList.size() - 1).action.getActionStatusName();
		enemyAction = battleElements.enemyTurnHistoryList.get(battleElements.enemyTurnHistoryList.size() - 1).action.getActionStatusName();
		setEnemyActionRate();
		// -------------------------------------------------------------------------------------------

	}

	public void EndTurn() {

	}

	public boolean isBattleEnd(boolean isEnd) {
		return isEnd;
	}

	public boolean isHaveNecessaryPoint(SelectedActionList selectedSkill, CharacterEntity character) {
		return battleService.isEnoughSkillPoint(character.skillList[selectedSkill.getActionNo()], character);
	}

	private void setEnemyActionRate() {
		String attackRate = String.valueOf(((Enemy) battleElements.getEnemy()).attackRate);
		String defenseRate = String.valueOf(((Enemy) battleElements.getEnemy()).defenseRate);
		String chargerate = String.valueOf(((Enemy) battleElements.getEnemy()).chargeRate);
		enemyActionRate = "攻撃確率：" + attackRate + " 防御確率：" + defenseRate + " チャージ確率：" + chargerate;
	}
}
