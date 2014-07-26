package dw.system.battle;

import dw.skill.AttackSkill;
import dw.skill.skillCollection;
import dw.skill.ChargeSkill;
import dw.skill.DefenseSkill;
import dw.system.entity.CharacterEntity;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.BattleResult;

public class SkillManager {
	public skillCollection skillCollection = new skillCollection();

	public BattleElements transactSkill(BattleElements elements) {
		// �X�L���g�p�҂̃X�L���̎�ނŕ���
		// �U���X�L���̏ꍇ
		if (elements.getActorSkill().actionStatus == ActionStatus.�U��) {
			AttackSkill actorSkill = (AttackSkill) elements.getActorSkill();
			// �X�L���g�p�҂̍s�����������Ă���ꍇ
			if (elements.result == BattleResult.win) {
				skillCollection.actAttackSkill(elements);
			}
			// �s�����ʂ����ł��������ꍇ
			else if (elements.result == BattleResult.clash) {
				AttackSkill targetSkill = (AttackSkill) elements.getTargetSkill();
				// �X�L���g�p�҂��������Ă�ꍇ
				if (actorSkill.damage > targetSkill.damage) {
					skillCollection.actAttackSkillOnClash(elements);
				}
			}
			// �X�L���ɐݒ肳��Ă���|�C���g�������
			skillCollection.consumeSkillPoint(elements);
		}
		// �h��̃X�L���̏ꍇ
		else if (elements.getActorSkill().actionStatus == ActionStatus.�h��) {
			DefenseSkill skill = (DefenseSkill) elements.getActorSkill();
		}
		// �`���[�W�X�L���̏ꍇ
		else if (elements.getActorSkill().actionStatus == ActionStatus.�`���[�W) {
			ChargeSkill skill = (ChargeSkill) elements.getActorSkill();
			skillCollection.chargeSkillPoint(elements);
		}

		return elements;
	}
}
