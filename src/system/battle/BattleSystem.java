package system.battle;

import entity.BattleStatus;
import entity.CharacterEntity;
import entity.BattleStatus.SelectedActionList;

public class BattleSystem {
	BattleService battleService;
	public BattleElements battleElements;

	// -------------------------------------------------------------------------------------------
	// ��ʏo�͗p
	public String playerAction;
	public String enemyAction;

	// -------------------------------------------------------------------------------------------

	public BattleSystem() {
		battleService = new BattleService();
		battleElements = new BattleElements();
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

		// -------------------------------------------------------------------------------------------
		// ��ʏo�͗p
		playerAction = battleElements.getPlayer().usingSkill.actionStatus.getActionStatusName();
		enemyAction = battleElements.getEnemy().usingSkill.actionStatus.getActionStatusName();
		// -------------------------------------------------------------------------------------------

		// 1�^�[���I�����̏���������
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
