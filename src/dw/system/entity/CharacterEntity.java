package dw.system.entity;

import dw.skill.AttackSkill;
import dw.skill.ChargeSkill;
import dw.skill.DefenseSkill;
import dw.skill.Skill;
import dw.system.battle.BattleElements;
import dw.system.entity.BattleStatus.ActionStatus;
import dw.system.entity.BattleStatus.BattleResult;

public class CharacterEntity {

	public String name;

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

	public CharacterEntity(String name) {
		this.name = name;
		mSkillList = new Skill[12];
		mItemList = new Item[4];

		mSkillList[0] = new AttackSkill(name);

		mSkillList[10] = new DefenseSkill(name);
		mSkillList[11] = new ChargeSkill(name);

		mHp = 100;
		mSp = 0;
	}

	public ActionStatus getActionStatus() {
		return usingSkill.actionStatus;
	}

}
