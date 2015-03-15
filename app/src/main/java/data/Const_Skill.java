package data;

import java.util.ArrayList;
import java.util.List;

import Trilemma.LEARNED_SKILL;
import Trilemma.M_ACTION_STATUS;
import Trilemma.M_SKILL_TYPE;
import Trilemma.SKILL;
import entity.BattleStatus;

/**
 * Created by mori_yu on 2015/03/12.
 */
public class Const_Skill {

    public static final int WIN = 1;
    public static final int DRAW = 0;
    public static final int LOSE = -1;

    private static final M_ACTION_STATUS A_ATTACK = new M_ACTION_STATUS((long) BattleStatus.ActionStatus.攻撃.getId(),
            BattleStatus.ActionStatus.攻撃.getValue(), DRAW, LOSE, WIN);
    private static final M_ACTION_STATUS A_DEFENSE = new M_ACTION_STATUS((long) BattleStatus.ActionStatus.防御.getId(),
            BattleStatus.ActionStatus.防御.getValue(), WIN, DRAW, LOSE);
    private static final M_ACTION_STATUS A_CHARGE = new M_ACTION_STATUS((long) BattleStatus.ActionStatus.チャージ.getId(),
            BattleStatus.ActionStatus.チャージ.getValue(), LOSE, WIN, DRAW);

    private static final M_SKILL_TYPE T_NORMAL_ATTACK = new M_SKILL_TYPE((long) 1,
            BattleStatus.SkillType.NormalAttack.getName(),
            BattleStatus.SkillType.NormalAttack.getId(),
            (long) BattleStatus.SkillType.NormalAttack.getActionStatus().getId());
    private static final M_SKILL_TYPE T_DEFENSE = new M_SKILL_TYPE((long) 2,
            BattleStatus.SkillType.Defense.getName(),
            BattleStatus.SkillType.Defense.getId(),
            (long) BattleStatus.SkillType.Defense.getActionStatus().getId());
    private static final M_SKILL_TYPE T_CHARGE = new M_SKILL_TYPE((long) 3,
            BattleStatus.SkillType.Charge.getName(),
            BattleStatus.SkillType.Charge.getId(),
            (long) BattleStatus.SkillType.Charge.getActionStatus().getId());
    private static final M_SKILL_TYPE T_SPECIAL_ATTACK = new M_SKILL_TYPE((long) 4,
            BattleStatus.SkillType.SpecialAttack.getName(),
            BattleStatus.SkillType.SpecialAttack.getId(),
            (long) BattleStatus.SkillType.SpecialAttack.getActionStatus().getId());


    private static final SKILL DEFENSE = new SKILL((long) 1, "防御", sp(0), effect(0), (long) T_DEFENSE.getSkill_type_id());
    private static final SKILL CHARGE = new SKILL((long) 2, "チャージ", sp(0), effect(1), (long) T_CHARGE.getSkill_type_id());

    private static final SKILL MAGIC_ALLOW = new SKILL((long) 100, "マジックアロー", sp(1), effect(8), (long) T_NORMAL_ATTACK.getSkill_type_id());
    private static final SKILL FIRE_BALL = new SKILL((long) 102, "ファイアーボール", sp(2), effect(22), (long) T_NORMAL_ATTACK.getSkill_type_id());
    private static final SKILL ICE_SPEAR = new SKILL((long) 103, "アイススピア", sp(3), effect(31), (long) T_NORMAL_ATTACK.getSkill_type_id());
    private static final SKILL WIND_CUTTER = new SKILL((long) 104, "ウィンドカッター", sp(4), effect(36), (long) T_NORMAL_ATTACK.getSkill_type_id());
    private static final SKILL THUNDER_BOLT = new SKILL((long) 105, "サンダーボルト", sp(5), effect(47), (long) T_NORMAL_ATTACK.getSkill_type_id());

    private static final SKILL TACKLE = new SKILL((long) 200, "体当たり", sp(1), effect(5), (long) T_NORMAL_ATTACK.getSkill_type_id());
    private static final SKILL BITE = new SKILL((long) 201, "噛み付く", sp(1), effect(6), (long) T_NORMAL_ATTACK.getSkill_type_id());
    private static final SKILL PECK = new SKILL((long) 202, "つつく", sp(1), effect(6), (long) T_NORMAL_ATTACK.getSkill_type_id());
    private static final SKILL CLEAVE = new SKILL((long) 203, "きりさく", sp(2), effect(17), (long) T_NORMAL_ATTACK.getSkill_type_id());
    private static final SKILL POISON_BREATH = new SKILL((long) 204, "毒の息", sp(2), effect(15), (long) T_NORMAL_ATTACK.getSkill_type_id());
    private static final SKILL FIRE_BREATH = new SKILL((long) 204, "ファイアブレス", sp(3), effect(30), (long) T_NORMAL_ATTACK.getSkill_type_id());


    public static List<M_ACTION_STATUS> getAllActionStatus() {
        List<M_ACTION_STATUS> result = new ArrayList<>();

        result.add(A_ATTACK);
        result.add(A_DEFENSE);
        result.add(A_CHARGE);

        return result;
    }

    public static List<M_SKILL_TYPE> getAllSkillType() {
        List<M_SKILL_TYPE> result = new ArrayList<>();

        result.add(T_NORMAL_ATTACK);
        result.add(T_DEFENSE);
        result.add(T_CHARGE);
        result.add(T_SPECIAL_ATTACK);

        return result;
    }

    public static List<SKILL> getAllSkill() {
        List<SKILL> result = new ArrayList<>();

        result.add(getDefense());
        result.add(getCharge());
        result.add(getMagicAllow());
        result.add(getFireBall());
        result.add(getIceSpear());
        result.add(getWindCutter());
        result.add(getThunderBolt());
        result.add(getTackle());
        result.add(getBite());
        result.add(getPeck());
        result.add(getCleave());
        result.add(getPoisonBreath());
        result.add(getFireBreath());

        return result;
    }

    private static int sp(int a) {
        return a;
    }

    private static int effect(int a) {
        return a;
    }

    public static SKILL getDefense() {
        return DEFENSE;
    }

    public static SKILL getCharge() {
        return CHARGE;
    }

    public static SKILL getMagicAllow() {
        return MAGIC_ALLOW;
    }

    public static SKILL getFireBall() {
        return FIRE_BALL;
    }

    public static SKILL getIceSpear() {
        return ICE_SPEAR;
    }

    public static SKILL getWindCutter() {
        return WIND_CUTTER;
    }

    public static SKILL getThunderBolt() {
        return THUNDER_BOLT;
    }

    public static SKILL getTackle() {
        return TACKLE;
    }

    public static SKILL getBite() {
        return BITE;
    }

    public static SKILL getPeck() {
        return PECK;
    }

    public static SKILL getCleave() {
        return CLEAVE;
    }

    public static SKILL getPoisonBreath() {
        return POISON_BREATH;
    }

    public static SKILL getFireBreath() {
        return FIRE_BREATH;
    }
}
