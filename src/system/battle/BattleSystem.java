package system.battle;

import Trilemma.CHARACTER;
import entity.BattleStatus;
import entity.CharacterEntity;
import entity.Skill;
import entity.BattleStatus.SelectedActionList;
import entity.Enemy;
import entity.Player;

public class BattleSystem {
	BattleService battleService = new BattleService();
	public BattleElements battleElements;

	// -------------------------------------------------------------------------------------------
	// 画面出力用
	public String playerAction;
	public String playerSkill;
	public String enemyAction;
	public String enemySkill;
	public String enemyActionRate;

	// -------------------------------------------------------------------------------------------

	public BattleSystem() {
		battleElements = new BattleElements();
		setEnemyActionRate();
	}

	/**
	 * 敵キャラクターのみ選択
	 * @param enemy 選択された敵キャラクター
	 */
	public BattleSystem(Player player, Enemy enemy) {
		battleElements = new BattleElements(player, enemy);
		setEnemyActionRate();
	}

	/**
	 * 画面で決定された行動に基いて 1ターンの一連の処理を開始する
	 * @param buttonNum タップされたボタン
	 */
	public void StartBattle(SelectedActionList selectedAction) {
		// 画面から入力されたボタンを設定する
		battleElements.inputButton = battleElements.getInputButton(selectedAction);
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
		playerAction = battleElements.playerTrunHistoryList.get(battleElements.playerTrunHistoryList.size() - 1).action
				.getValue();
		playerSkill = battleElements.playerTrunHistoryList.get(battleElements.playerTrunHistoryList.size() - 1).skill.skillName;
		enemyAction = battleElements.enemyTurnHistoryList.get(battleElements.enemyTurnHistoryList.size() - 1).action
				.getValue();
		enemySkill = battleElements.enemyTurnHistoryList.get(battleElements.enemyTurnHistoryList.size() - 1).skill.skillName;
		setEnemyActionRate();
		// -------------------------------------------------------------------------------------------

	}

	public void EndTurn() {

	}

	public boolean isBattleEnd(boolean isEnd) {
		return isEnd;
	}

	public boolean isHaveNecessaryPoint(SelectedActionList selectedAction, CharacterEntity character) {
		return battleService.isEnoughSkillPoint(character.skillList[selectedAction.getActionNo()], character);
	}

	public boolean isSetSkill(SelectedActionList selectedAction, BattleElements elements) {
		return battleService.isSetSkill(selectedAction, elements);
	}

	private void setEnemyActionRate() {
		String attackRate = String.valueOf(((Enemy) battleElements.getEnemy()).attackRate);
		String defenseRate = String.valueOf(((Enemy) battleElements.getEnemy()).defenseRate);
		String chargerate = String.valueOf(((Enemy) battleElements.getEnemy()).chargeRate);
		enemyActionRate = "攻撃確率：" + attackRate + " 防御確率：" + defenseRate + " チャージ確率：" + chargerate;
	}
}
