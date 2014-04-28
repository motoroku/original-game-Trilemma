package dw.system.entity;

import dw.system.battle.BattleElements;
import dw.system.entity.BattleStatus.BattleResult;

public class CharacterEntity {

	public Skill[] mSkillList;
	public Item[] mItemList;

	public Skill usingSkill;

	public int mHp;
	public int mSp;

	public CharacterEntity() {
		mSkillList = new Skill[12];
		mItemList = new Item[4];

		mSkillList[0] = new AttackSkill();

		mSkillList[10] = new DefenseSkill();
		mSkillList[11] = new ChargeSkill();

		mHp = 100;
		mSp = 0;
	}

}
