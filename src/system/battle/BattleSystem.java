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
	// ��ʏo�͗p
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
	 * �G�L�����N�^�[�̂ݑI��
	 * @param enemy �I�����ꂽ�G�L�����N�^�[
	 */
	public BattleSystem(Player player, Enemy enemy) {
		battleElements = new BattleElements(player, enemy);
		setEnemyActionRate();
	}

	/**
	 * ��ʂŌ��肳�ꂽ�s���Ɋ�� 1�^�[���̈�A�̏������J�n����
	 * @param buttonNum �^�b�v���ꂽ�{�^��
	 */
	public void StartBattle(SelectedActionList selectedAction) {
		// ��ʂ�����͂��ꂽ�{�^����ݒ肷��
		battleElements.inputButton = battleElements.getInputButton(selectedAction);
		// NPC�̍s�������肷��
		battleService.getAction(battleElements, BattleStatus.ENEMY);
		// �v���C���[�̍s����ݒ肷��
		battleService.getAction(battleElements, BattleStatus.PLAYER);
		// �X�L���̏������s��
		battleService.processBattleAction(battleElements, BattleStatus.PLAYER);
		battleService.processBattleAction(battleElements, BattleStatus.ENEMY);
		// 1�^�[���I�����̏���������
		battleService.endProcessing(battleElements);

		// -------------------------------------------------------------------------------------------
		// ��ʏo�͗p
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
		enemyActionRate = "�U���m���F" + attackRate + " �h��m���F" + defenseRate + " �`���[�W�m���F" + chargerate;
	}
}
