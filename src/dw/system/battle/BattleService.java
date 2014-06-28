package dw.system.battle;

import java.lang.annotation.ElementType;

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
	public BattleElements resetAction(BattleElements elements) {
		elements.characterMap.get(BattleStatus.PLAYER).usingSkill = null;
		elements.characterMap.get(BattleStatus.NPC).usingSkill = null;
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
			elements.characterMap.get(BattleStatus.NPC).usingSkill = elements.characterMap.get(BattleStatus.NPC).mSkillList[11];
		}
		return elements;
	}
}
