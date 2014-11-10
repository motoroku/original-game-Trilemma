package system.battle;

import entity.BattleStatus;
import entity.CharacterEntity;
import entity.BattleStatus.SelectedActionList;
import entity.Enemy;

public class BattleSystem {
	BattleService battleService;
	public BattleElements battleElements;

	// -------------------------------------------------------------------------------------------
	// ��ʏo�͗p
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
	 * ��ʂŌ��肳�ꂽ�s���Ɋ�� 1�^�[���̈�A�̏������J�n����
	 * @param buttonNum �^�b�v���ꂽ�{�^��
	 */
	public void StartBattle(SelectedActionList selectedAction) {
		// ��ʂ�����͂��ꂽ�{�^����ݒ肷��
		battleElements.inputButton = selectedAction.getActionNo();
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
		enemyActionRate = "�U���m���F" + attackRate + " �h��m���F" + defenseRate + " �`���[�W�m���F" + chargerate;
	}
}
