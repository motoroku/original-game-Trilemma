package data;

import java.util.ArrayList;
import java.util.List;

import Trilemma.LEARNED_SKILL;
import Trilemma.PLAYER_SKILL;

/**
 * Created by mori_yu on 2015/03/15.
 */
public class Const_LearnedSkill {

    private static final LEARNED_SKILL 地下室のスライム_1 = new LEARNED_SKILL((long) 1, Const_Monster.get地下室のスライム().getId(), Const_Skill.getTackle().getId(), Const_PLACE.getNo1Basement().getId());
    private static final LEARNED_SKILL 凶暴なカラス_1 = new LEARNED_SKILL((long) 2,  Const_Monster.get凶暴なカラス().getId(), Const_Skill.getPeck().getId(), Const_PLACE.getNo2GreenField().getId());
    private static final LEARNED_SKILL 空腹のイノシシ_1 = new LEARNED_SKILL((long) 3,  Const_Monster.get空腹のイノシシ().getId(), Const_Skill.getBite().getId(), Const_PLACE.getNo2GreenField().getId());
    private static final LEARNED_SKILL 洞窟のスライム_1 = new LEARNED_SKILL((long) 4,  Const_Monster.get洞窟のスライム().getId(), Const_Skill.getTackle().getId(), Const_PLACE.getNo3Cave().getId());
    private static final LEARNED_SKILL 野生のパンダ_1 = new LEARNED_SKILL((long) 5,  Const_Monster.get野生のパンダ().getId(), Const_Skill.getBite().getId(), Const_PLACE.getNo3Cave().getId());
    private static final LEARNED_SKILL 野生のパンダ_2 = new LEARNED_SKILL((long) 6,  Const_Monster.get野生のパンダ().getId(), Const_Skill.getCleave().getId(), Const_PLACE.getNo3Cave().getId());
    private static final LEARNED_SKILL 育ちすぎたマタンゴ_1 = new LEARNED_SKILL((long) 7, Const_Monster.get育ちすぎたマタンゴ().getId(), Const_Skill.getTackle().getId(), Const_PLACE.getNo3Cave().getId());
    private static final LEARNED_SKILL 育ちすぎたマタンゴ_2 = new LEARNED_SKILL((long) 8, Const_Monster.get育ちすぎたマタンゴ().getId(), Const_Skill.getPoisonBreath().getId(), Const_PLACE.getNo3Cave().getId());
    private static final LEARNED_SKILL 野良ドラゴン_1 = new LEARNED_SKILL((long) 9,  Const_Monster.get野良ドラゴン().getId(), Const_Skill.getBite().getId(), Const_PLACE.getNo4RockMountain().getId());
    private static final LEARNED_SKILL 野良ドラゴン_2 = new LEARNED_SKILL((long) 10, Const_Monster.get野良ドラゴン().getId(), Const_Skill.getCleave().getId(), Const_PLACE.getNo4RockMountain().getId());
    private static final LEARNED_SKILL 野良ドラゴン_3 = new LEARNED_SKILL((long) 11, Const_Monster.get野良ドラゴン().getId(), Const_Skill.getFireBreath().getId(), Const_PLACE.getNo4RockMountain().getId());


    private static final PLAYER_SKILL 初めの魔法 = new PLAYER_SKILL((long)1, 1,Const_Skill.getMagicAllow().getId());

    public static List<LEARNED_SKILL> getAllLearnedSkill(){
        List<LEARNED_SKILL> result = new ArrayList<>();

        result.add(get地下室のスライム1());
        result.add(get凶暴なカラス1());
        result.add(get空腹のイノシシ1());
        result.add(get洞窟のスライム1());
        result.add(get野生のパンダ1());
        result.add(get野生のパンダ2());
        result.add(get育ちすぎたマタンゴ1());
        result.add(get育ちすぎたマタンゴ2());
        result.add(get野良ドラゴン1());
        result.add(get野良ドラゴン2());
        result.add(get野良ドラゴン3());

        return result;
    }

    public static LEARNED_SKILL get地下室のスライム1() {
        return 地下室のスライム_1;
    }

    public static LEARNED_SKILL get凶暴なカラス1() {
        return 凶暴なカラス_1;
    }

    public static LEARNED_SKILL get空腹のイノシシ1() {
        return 空腹のイノシシ_1;
    }

    public static LEARNED_SKILL get洞窟のスライム1() {
        return 洞窟のスライム_1;
    }

    public static LEARNED_SKILL get野生のパンダ1() {
        return 野生のパンダ_1;
    }

    public static LEARNED_SKILL get野生のパンダ2() {
        return 野生のパンダ_2;
    }

    public static LEARNED_SKILL get育ちすぎたマタンゴ1() {
        return 育ちすぎたマタンゴ_1;
    }

    public static LEARNED_SKILL get育ちすぎたマタンゴ2() {
        return 育ちすぎたマタンゴ_2;
    }

    public static LEARNED_SKILL get野良ドラゴン1() {
        return 野良ドラゴン_1;
    }

    public static LEARNED_SKILL get野良ドラゴン2() {
        return 野良ドラゴン_2;
    }

    public static LEARNED_SKILL get野良ドラゴン3() {
        return 野良ドラゴン_3;
    }

    public static PLAYER_SKILL get初めの魔法() {
        return 初めの魔法;
    }
}
