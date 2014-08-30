package dw.system.battle;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.SelectActionList;
import dw.system.entity.CharacterEntity;

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
	public void StartBattle(SelectActionList selectedAction) {
		// ��ʂ�����͂��ꂽ�{�^����ݒ肷��
		battleElements.inputButton = selectedAction.getActionNo();
		// NPC�̍s�������肷��
		battleElements = battleService.getAction(battleElements, BattleStatus.NPC);
		// �v���C���[�̍s����ݒ肷��
		battleElements = battleService.getAction(battleElements, BattleStatus.PLAYER);
		// �X�L���̏������s��
		battleService.ActBattleAction(battleElements, BattleStatus.PLAYER);
		battleService.ActBattleAction(battleElements, BattleStatus.NPC);

		// -------------------------------------------------------------------------------------------
		// ��ʏo�͗p
		playerAction = battleElements.getPlayer().usingSkill.actionStatus.getActionStatusName();
		enemyAction = battleElements.getEnemy().usingSkill.actionStatus.getActionStatusName();
		// -------------------------------------------------------------------------------------------

		// 1�^�[���I�����̏���������
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
