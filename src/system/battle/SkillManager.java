package system.battle;

import utility.Cal;
import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.BattleResult;

public class SkillManager {
	public SkillCollection skillCollection = new SkillCollection();

	public BattleElements transactSkill(BattleElements elements) {
		// �X�L���g�p�҂̃X�L���̎�ނŕ���
		// �U���X�L���̏ꍇ
		if (elements.getActorSkill().actionStatus == ActionStatus.�U��) {
			// �X�L���g�p�҂̍s�����������Ă���ꍇ
			if (elements.result == BattleResult.win) {
				skillCollection.actAttackSkill(elements);
			}
			// �s�����ʂ����ł��������ꍇ
			else if (elements.result == BattleResult.clash) {
				// �X�L���g�p�҂��������Ă�ꍇ
				if (getClashResult(elements)) {
					skillCollection.actAttackSkillOnClash(elements);
				}
			}
			// �X�L���ɐݒ肳��Ă���|�C���g�������
			skillCollection.consumeSkillPoint(elements);
		}
		// �h��̃X�L���̏ꍇ
		else if (elements.getActorSkill().actionStatus == ActionStatus.�h��) {
			// Skill skill = elements.getActorSkill();
		}
		// �`���[�W�X�L���̏ꍇ
		else if (elements.getActorSkill().actionStatus == ActionStatus.�`���[�W) {
			// Skill skill = elements.getActorSkill();
			skillCollection.chargeSkillPoint(elements);
		}

		return elements;
	}

	private boolean getClashResult(BattleElements elements) {
		boolean result = false;

		// TODO: �A�N�^�[�ƃ^�[�Q�b�g�̃X�L���^�C�v�ɂ���Ĕ�����@���ς��\��
		result = Cal.calSkillMight(elements.actor, elements.getActorSkill()) > Cal.calSkillMight(elements.target,
				elements.getTargetSkill());

		return result;
	}
}