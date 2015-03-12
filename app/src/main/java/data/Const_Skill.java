package data;

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

    private static final M_ACTION_STATUS ATTACK_A = new M_ACTION_STATUS((long) BattleStatus.ActionStatus.攻撃.getId(),
            BattleStatus.ActionStatus.攻撃.getValue(), DRAW, LOSE, WIN);
    private static final M_ACTION_STATUS DEFENSE_A = new M_ACTION_STATUS((long) BattleStatus.ActionStatus.防御.getId(),
            BattleStatus.ActionStatus.防御.getValue(), WIN, DRAW, LOSE);
    private static final M_ACTION_STATUS CHARGE_A = new M_ACTION_STATUS((long) BattleStatus.ActionStatus.チャージ.getId(),
            BattleStatus.ActionStatus.チャージ.getValue(), LOSE, WIN, DRAW);

    private static final M_SKILL_TYPE NORMAL_ATTACK = new M_SKILL_TYPE((long) 1,
            BattleStatus.SkillType.NormalAttack.getName(),
            BattleStatus.SkillType.NormalAttack.getId(),
            (long) BattleStatus.SkillType.NormalAttack.getActionStatus().getId());
    private static final M_SKILL_TYPE DEFENSE = new M_SKILL_TYPE((long) 2,
            BattleStatus.SkillType.Defense.getName(),
            BattleStatus.SkillType.Defense.getId(),
            (long) BattleStatus.SkillType.Defense.getActionStatus().getId());
    private static final M_SKILL_TYPE CHARGE = new M_SKILL_TYPE((long) 3,
            BattleStatus.SkillType.Charge.getName(),
            BattleStatus.SkillType.Charge.getId(),
            (long) BattleStatus.SkillType.Charge.getActionStatus().getId());
    private static final M_SKILL_TYPE SPECIAL_ATTACK = new M_SKILL_TYPE((long) 4,
            BattleStatus.SkillType.SpecialAttack.getName(),
            BattleStatus.SkillType.SpecialAttack.getId(),
            (long) BattleStatus.SkillType.SpecialAttack.getActionStatus().getId());

    private static final SKILL MAGIC_ALLOW = new SKILL((long) 1, "マジックアロー", sp(1),effect(0.8),(long)NORMAL_ATTACK.getSkill_type_id());
    private static final SKILL FIRE_BALL = new SKILL((long) 2, "ファイアーボール", sp(2),effect(2.2),(long)NORMAL_ATTACK.getSkill_type_id());
    private static final SKILL ICE_SPIR = new SKILL((long) 3, "アイススピア", sp(3),effect(3.1),(long)NORMAL_ATTACK.getSkill_type_id());
    private static final SKILL WIND_CUTTER = new SKILL((long) 4, "ウィンドカッター", sp(4),effect(3.6),(long)NORMAL_ATTACK.getSkill_type_id());
    private static final SKILL THANDER_BOLT = new SKILL((long) 5, "サンダーボルト", sp(5),effect(4.7),(long)NORMAL_ATTACK.getSkill_type_id());


    private static int sp(int a){return a;}
    private static int effect(double a){return (int)a*10;}
}
