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
	 * ��ʂŌ��肳�ꂽ�s���Ɋ�� 1�^�[���̈�A�̏������J�n����
	 * @param buttonNum �^�b�v���ꂽ�{�^��
	 */
	public void StartBattle(int buttonNum) {
		mBattleElements.inputButton = buttonNum;
		// NPC�̍s�������肷��AI���\�b�h���̂ւ�Ŏ��s����
		// NPC�̍s�������肷��
		mBattleElements = mBattleService.getAction(mBattleElements, BattleStatus.NPC);
		// �v���C���[�̍s����ݒ肷��
		mBattleElements = mBattleService.getAction(mBattleElements, BattleStatus.PLAYER);
		// �X�L���̏������s��
		mBattleElements = mBattleService.transactBattleTurn(mBattleElements);

		playerAction = mBattleElements.getPlayer().usingSkill.actionStatus.getActionStatusName();
		enemyAction = mBattleElements.getEnemy().usingSkill.actionStatus.getActionStatusName();

		// 1�^�[���I�����̏���������
		mBattleElements = mBattleService.resetAction(mBattleElements);
	}

	public void EndTurn() {

	}

	public boolean isBattleEnd(boolean isEnd) {
		return isEnd;
	}
}
