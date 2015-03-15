package service;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Trilemma.PLAYER_SKILL;
import Trilemma.PLAYER_SKILLDao;
import Trilemma.SKILL;
import Trilemma.SKILLDao;
import dao.DaoManager;

/**
 * Created by mori_yu on 2015/04/04.
 */
public class SkillLearningService {
    DaoManager dao;
    PLAYER_SKILLDao playerSkillDao;
    List<PLAYER_SKILL> playerSkillList;

    public SkillLearningService(DaoManager dao) {
        this.dao = dao;
        playerSkillDao = dao.session.getPLAYER_SKILLDao();
    }

    public boolean learnSkill(SKILL learnedSkill) {
        playerSkillList = getAllPlayerSkills();
        long skillId = learnedSkill.getId();
        long id = playerSkillList.size() + 1;
        PLAYER_SKILL skill = new PLAYER_SKILL(id, 0, skillId);

        if (isContainSkill(skillId)) {
            return false;
        } else {
            playerSkillDao.insertOrReplace(skill);
        }

        return false;
    }

    public void setSkill(int position, PLAYER_SKILL skill) {
        playerSkillList = getAllPlayerSkills();
        long duplicatedSkillId = 0;
        PLAYER_SKILL duplicatedSkill = new PLAYER_SKILL();
        for (int i = 0; i < playerSkillList.size(); i++) {
            if (position == playerSkillList.get(i).getPosition()) {
                duplicatedSkill = playerSkillList.get(i);
                duplicatedSkillId = duplicatedSkill.getId();
            } else {
                continue;
            }
        }

        skill.setPosition(position);
        if (duplicatedSkillId == 0) {
            playerSkillDao.update(skill);
        } else if(skill.getId() != duplicatedSkillId) {
            duplicatedSkill.setPosition(0);
            playerSkillDao.update(duplicatedSkill);
            playerSkillDao.update(skill);
        }
    }

    public List<PLAYER_SKILL> getAllPlayerSkills() {
        return playerSkillDao.loadAll();
    }

    public List<PLAYER_SKILL> getSettingSkills() {
        return playerSkillDao.queryBuilder()
                .where(PLAYER_SKILLDao.Properties.Position.notEq(0))
                .orderAsc(PLAYER_SKILLDao.Properties.Position).list();
    }

    private boolean isContainSkill(long id) {
        for (int i = 0; i < playerSkillList.size(); i++) {
            long skillId = playerSkillList.get(i).getSkill_id();
            if (skillId == id) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

}
