package data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import Trilemma.PLAYER_STATUS;
import Trilemma.WEAPON;
import dao.DaoManager;
import service.SkillLearningService;

public class DataManager {
    List<WEAPON> weaponList = new ArrayList<WEAPON>();
    DaoManager dao;

    public DataManager(Context context){
        dao = new DaoManager(context);
    }

    public void createData(Context context) {
        dao.session.getWEAPONDao().insertOrReplaceInTx(Const_Item.getAllWeapon());
        dao.session.getARMORDao().insertOrReplaceInTx(Const_Item.getAllArmor());
        dao.session.getMONSTERDao().insertOrReplaceInTx(Const_Monster.getAllMonster());
        dao.session.getDUNGEONDao().insertOrReplaceInTx(Const_PLACE.getAllDungeon());
        dao.session.getTOWNDao().insertOrReplaceInTx(Const_PLACE.getAllTown());
        updatePlayerStatus(dao);
        dao.session.getM_ACTION_STATUSDao().insertOrReplaceInTx(Const_Skill.getAllActionStatus());
        dao.session.getM_SKILL_TYPEDao().insertOrReplaceInTx(Const_Skill.getAllSkillType());
        dao.session.getSKILLDao().insertOrReplaceInTx(Const_Skill.getAllSkill());
        dao.session.getLEARNED_SKILLDao().insertOrReplaceInTx(Const_LearnedSkill.getAllLearnedSkill());
        dao.session.getPLAYER_SKILLDao().insertOrReplace(Const_LearnedSkill.get初めの魔法());
    }

    public void setFirstSkill(){
        SkillLearningService skillLearningService = new SkillLearningService(dao);
        skillLearningService.setSkill(1,Const_LearnedSkill.get初めの魔法());
    }

    private void updatePlayerStatus(DaoManager dao) {
        List<PLAYER_STATUS> loadPlayerStatusList = dao.session.getPLAYER_STATUSDao().loadAll();
        List<String> loadStatusNameList = new ArrayList<>();
        for (int i = 0; i < loadPlayerStatusList.size(); i++) {
            loadStatusNameList.add(loadPlayerStatusList.get(i).getStatus_name());
        }

        List<PLAYER_STATUS> constPlayerStatusList = Const_PlayerStatus.getAllPlayerStatus();
        List<String> constStatusNameList = new ArrayList<>();
        for (int i = 0; i < constPlayerStatusList.size(); i++) {
            constStatusNameList.add(constPlayerStatusList.get(i).getStatus_name());
        }

        /*
        Constのものと比較して、
        足りない場合は追加する。
        存在する場合はそのまま。
        余分なものは削除する。
         */
        for (int i = 0; i < constStatusNameList.size(); i++) {
            if (loadStatusNameList.contains(constStatusNameList.get(i))) {
                continue;
            } else {
                dao.session.getPLAYER_STATUSDao().insert(constPlayerStatusList.get(i));
            }
        }
    }


}
