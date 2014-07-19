package dw.skill;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.AttackSkillType;
import dw.system.entity.CharacterEntity;

public class AttackSkill extends Skill {

	public int damage;
	public static AttackSkillType type = AttackSkillType.Normal;

	/**
	 * �R���X�g���N�^�B
	 */
	public AttackSkill() {
		actionStatus = ActionStatus.�U��;
	}

	/**
	 * �R���X�g���N�^�B�^�[�Q�b�g��ݒ�ł���B
	 * @param actor �X�L�����g���L�����N�^�[�B�^�[�Q�b�g�̓X�L���g�p�҂ł͂Ȃ����ɐݒ肳���B
	 */
	public AttackSkill(String actor) {
		actionStatus = ActionStatus.�U��;
		damage = 10;
		necessaryPoint = 1;
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
	}

	/**
	 * �R���X�g���N�^�B�^�[�Q�b�g�A�_���[�W��ݒ�ł���B
	 * @param actor �X�L�����g���L�����N�^�[�B�^�[�Q�b�g�̓X�L���g�p�҂ł͂Ȃ����ɐݒ肳���B
	 * @param damage ���̃X�L���_���[�W�|�C���g�ɐݒ肳���B
	 */
	public AttackSkill(String actor, int damage) {
		actionStatus = ActionStatus.�U��;
		this.damage = damage;
		necessaryPoint = 1;
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
	}

	/**
	 * �R���X�g���N�^�B�^�[�Q�b�g�A�_���[�W�A�K�v�X�L���|�C���g��ݒ�ł���B
	 * @param actor �X�L�����g���L�����N�^�[�B�^�[�Q�b�g�̓X�L���g�p�҂ł͂Ȃ����ɐݒ肳���B
	 * @param damage ���̃X�L���_���[�W�|�C���g�ɐݒ肳���B
	 * @param point �K�v�ȃX�L���|�C���g�ɐݒ肳���B
	 */
	public AttackSkill(String actor, int damage, int point) {
		actionStatus = ActionStatus.�U��;
		this.damage = damage;
		necessaryPoint = point;
		if (actor == BattleStatus.NPC) {
			target = BattleStatus.PLAYER;
		} else if (actor == BattleStatus.PLAYER) {
			target = BattleStatus.NPC;
		}
	}

	/**
	 * �X�L���̏������s���B�ݒ肳��Ă���_���[�W�ʂ������̃^�[�Q�b�g��HP����}�C�i�X����B
	 * @param target �X�L���̌��ʂ�K�p����L�����N�^�[
	 * @return �X�L���̌��ʂ��K�p���ꂽ�L�����N�^�[���Ԃ����
	 */
	public CharacterEntity actAtackSkill(CharacterEntity target) {
		target.mHp = target.mHp - damage;
		return target;
	}

	/**
	 * �s������̌��ʂ�Clash�������ꍇ�ɗ��p���邱�Ƃ�z�肵���X�L���̏������s���B
	 * ���̃X�L���̃_���[�W����^�[�Q�b�g�̎g���X�L���̃_���[�W�����������̃_���[�W���^�[�Q�b�g�ɗ^����
	 * @param target �X�L���̌��ʂ�K�p����
	 * @return�@�X�L���̌��ʂ��K�p���ꂽ�L�����N�^�[���Ԃ����
	 */
	public CharacterEntity actAttackSkillOnClash(CharacterEntity target) {
		AttackSkill skill = (AttackSkill) target.usingSkill;
		target.mHp = target.mHp - (damage - skill.damage);
		return target;
	}
}
