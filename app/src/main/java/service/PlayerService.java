package service;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Trilemma.ARMOR;
import Trilemma.PLAYER_SKILL;
import Trilemma.PLAYER_SKILLDao;
import Trilemma.PLAYER_STATUS;
import Trilemma.PLAYER_STATUSDao;
import Trilemma.SKILL;
import Trilemma.SKILLDao;
import Trilemma.WEAPON;
import dao.DaoManager;
import dao.PlayerDto;
import data.Const_Item;
import data.Const_PlayerStatus;
import de.greenrobot.dao.query.QueryBuilder;
import entity.Armor;
import entity.Player;
import entity.Weapon;

/**
 * Created by mori_yu on 2015/04/04.
 */
public class PlayerService {
    DaoManager dao;
    SKILLDao skillDao;
    PLAYER_SKILLDao playerSkillDao;
    SkillLearningService skillLearningService;

    public PlayerService(DaoManager dao) {
        this.dao = dao;
        skillDao = dao.session.getSKILLDao();
        playerSkillDao = dao.session.getPLAYER_SKILLDao();
        skillLearningService = new SkillLearningService(dao);
    }

    public Player createPlayer() {
        SKILL defense = dao.getDefaultDefenseSkill();
        SKILL charge = dao.getDefaultChargeSkill();

        List<SKILL> skillList = new ArrayList<>();
        List<PLAYER_SKILL> playerSkillList = skillLearningService.getSettingSkills();

        for (int i = 0; i < playerSkillList.size(); i++) {
            skillList.add(playerSkillList.get(i).getSKILL());
        }
        return new Player(getPlayerDto(), skillList, defense, charge);
    }

    public PlayerDto getPlayerDto() {
        PlayerDto player = new PlayerDto();

        List<PLAYER_STATUS> list = dao.session.getPLAYER_STATUSDao().loadAll();

        player.name = dao.session.getPLAYER_STATUSDao().queryBuilder()
                .where(PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getPlayerName().getStatus_name())).list()
                .get(0).getStatus_value();
        player.hp = Integer.parseInt(dao.session.getPLAYER_STATUSDao().queryBuilder()
                .where(PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getHp().getStatus_name())).list().get(0)
                .getStatus_value());
        player.maxSp = Integer.parseInt(dao.session.getPLAYER_STATUSDao().queryBuilder()
                .where(PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getMaxSp().getStatus_name())).list().get(0)
                .getStatus_value());
        player.baseSp = Integer.parseInt(dao.session.getPLAYER_STATUSDao().queryBuilder()
                .where(PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getBaseSp().getStatus_name())).list().get(0)
                .getStatus_value());
        player.level = Integer.parseInt(dao.session.getPLAYER_STATUSDao().queryBuilder()
                .where(PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getLevel().getStatus_name())).list().get(0)
                .getStatus_value());
        try {
            player.attack = Integer.parseInt(dao.session.getPLAYER_STATUSDao().queryBuilder()
                    .where(PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getAttack().getStatus_name())).list()
                    .get(0).getStatus_value());
        } catch (Exception e) {
            player.attack = 1;
        }
        try {
            player.defense = Integer.parseInt(dao.session.getPLAYER_STATUSDao().queryBuilder()
                    .where(PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getDefense().getStatus_name())).list()
                    .get(0).getStatus_value());
        } catch (Exception e) {
            player.defense = 1;
        }
        player.gold = Integer.parseInt(dao.session.getPLAYER_STATUSDao().queryBuilder()
                .where(PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getGold().getStatus_name())).list().get(0)
                .getStatus_value());
        player.exp = Integer.parseInt(dao.session.getPLAYER_STATUSDao().queryBuilder()
                .where(PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getExp().getStatus_name())).list().get(0)
                .getStatus_value());
        int weaponId = Integer.parseInt(dao.session.getPLAYER_STATUSDao().queryBuilder()
                .where(PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getEquipmentWeapon().getStatus_name())).list()
                .get(0).getStatus_value());
        int armorId = Integer.parseInt(dao.session.getPLAYER_STATUSDao().queryBuilder()
                .where(PLAYER_STATUSDao.Properties.Status_name.eq(Const_PlayerStatus.getEquipmentArmor().getStatus_name())).list()
                .get(0).getStatus_value());

        if (weaponId == 0) {
            player.weapon = new Weapon(Const_Item.getHand());
        } else {
            player.weapon = new Weapon(getWeapon(weaponId));
        }
        if (armorId == 0) {
            player.armor = new Armor(Const_Item.getSkin());
        } else {
            player.armor = new Armor(getArmor(armorId));
        }

        return player;
    }


    public WEAPON getWeapon(long weaponId) {
        QueryBuilder<WEAPON> weapons = dao.session.getWEAPONDao().queryBuilder()
                .where(Trilemma.WEAPONDao.Properties.Id.eq(weaponId));
        WEAPON weapon;
        if (weapons == null) {
            weapon = Const_Item.getHand();
        } else {
            weapon = weapons.list().get(0);
        }
        return weapon;
    }

    public ARMOR getArmor(long armorId) {
        ARMOR armor = dao.session.getARMORDao().queryBuilder().where(Trilemma.ARMORDao.Properties.Id.eq(armorId)).list()
                .get(0);
        if (armor == null) {
            armor = Const_Item.getSkin();
        }
        return armor;
    }
}
