package system.battle;

import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.BattleResult;
import entity.skill.Skill;

public class SkillManager {
	public SkillCollection skillCollection = new SkillCollection();

	public BattleElements transactSkill(BattleElements elements) {
		// �X�L���g�p�҂̃X�L���̎�ނŕ���
		// �U���X�L���̏ꍇ
		if (elements.getActorSkill().actionStatus == ActionStatus.�U��) {
			Skill actorSkill = elements.getActorSkill();
			// �X�L���g�p�҂̍s�����������Ă���ꍇ
			if (elements.result == BattleResult.win) {
				skillCollection.actAttackSkill(elements);
			}
			// �s�����ʂ����ł��������ꍇ
			else if (elements.result == BattleResult.clash) {
				Skill targetSkill = elements.getTargetSkill();
				// �X�L���g�p�҂��������Ă�ꍇ
				if (actorSkill.point > targetSkill.point) {
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
}
