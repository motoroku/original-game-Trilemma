package dw.system.battle;

import dw.system.entity.BattleStatus.BattleResult;
import dw.system.entity.CharacterEntity;
import dw.system.entity.Enemy;
import dw.system.entity.Skill;

public class BattleLogic {

	BattleService mBattleService;
	public BattleElements mBattleElements;

	public BattleLogic() {
		mBattleService = new BattleService();
		mBattleElements = new BattleElements();
	}

	/**
	 * 画面で決定された行動に基いて 1ターンの一連の処理を開始する
	 * @param buttonNum タップされたボタン
	 */
	public void StartBattle(int buttonNum) {
		// NPCの行動を決定するAIメソッドこのへんで実行する
		// NPCの行動を決定する
		mBattleElements.mNpc.usingSkill = mBattleElements.mNpc.mSkillList[11];

		// プレイヤーの行動を設定する
		mBattleElements.mPlayer.usingSkill = mBattleElements.mPlayer.mSkillList[buttonNum];
		// プレイヤーとNPCの行動で勝敗判定を行う
		mBattleElements = mBattleService.decideActionResult(mBattleElements);
		// 勝敗によってスキルの処理を行う
		mBattleElements = mBattleService.transactBattleAction(mBattleElements);
		// 1ターン終了時の初期化処理
		mBattleElements.mPlayer.usingSkill = null;
		mBattleElements.mNpc.usingSkill = null;
	}

	public void EndTurn() {

	}

	public boolean isBattleEnd(boolean isEnd) {
		return isEnd;
	}
}
