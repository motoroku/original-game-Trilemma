package dw.system.battle;

import dw.skill.Skill;
import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.BattleResult;
import dw.system.entity.CharacterEntity;
import dw.system.entity.Enemy;

public class BattleSystem {

	BattleService battleService;
	public BattleElements battleElements;

	public String playerAction;
	public String enemyAction;

	public BattleSystem() {
		battleService = new BattleService();
		battleElements = new BattleElements();
	}

	/**
	 * ��ʂŌ��肳�ꂽ�s���Ɋ�� 1�^�[���̈�A�̏������J�n����
	 * @param buttonNum �^�b�v���ꂽ�{�^��
	 */
	public void StartBattle(int buttonNum) {
		battleElements.inputButton = buttonNum;
		// NPC�̍s�������肷��AI���\�b�h���̂ւ�Ŏ��s����
		// NPC�̍s�������肷��
		battleElements = battleService.getAction(battleElements, BattleStatus.NPC);
		// �v���C���[�̍s����ݒ肷��
		battleElements = battleService.getAction(battleElements, BattleStatus.PLAYER);
		// �X�L���̏������s��
		battleElements = battleService.transactBattleTurn(battleElements);

		playerAction = battleElements.getPlayer().usingSkill.actionStatus.getActionStatusName();
		enemyAction = battleElements.getEnemy().usingSkill.actionStatus.getActionStatusName();

		// 1�^�[���I�����̏���������
		battleElements = battleService.turnEnd(battleElements);
	}

	public void EndTurn() {

	}

	public boolean isBattleEnd(boolean isEnd) {
		return isEnd;
	}

	public boolean isHaveNecessaryPoint(int buttonNum, CharacterEntity character) {
		return battleService.logic.isHaveNecessaryPoint(buttonNum, character);
	}
}
