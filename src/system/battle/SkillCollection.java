package system.battle;

import entity.CharacterEntity;
import entity.skill.Skill;

/**
 * Skill�̏������\�b�h�����N���X
 * @author mori_yu
 * 
 */
public class SkillCollection {
	public SkillCollection() {
	}

	// ----------------------------------------------------------------------------------------
	// �g�p�X�L���̕��򏈗�
	public boolean actAttackSkill(BattleElements elements) {
		Skill skill = elements.getActorSkill();
		switch (skill.type.getSkill_type_name()) {
			case "�ʏ�U��":
				normalAttack(elements, skill);
				break;
			case "���U��":
				break;
			default:
				break;
		}
		return true;
	}

	public boolean actAttackSkillOnClash(BattleElements elements) {
		Skill skill = elements.getActorSkill();
		switch (skill.type.getSkill_type_name()) {
			case "�ʏ�U��":
				normalAttackOnClash(elements, skill);
				break;
			case "���U��":
				break;
			default:
				break;
		}
		return true;
	}

	// ----------------------------------------------------------------------------------------
	/**
	 * ��{�U���X�L��
	 * @param elements
	 * @param skill
	 * @return
	 */
	public boolean normalAttack(BattleElements elements, Skill skill) {
		CharacterEntity target = elements.target;
		CharacterEntity actor = elements.actor;

		target.maxHp = target.maxHp - skill.effetPoint;

		elements.target = target;
		elements.actor = actor;
		return true;
	}

	/**
	 * ��{�U���X�L���BClash���ɗ��p����B
	 * @param elements
	 * @param skill
	 * @return
	 */
	public boolean normalAttackOnClash(BattleElements elements, Skill skill) {
		CharacterEntity target = elements.target;
		CharacterEntity actor = elements.actor;

		Skill targetSkill = target.usingSkill;
		target.maxHp = target.maxHp - (skill.effetPoint - targetSkill.effetPoint);

		elements.target = target;
		elements.actor = actor;
		return true;
	}

	// ----------------------------------------------------------------------------------------
	/**
	 * ��{�`���[�W�X�L��
	 * @param elements
	 */
	public void chargeSkillPoint(BattleElements elements) {
		CharacterEntity actor = elements.actor;

		actor.maxSp++;

		elements.actor = actor;
	}

	// ----------------------------------------------------------------------------------------
	/**
	 * �X�L���|�C���g�̏����
	 * @param elements
	 */
	public void consumeSkillPoint(BattleElements elements) {
		CharacterEntity actor = elements.actor;

		Skill skill = elements.getActorSkill();
		actor.maxSp = actor.maxSp - skill.skillPoint;

		elements.actor = actor;
	}

	// ----------------------------------------------------------------------------------------
}
