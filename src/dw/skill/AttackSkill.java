package dw.skill;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.AttackSkillType;

public class AttackSkill extends Skill {

	public int damage;
	public AttackSkillType type;

	/**
	 * �R���X�g���N�^�B
	 */
	public AttackSkill() {
		actionStatus = ActionStatus.�U��;
	}

	/**
	 * �R���X�g���N�^
	 * @param actor �X�L���̎g�p��
	 */
	public AttackSkill(String actor) {
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
		damage = 10;
		type = AttackSkillType.Normal;
		necessaryPoint = 1;
	}

	/**
	 * �R���X�g���N�^�B�^�[�Q�b�g�A�_���[�W�A�K�v�X�L���|�C���g��ݒ�ł���B
	 * @param actor �X�L�����g���L�����N�^�[�B�^�[�Q�b�g�̓X�L���g�p�҂ł͂Ȃ����ɐݒ肳���B
	 * @param damage ���̃X�L���_���[�W�|�C���g�ɐݒ肳���B
	 * @param point �K�v�ȃX�L���|�C���g�ɐݒ肳���B
	 */
	public AttackSkill(String actor, int damage, int point, AttackSkillType type) {
		actionStatus = ActionStatus.�U��;
		this.damage = damage;
		necessaryPoint = point;
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
		this.type = type;
	}

}
