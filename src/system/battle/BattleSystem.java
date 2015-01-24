package system.battle;

import android.content.Context;
import dao.DaoManager;
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
	Context context;
	DaoManager dao;

	// -------------------------------------------------------------------------------------------
	// 画面出力用
	public String playerAction;
	public String playerSkill;
	public String enemyAction;
	public String enemySkill;

	// -------------------------------------------------------------------------------------------

	public BattleSystem() {
		battleElements = new BattleElements();
	}

	/**
	 * 敵キャラクターのみ選択
	 * @param enemy 選択された敵キャラクター
	 */
	public BattleSystem(Player player, Enemy enemy, Context context) {
		battleElements = new BattleElements(player, enemy);
		this.context = context;
		dao = new DaoManager(context);
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

	}

	public void endBattle() {
		Enemy enemy = (Enemy) battleElements.getEnemy();
		int profitGold = enemy.gold;
		int profitExp = enemy.exp;

		// TODO お金が手に入る処理を追加する
		dao.addGold(profitGold);
		// TODO 経験値を取得する処理を実装する
		dao.addExp(profitExp);
		// TODO アイテムの取得処理を実装する
		item();
		// TODO　レベルアップの処理を実装する
		levelUp();
	}

	public void item() {

	}

	public void levelUp() {

	}

	// ------------------------------------------------------------------------------------------------------------
	// チェック系のメソッド
	public boolean isHaveNecessaryPoint(SelectedActionList selectedAction, CharacterEntity character) {
		return battleService.isEnoughSkillPoint(character.skillList[selectedAction.getActionNo()], character);
	}

	public boolean isBattleEnd() {
		return battleElements.getEnemy().currentHp <= 0;
	}

	public boolean isSetSkill(SelectedActionList selectedAction, BattleElements elements) {
		return battleService.isSetSkill(selectedAction, elements);
	}

	// ------------------------------------------------------------------------------------------------------------

}
