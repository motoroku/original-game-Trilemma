package dw.system.battle;

import dw.skill.Skill;
import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.BattleResult;
import dw.system.entity.CharacterEntity;

public class BattleLogic {

	/**
	 * �g�p�X�L���̏��s������s��
	 * @param playerAction
	 * @param npcAction
	 * @return
	 */
	public BattleResult decideActionResult(ActionStatus playerAction, ActionStatus npcAction) {
		BattleResult result;

		if (playerAction == ActionStatus.�U�� && npcAction == ActionStatus.�h��) {
			result = BattleResult.lose;
		} else if (playerAction == ActionStatus.�U�� && npcAction == ActionStatus.�`���[�W) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.�U�� && npcAction == ActionStatus.�U��) {
			result = BattleResult.clash;
		} else if (playerAction == ActionStatus.�`���[�W && npcAction == ActionStatus.�U��) {
			result = BattleResult.lose;
		} else if (playerAction == ActionStatus.�`���[�W && npcAction == ActionStatus.�h��) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.�h�� && npcAction == ActionStatus.�U��) {
			result = BattleResult.win;
		} else if (playerAction == ActionStatus.�h�� && npcAction == ActionStatus.�`���[�W) {
			result = BattleResult.lose;
		} else {
			result = BattleResult.draw;
		}

		return result;
	}

	/**
	 * �s������L�����N�^�[�ƃX�L���̑Ώۂ�ݒ肷��
	 * @param elements
	 * @param actor
	 * @return
	 */
	public BattleElements setSkillActor(BattleElements elements, String actor) {
		if (actor == BattleStatus.PLAYER) {
			elements.setPlayerTurn();
		} else if (actor == BattleStatus.NPC) {
			elements.setEnemyTurn();
		}
		elements.target = setTarget(elements);
		return elements;
	}

	/**
	 * �g�p�X�L���̑Ώۂ�ݒ肷��
	 * @param elements
	 * @return
	 */
	private CharacterEntity setTarget(BattleElements elements) {
		Skill usingSkill = elements.actor.usingSkill;
		if (usingSkill.target == BattleStatus.PLAYER) {
			elements.setTargetPlayer();
		} else if (usingSkill.target == BattleStatus.NPC) {
			elements.setTargetEnemy();
		}
		return elements.target;
	}

	/**
	 * �g�p�X�L���̕K�v�X�L���|�C���g�������Ă��邩�ǂ����𔻒肷��
	 * @param buttonNum
	 * @param character
	 * @return
	 */
	public boolean isHaveNecessaryPoint(int buttonNum, CharacterEntity character) {
		Skill selectedSkill = character.mSkillList[buttonNum];
		if (character.mSp >= selectedSkill.necessaryPoint) {
			return true;
		}
		return false;
	}
}
