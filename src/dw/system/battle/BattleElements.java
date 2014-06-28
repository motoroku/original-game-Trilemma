package dw.system.battle;

import java.util.HashMap;
import java.util.Map;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.BattleResult;
import dw.system.entity.CharacterEntity;

public class BattleElements {
	public BattleResult result;
	public int inputButton;
	public Map<String, CharacterEntity> characterMap = new HashMap<String, CharacterEntity>();
	public CharacterEntity actor;
	public CharacterEntity target;

	public BattleElements() {
		CharacterEntity player = new CharacterEntity(BattleStatus.PLAYER);
		CharacterEntity npc = new CharacterEntity(BattleStatus.NPC);

		characterMap.put(BattleStatus.PLAYER, player);
		characterMap.put(BattleStatus.NPC, npc);
	}

	public void setPlayerTurn() {
		actor = characterMap.get(BattleStatus.PLAYER);
	}

	public void setEnemyTurn() {
		actor = characterMap.get(BattleStatus.NPC);
	}

	public void setTargetPlayer() {
		target = characterMap.get(BattleStatus.PLAYER);
	}

	public void setTargetEnemy() {
		target = characterMap.get(BattleStatus.NPC);
	}

	public void setCharacters() {
		if (actor.name == BattleStatus.PLAYER) {
			characterMap.put(BattleStatus.PLAYER, actor);
		} else if (actor.name == BattleStatus.NPC) {
			characterMap.put(BattleStatus.NPC, actor);
		}

		if (target.name == BattleStatus.PLAYER) {
			characterMap.put(BattleStatus.PLAYER, target);
		} else if (target.name == BattleStatus.NPC) {
			characterMap.put(BattleStatus.NPC, target);
		}
	}

	public CharacterEntity getPlayer() {
		return characterMap.get(BattleStatus.PLAYER);
	}

	public CharacterEntity getEnemy() {
		return characterMap.get(BattleStatus.NPC);
	}

	public CharacterEntity getCharacter(String name) {
		if (name != BattleStatus.PLAYER && name != BattleStatus.NPC) {
			return null;
		}
		return characterMap.get(name);
	}
}
