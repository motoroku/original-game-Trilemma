package data;

import java.util.ArrayList;
import java.util.List;

import Trilemma.PLAYER_STATUS;

public class Const_PlayerStatus {
    private static final PLAYER_STATUS PLAYER_NAME = new PLAYER_STATUS((long)1,"name","ヴィクター");
    private static final PLAYER_STATUS HP = new PLAYER_STATUS((long)2,"hp","100");
    private static final PLAYER_STATUS MAX_SP = new PLAYER_STATUS((long)3,"max_sp","10");
    private static final PLAYER_STATUS BASE_SP = new PLAYER_STATUS((long)4,"base_sp","3");
    private static final PLAYER_STATUS LEVEL = new PLAYER_STATUS((long)5,"level","1");
    private static final PLAYER_STATUS ATTACK = new PLAYER_STATUS((long)6,"attack","10");
    private static final PLAYER_STATUS DEFENSE = new PLAYER_STATUS((long)7,"defense","10");
    private static final PLAYER_STATUS GOLD = new PLAYER_STATUS((long)8,"gold","10");
    private static final PLAYER_STATUS EXP = new PLAYER_STATUS((long)9,"exp","10");
    private static final PLAYER_STATUS EQUIPMENT_WEAPON = new PLAYER_STATUS((long)10,"weaponId",String.valueOf(Const_Item.getHand().getId()));
    private static final PLAYER_STATUS EQUIPMENT_ARMOR = new PLAYER_STATUS((long)11,"armorId",String.valueOf(Const_Item.getSkin().getId()));
//    private static final PLAYER_STATUS SET_SKILL_1 = new PLAYER_STATUS((long)12,"skill1",String.valueOf(Const_Skill.getMagicAllow().getId()));
//    private static final PLAYER_STATUS SET_SKILL_2 = new PLAYER_STATUS((long)13,"skill2",String.valueOf(Const_Skill.getFireBall().getId()));
//    private static final PLAYER_STATUS SET_SKILL_3 = new PLAYER_STATUS((long)14,"skill3",String.valueOf(Const_Skill.getIceSpear().getId()));
//    private static final PLAYER_STATUS SET_SKILL_4 = new PLAYER_STATUS((long)15,"skill4",String.valueOf(Const_Skill.getThunderBolt().getId()));

    public static List<PLAYER_STATUS> getAllPlayerStatus(){
        List<PLAYER_STATUS> result = new ArrayList<>();

        result.add(getPlayerName());
        result.add(getHp());
        result.add(getMaxSp());
        result.add(getBaseSp());
        result.add(getLevel());
        result.add(getAttack());
        result.add(getDefense());
        result.add(getGold());
        result.add(getExp());
        result.add(getEquipmentWeapon());
        result.add(getEquipmentArmor());
//        result.add(getSetSkill1());
//        result.add(getSetSkill2());
//        result.add(getSetSkill3());
//        result.add(getSetSkill4());

        return result;
    }

    public static PLAYER_STATUS getPlayerName() {

        return PLAYER_NAME;
    }

    public static PLAYER_STATUS getHp() {
        return HP;
    }

    public static PLAYER_STATUS getMaxSp() {
        return MAX_SP;
    }

    public static PLAYER_STATUS getBaseSp() {
        return BASE_SP;
    }

    public static PLAYER_STATUS getLevel() {
        return LEVEL;
    }

    public static PLAYER_STATUS getAttack() {
        return ATTACK;
    }

    public static PLAYER_STATUS getDefense() {
        return DEFENSE;
    }

    public static PLAYER_STATUS getGold() {
        return GOLD;
    }

    public static PLAYER_STATUS getExp() {
        return EXP;
    }

    public static PLAYER_STATUS getEquipmentWeapon() {
        return EQUIPMENT_WEAPON;
    }

    public static PLAYER_STATUS getEquipmentArmor() {
        return EQUIPMENT_ARMOR;
    }

//    public static PLAYER_STATUS getSetSkill1() {
//        return SET_SKILL_1;
//    }
//
//    public static PLAYER_STATUS getSetSkill2() {
//        return SET_SKILL_2;
//    }
//
//    public static PLAYER_STATUS getSetSkill3() {
//        return SET_SKILL_3;
//    }
//
//    public static PLAYER_STATUS getSetSkill4() {
//        return SET_SKILL_4;
//    }

}
