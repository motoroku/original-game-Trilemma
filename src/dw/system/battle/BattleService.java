package dw.system.battle;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.CharacterEntity;
import dw.system.entity.Enemy;

public class BattleService {
	BattleLogic logic = new BattleLogic();
	SkillManager mSkillManager = new SkillManager();

	/**
	 * �퓬�������s��
	 * @param elements
	 * @return
	 */
	public BattleElements transactBattleTurn(BattleElements elements) {
		ActionStatus playerAction = elements.getPlayer().usingSkill.actionStatus;
		ActionStatus npcAction = elements.getEnemy().usingSkill.actionStatus;
		// Player�̃X�L���𔭓������āA���ʂ��擾����
		elements.result = logic.decideActionResult(playerAction, npcAction);
		elements = logic.setTurn(elements, BattleStatus.PLAYER);
		elements = mSkillManager.transactSkill(elements);
		elements.setCharacters();
		// NPC�̃X�L���𔭓������āA���ʂ��擾����
		elements.result = logic.decideActionResult(npcAction, playerAction);
		elements = logic.setTurn(elements, BattleStatus.NPC);
		elements = mSkillManager.transactSkill(elements);
		elements.setCharacters();
		return elements;
	}

	/**
	 * �^�[���I�����̏������s�� �B�g�p�X�L��������������
	 * @param elements
	 * @return
	 */
	public BattleElements turnEnd(BattleElements elements) {
		elements.characterMap.get(BattleStatus.PLAYER).usingSkill = null;
		elements.characterMap.get(BattleStatus.NPC).usingSkill = null;

		elements.turnCount++;
		return elements;
	}

	/**
	 * �g�p�X�L����ݒ肷��
	 * @param elements
	 * @param actor
	 * @return
	 */
	public BattleElements getAction(BattleElements elements, String actor) {
		if (actor == BattleStatus.PLAYER) {
			elements.characterMap.get(BattleStatus.PLAYER).usingSkill = elements.characterMap.get(BattleStatus.PLAYER).mSkillList[elements.inputButton];
		} else if (actor == BattleStatus.NPC) {
			Enemy enemy = (Enemy) elements.characterMap.get(BattleStatus.NPC);
			int num = enemy.getEnemyAction();
			elements.getEnemy().usingSkill = enemy.mSkillList[num];
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
