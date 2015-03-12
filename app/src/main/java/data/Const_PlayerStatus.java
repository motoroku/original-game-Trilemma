package data;

import Trilemma.PLAYER_STATUS;

public class Const_PlayerStatus {
    private static final PLAYER_STATUS PLAYER_NAME = new PLAYER_STATUS((long)1,"name","ヴィクター");
    private static final PLAYER_STATUS HP = new PLAYER_STATUS((long)1,"hp","100");
    private static final PLAYER_STATUS MAX_SP = new PLAYER_STATUS((long)1,"max_sp","10");
    private static final PLAYER_STATUS BASE_SP = new PLAYER_STATUS((long)1,"base_sp","3");
    private static final PLAYER_STATUS LEVEL = new PLAYER_STATUS((long)1,"level","1");
    private static final PLAYER_STATUS ATTACK = new PLAYER_STATUS((long)1,"attack","10");
    private static final PLAYER_STATUS DEFENSE = new PLAYER_STATUS((long)1,"defense","10");
    private static final PLAYER_STATUS GOLD = new PLAYER_STATUS((long)1,"gold","10");
    private static final PLAYER_STATUS EXP = new PLAYER_STATUS((long)1,"exp","10");
    private static final PLAYER_STATUS EQUIPMENT_WEAPON = new PLAYER_STATUS((long)1,"weaponId",String.valueOf(Const_Item.getHand().getId()));
    private static final PLAYER_STATUS EQUIPMENT_ARMOR = new PLAYER_STATUS((long)1,"armorId",String.valueOf(Const_Item.getSkin().getId()));

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


}
