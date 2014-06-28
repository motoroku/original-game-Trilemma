package dw.system.battle;

import dw.skill.Skill;
import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.BattleResult;
import dw.system.entity.CharacterEntity;
import dw.system.entity.Enemy;

public class BattleSystem {

	BattleService mBattleService;
	public BattleElements mBattleElements;

	public String playerAction;
	public String enemyAction;

	public BattleSystem() {
		mBattleService = new BattleService();
		mBattleElements = new BattleElements();
	}

	/**
	 * 画面で決定された行動に基いて 1ターンの一連の処理を開始する
	 * @param buttonNum タップされたボタン
	 */
	public void StartBattle(int buttonNum) {
		mBattleElements.inputButton = buttonNum;
		// NPCの行動を決定するAIメソッドこのへんで実行する
		// NPCの行動を決定する
		mBattleElements = mBattleService.getAction(mBattleElements, BattleStatus.NPC);
		// プレイヤーの行動を設定する
		mBattleElements = mBattleService.getAction(mBattleElements, BattleStatus.PLAYER);
		// スキルの処理を行う
		mBattleElements = mBattleService.transactBattleTurn(mBattleElements);

		playerAction = mBattleElements.getPlayer().usingSkill.actionStatus.getActionStatusName();
		enemyAction = mBattleElements.getEnemy().usingSkill.actionStatus.getActionStatusName();

		// 1ターン終了時の初期化処理
		mBattleElements = mBattleService.resetAction(mBattleElements);
	}

	public void EndTurn() {

	}

	public boolean isBattleEnd(boolean isEnd) {
		return isEnd;
	}
}
