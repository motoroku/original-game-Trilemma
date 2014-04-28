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
	 * ��ʂŌ��肳�ꂽ�s���Ɋ�� 1�^�[���̈�A�̏������J�n����
	 * @param buttonNum �^�b�v���ꂽ�{�^��
	 */
	public void StartBattle(int buttonNum) {
		// NPC�̍s�������肷��AI���\�b�h���̂ւ�Ŏ��s����
		// NPC�̍s�������肷��
		mBattleElements.mNpc.usingSkill = mBattleElements.mNpc.mSkillList[11];

		// �v���C���[�̍s����ݒ肷��
		mBattleElements.mPlayer.usingSkill = mBattleElements.mPlayer.mSkillList[buttonNum];
		// �v���C���[��NPC�̍s���ŏ��s������s��
		mBattleElements = mBattleService.decideActionResult(mBattleElements);
		// ���s�ɂ���ăX�L���̏������s��
		mBattleElements = mBattleService.transactBattleAction(mBattleElements);
		// 1�^�[���I�����̏���������
		mBattleElements.mPlayer.usingSkill = null;
		mBattleElements.mNpc.usingSkill = null;
	}

	public void EndTurn() {

	}

	public boolean isBattleEnd(boolean isEnd) {
		return isEnd;
	}
}
