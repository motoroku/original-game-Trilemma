package system.battle;

import entity.BattleStatus;
import entity.BattleStatus.SelectedActionList;
import entity.CharacterEntity;
import entity.Enemy;
import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.BattleResult;
import entity.BattleStatus.TargetStatus;
import entity.skill.Skill;

public class BattleService {
	SkillManager mSkillManager = new SkillManager();

	/**
	 * �g�p�X�L����ݒ肷��
	 * @param elements
	 * @param actorType
	 * @return
	 */
	public void getAction(BattleElements elements, String actorType) {
		if (actorType == BattleStatus.PLAYER) {
			elements.getPlayer().usingSkill = elements.getPlayer().skillList[elements.inputButton];
		} else if (actorType == BattleStatus.ENEMY) {
			// TODO:
			int num = ((Enemy) elements.getEnemy()).getEnemyAction(elements);
			elements.getEnemy().usingSkill = elements.getEnemy().skillList[num];
			if (!isEnoughSkillPoint(elements.getEnemy().usingSkill, elements.getEnemy())) {
				getAction(elements, BattleStatus.ENEMY);
			}
		}
	}

	/**
	 * �I�������X�L�����ݒ肳��Ă��邩�ǂ����𔻒肷��
	 * @param selectedAction
	 * @param elements
	 * @return �ݒ肳��Ă����true.�ݒ肳��Ă��Ȃ���false.
	 */
	public boolean isSetSkill(SelectedActionList selectedAction, BattleElements elements) {
		int num = elements.getInputButton(selectedAction);

		Skill skill = elements.getPlayer().skillList[num];

		if (skill != null) {
			return true;
		}

		return false;
	}

	/**
	 * �g�p�X�L���̕K�v�X�L���|�C���g�������Ă��邩�ǂ����𔻒肷��
	 * @param buttonNum
	 * @param character
	 * @return
	 */
	public boolean isEnoughSkillPoint(Skill skill, CharacterEntity character) {
		return character.currentSp >= skill.skillPoint;
	}

	/**
	 * �퓬�������s���B
	 * @param elements
	 * @param actorType �s�����s���L�����N�^�[
	 * @return
	 */
	public boolean processBattleAction(BattleElements elements, String actorType) {
		ActionStatus actorAction;
		ActionStatus receiverAction;

		if (actorType == BattleStatus.PLAYER) {
			actorAction = elements.getPlayer().usingSkill.actionStatus;
			receiverAction = elements.getEnemy().usingSkill.actionStatus;
		} else {
			actorAction = elements.getEnemy().usingSkill.actionStatus;
			receiverAction = elements.getPlayer().usingSkill.actionStatus;
		}

		// �g�p�X�L���̏��s������s��
		elements.result = getActionResult(actorAction, receiverAction);
		// �X�L���g�p�҂Ƃ��̑Ώۂ�ݒ肷��
		setSkillActor(elements, actorType);
		// �X�L���𔭓����鏈�����s��
		elements = mSkillManager.transactSkill(elements);
		// �X�L���̌��ʂ�K�p�������ʂ��G�������g�̃L�����N�^�[�ɔ��f������
		elements.applyBattleResult();
		// �X�L���̎g�p������ۑ�
		elements.setTurnHistory();

		return true;
	}

	/**
	 * �^�[���I�����̏������s�� �B�퓬�v�f�̏������B
	 * @param elements
	 * @return
	 */
	public boolean endProcessing(BattleElements elements) {
		elements.characterMap.get(BattleStatus.PLAYER).usingSkill = null;
		elements.characterMap.get(BattleStatus.ENEMY).usingSkill = null;

		elements.actor = null;
		elements.target = null;

		((Enemy) elements.getEnemy()).resetRate();

		elements.turnCount++;
		return true;
	}

	// ------------------------------------------------------------------------------------------------------
	// Private Method

	/**
	 * �g�p�X�L���̏��s������s��
	 * @param playerAction
	 * @param npcAction
	 * @return
	 */
	private BattleResult getActionResult(ActionStatus playerAction, ActionStatus npcAction) {
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
	 * @param actorType
	 * @return
	 */
	private void setSkillActor(BattleElements elements, String actorType) {
		if (actorType == BattleStatus.PLAYER) {
			elements.setPlayerTurn();
		} else if (actorType == BattleStatus.ENEMY) {
			elements.setEnemyTurn();
		}
		setTarget(elements);
	}

	/**
	 * �g�p�X�L���̑Ώۂ�ݒ肷��
	 * @param elements
	 * @return
	 */
	private void setTarget(BattleElements elements) {
		Skill usingSkill = elements.actor.usingSkill;
		String actorType = elements.actor.characterType;

		if (actorType == BattleStatus.PLAYER) {
			if (usingSkill.target == TargetStatus.self) {
				elements.setTargetPlayer();
			} else if (usingSkill.target == TargetStatus.enemy) {
				elements.setTargetEnemy();
			}
		} else if (actorType == BattleStatus.ENEMY) {
			if (usingSkill.target == TargetStatus.self) {
				elements.setTargetEnemy();
			} else if (usingSkill.target == TargetStatus.enemy) {
				elements.setTargetPlayer();
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------
}
