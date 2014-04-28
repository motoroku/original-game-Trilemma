package dw.system.battle;

import java.util.HashMap;
import java.util.Map;

import dw.system.entity.BattleStatus;
import dw.system.entity.BattleStatus.BattleResult;
import dw.system.entity.CharacterEntity;

public class BattleElements {

	CharacterEntity mPlayer;
	CharacterEntity mNpc;

	public Map<String, CharacterEntity> mCharacterMap = new HashMap<String, CharacterEntity>();

	public BattleResult mResult;

	public BattleElements() {
		mPlayer = new CharacterEntity();
		mNpc = new CharacterEntity();

		mCharacterMap.put(BattleStatus.PLAYER, mPlayer);
		mCharacterMap.put(BattleStatus.NPC, mNpc);
	}

}
