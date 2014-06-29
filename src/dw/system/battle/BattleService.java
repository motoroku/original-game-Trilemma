package dw.system.battle;

import java.lang.annotation.ElementType;
import java.util.Random;

import dw.skill.Skill;
import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.BattleResult;
import dw.system.entity.CharacterEntity;
import dw.system.entity.Enemy;

public class BattleService {
	BattleLogic logic = new BattleLogic();
	SkillManager mSkillManager = new SkillManager();

	/**
	 * 
	 * @param elements
	 * @return
	 */
	public BattleElements transactBattleTurn(BattleElements elements) {
		ActionStatus playerAction = elements.getPlayer().usingSkill.actionStatus;
		ActionStatus npcAction = elements.getEnemy().usingSkill.actionStatus;
		// Playerのスキルを発動させて、結果を取得する
		elements.result = logic.decideActionResult(playerAction, npcAction);
		elements = logic.setTurn(elements, BattleStatus.PLAYER);
		elements = mSkillManager.transactSkill(elements);
		elements.setCharacters();
		// NPCのスキルを発動させて、結果を取得する
		elements.result = logic.decideActionResult(npcAction, playerAction);
		elements = logic.setTurn(elements, BattleStatus.NPC);
		elements = mSkillManager.transactSkill(elements);
		elements.setCharacters();
		return elements;
	}

	/**
	 * 
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
	 * 
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

	public boolean isHaveNecessaryPoint(int buttonNum, CharacterEntity character) {
		return logic.isHaveNecessaryPoint(buttonNum, character);
	}
}
