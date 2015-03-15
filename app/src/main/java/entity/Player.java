package entity;

import java.util.List;

import Trilemma.PLAYER_SKILL;
import dao.PlayerDto;
import Trilemma.ARMOR;
import Trilemma.LEARNED_SKILL;
import Trilemma.PLAYER_STATUS;
import Trilemma.SKILL;
import Trilemma.WEAPON;

public class Player extends CharacterEntity {

    private static final int SKILL_SIZE_CUSTAMIZE = 4;
    public Weapon weapon;
    public Armor armor;

    public Player(PlayerDto playerDto, List<SKILL> skillList, SKILL defenseSkill, SKILL chargeSkill) {
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
        gold = playerDto.gold;
        exp = playerDto.exp;

        this.characterType = BattleStatus.PLAYER;

        this.skillList[0] = new Skill(defenseSkill);
        this.skillList[1] = new Skill(chargeSkill);

        for (int i = 0; i < skillList.size(); i++) {
            this.skillList[i + 2] = new Skill(skillList.get(i));
        }

    }

}
