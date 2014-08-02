package dw.system.battle;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.CharacterEntity;
import dw.system.entity.Enemy;

public class BattleService {
	BattleLogic logic = new BattleLogic();
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
		elements.result = logic.decideActionResult(playerAction, npcAction);
		elements = logic.setSkillActor(elements, BattleStatus.PLAYER);
		elements = mSkillManager.transactSkill(elements);
		elements.setCharacters();
		// NPC�̃X�L���𔭓������āA���ʂ��擾����
		elements.result = logic.decideActionResult(npcAction, playerAction);
		elements = logic.setSkillActor(elements, BattleStatus.NPC);
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

		elements.result = logic.decideActionResult(actorAction, receiverAction);
		// �X�L���̑Ώۂ�ݒ肷��
		elements = logic.setSkillActor(elements, actor);
		// �X�L���𔭓����鏈�����s��
		elements = mSkillManager.transactSkill(elements);
		// �X�L���̌��ʂ�K�p�������ʂ��G�������g�̃L�����N�^�[�ɔ��f������
		elements.setCharacters();

		return true;
	}

	/**
	 * �^�[���I�����̏������s�� �B�g�p�X�L��������������
	 * @param elements
	 * @return
	 */
	public boolean turnEnd(BattleElements elements) {
		elements.characterMap.get(BattleStatus.PLAYER).usingSkill = null;
		elements.characterMap.get(BattleStatus.NPC).usingSkill = null;

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
		} else if (actor == BattleStatus.NPC) {
			int num = ((Enemy) elements.getEnemy()).getEnemyAction();
			elements.getEnemy().usingSkill = elements.getEnemy().skillList[num];
			if (!logic.isHaveNecessaryPoint(num, elements.getEnemy())) {
				elements = getAction(elements, BattleStatus.NPC);
			}
		}
		return elements;
	}

	/**
	 * �X�L�����g�p����̂ɕK�v�ȃX�L���|�C���g�������Ă��邩�ǂ����𔻒肷��
	 * @param buttonNum
	 * @param character
	 * @return
	 */
	public boolean isHaveNecessaryPoint(int buttonNum, CharacterEntity character) {
		return logic.isHaveNecessaryPoint(buttonNum, character);
	}
}
