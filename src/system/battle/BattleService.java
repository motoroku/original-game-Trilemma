package system.battle;

import entity.BattleStatus;
import entity.CharacterEntity;
import entity.Enemy;
import entity.BattleStatus.ActionStatus;
import entity.BattleStatus.BattleResult;
import entity.skill.Skill;

public class BattleService {
	SkillManager mSkillManager = new SkillManager();

	/**
	 * �v���C���[��NPC�̐퓬�������܂Ƃ߂čs��
	 * @param elements
	 * @return
	 */
	public boolean ActBattleActions(BattleElements elements) {
		ActionStatus playerAction = elements.getPlayer().usingSkill.actionStatus;
		ActionStatus npcAction = elements.getEnemy().usingSkill.actionStatus;
		// Player�̃X�L���𔭓������āA���ʂ��擾����
		elements.result = decideActionResult(playerAction, npcAction);
		elements = setSkillActor(elements, BattleStatus.PLAYER);
		elements = mSkillManager.transactSkill(elements);
		elements.setCharacters();
		// NPC�̃X�L���𔭓������āA���ʂ��擾����
		elements.result = decideActionResult(npcAction, playerAction);
		elements = setSkillActor(elements, BattleStatus.ENEMY);
		elements = mSkillManager.transactSkill(elements);
		elements.setCharacters();
		return true;
	}

	/**
	 * �퓬�������s���B
	 * @param elements
	 * @param actor �s�����s���L�����N�^�[
	 * @return
	 */
	public boolean ActBattleAction(BattleElements elements, String actor) {
		ActionStatus actorAction;
		ActionStatus receiverAction;

		if (actor == BattleStatus.PLAYER) {
			actorAction = elements.getPlayer().usingSkill.actionStatus;
			receiverAction = elements.getEnemy().usingSkill.actionStatus;
		} else {
			actorAction = elements.getEnemy().usingSkill.actionStatus;
			receiverAction = elements.getPlayer().usingSkill.actionStatus;
		}

		elements.result = decideActionResult(actorAction, receiverAction);
		// �X�L���g�p�҂Ƃ��̑Ώۂ�ݒ肷��
		elements = setSkillActor(elements, actor);
		// �X�L���𔭓����鏈�����s��
		elements = mSkillManager.transactSkill(elements);
		// �X�L���̌��ʂ�K�p�������ʂ��G�������g�̃L�����N�^�[�ɔ��f������
		elements.setCharacters();
		// �X�L���̎g�p������ۑ�
		elements.setTurnHistory();

		return true;
	}

	/**
	 * �^�[���I�����̏������s�� �B�g�p�X�L��������������
	 * @param elements
	 * @return
	 */
	public boolean turnEnd(BattleElements elements) {
		elements.characterMap.get(BattleStatus.PLAYER).usingSkill = null;
		elements.characterMap.get(BattleStatus.ENEMY).usingSkill = null;

		elements.turnCount++;
		return true;
	}

	/**
	 * �g�p�X�L����ݒ肷��
	 * @param elements
	 * @param actor
	 * @return
	 */
	public BattleElements getAction(BattleElements elements, String actor) {
		if (actor == BattleStatus.PLAYER) {
			elements.getPlayer().usingSkill = elements.getPlayer().skillList[elements.inputButton];
		} else if (actor == BattleStatus.ENEMY) {
			int num = ((Enemy) elements.getEnemy()).getEnemyAction();
			elements.getEnemy().usingSkill = elements.getEnemy().skillList[num];
			if (!isHaveNecessaryPoint(num, elements.getEnemy())) {
				elements = getAction(elements, BattleStatus.ENEMY);
			}
		}
		return elements;
	}

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
		} else if (actor == BattleStatus.ENEMY) {
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
		} else if (usingSkill.target == BattleStatus.ENEMY) {
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
		Skill selectedSkill = character.skillList[buttonNum];
		if (character.sp >= selectedSkill.necessarySkillPoint) {
			return true;
		}
		return false;
	}
}
