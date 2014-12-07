package system.battle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.BattleStatus;
import entity.CharacterEntity;
import entity.Enemy;
import entity.Player;
import entity.BattleStatus.BattleResult;
import entity.TurnAction;
import entity.skill.Skill;

public class BattleElements {
	public BattleResult result;
	public int inputButton;
	public Map<String, CharacterEntity> characterMap = new HashMap<String, CharacterEntity>();
	public CharacterEntity actor;
	public CharacterEntity target;
	public int turnCount;
	public List<TurnAction> playerTrunHistoryList = new ArrayList<TurnAction>();
	public List<TurnAction> enemyTurnHistoryList = new ArrayList<TurnAction>();

	public BattleElements() {
		// CharacterEntity player = new Player(BattleStatus.PLAYER);
		// CharacterEntity npc = new Enemy(BattleStatus.ENEMY);

		// characterMap.put(BattleStatus.PLAYER, player);
		// characterMap.put(BattleStatus.ENEMY, npc);
	}

	public void setPlayerTurn() {
		actor = characterMap.get(BattleStatus.PLAYER);
	}

	public void setEnemyTurn() {
		actor = characterMap.get(BattleStatus.ENEMY);
	}

	public void setTargetPlayer() {
		target = characterMap.get(BattleStatus.PLAYER);
	}

	public void setTargetEnemy() {
		target = characterMap.get(BattleStatus.ENEMY);
	}

	public void applyBattleResult() {
		if (actor.name == BattleStatus.PLAYER) {
			characterMap.put(BattleStatus.PLAYER, actor);
		} else if (actor.name == BattleStatus.ENEMY) {
			characterMap.put(BattleStatus.ENEMY, actor);
		}

		if (target.name == BattleStatus.PLAYER) {
			characterMap.put(BattleStatus.PLAYER, target);
		} else if (target.name == BattleStatus.ENEMY) {
			characterMap.put(BattleStatus.ENEMY, target);
		}
	}

	public void setTurnHistory() {
		TurnAction turnAction = new TurnAction();
		turnAction.setTurnAction(actor.usingSkill);
		if (actor.characterType == BattleStatus.PLAYER) {
			playerTrunHistoryList.add(turnAction);
		} else if (actor.characterType == BattleStatus.ENEMY) {
			enemyTurnHistoryList.add(turnAction);
		}
	}

	public CharacterEntity getPlayer() {
		return characterMap.get(BattleStatus.PLAYER);
	}

	public CharacterEntity getEnemy() {
		return characterMap.get(BattleStatus.ENEMY);
	}

	public CharacterEntity getCharacter(String name) {
		if (name != BattleStatus.PLAYER && name != BattleStatus.ENEMY) {
			return null;
		}
		return characterMap.get(name);
	}

	public Skill getActorSkill() {
		return actor.usingSkill;
	}

	public Skill getTargetSkill() {
		return target.usingSkill;
	}
}
