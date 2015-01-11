package entity;

import java.util.List;

import dao.PlayerDto;

import Trilemma.LEARNED_SKILL;
import Trilemma.PLAYER_STATUS;
import Trilemma.SKILL;

public class Player extends CharacterEntity {

	private static final int SKILL_SIZE_CUSTAMIZE = 5;
	public Weapon weapon;
	public Armor armor;

	public Player(String name, List<LEARNED_SKILL> learnedSkillList, List<SKILL> skillList, SKILL defense, SKILL charge) {
		super(name);

		level = 1;
		hp = 100;
		maxSp = 10;
		currentHp = hp;
		currentSp = 3;

		this.characterType = BattleStatus.PLAYER;

		this.skillList[0] = new Skill(defense);
		this.skillList[1] = new Skill(charge);

		// TODO:イマイチ保証になってない
		if (skillList.size() == learnedSkillList.size()) {
			for (int i = 0; i < skillList.size(); i++) {
				if (!learnedSkillList.get(i).getIs_set_flg()) {
					skillList.remove(i);
					learnedSkillList.remove(i);
				}
			}
		}

		for (int i = 0; i < this.skillList.length - 2; i++) {
			this.skillList[i + 2] = new Skill(skillList.get(i));
		}

	}

	public Player(PlayerDto playerDto, List<LEARNED_SKILL> learnedSkillList, List<SKILL> skillList, SKILL defenseSkill,
			SKILL chargeSkill) {
		super(playerDto.name);

		level = playerDto.level;
		hp = playerDto.hp;
		maxSp = playerDto.maxSp;
		currentHp = hp;
		currentSp = playerDto.baseSp;
		weapon = playerDto.weapon;
		armor = playerDto.armor;
		attack = playerDto.attack + weapon.attackPower;
		defense = playerDto.defense + armor.defensePower;

		this.characterType = BattleStatus.PLAYER;

		this.skillList[0] = new Skill(defenseSkill);
		this.skillList[1] = new Skill(chargeSkill);

		// TODO:イマイチ保証になってない
		if (skillList.size() == learnedSkillList.size()) {
			for (int i = 0; i < skillList.size(); i++) {
				if (!learnedSkillList.get(i).getIs_set_flg()) {
					skillList.remove(i);
					learnedSkillList.remove(i);
				}
			}
		}

		for (int i = 0; i < this.skillList.length - 2; i++) {
			this.skillList[i + 2] = new Skill(skillList.get(i));
		}

	}
}
