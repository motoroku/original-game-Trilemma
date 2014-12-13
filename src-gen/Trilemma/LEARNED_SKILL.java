package Trilemma;

import Trilemma.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table LEARNED__SKILL.
 */
public class LEARNED_SKILL {

    private Long id;
    private Integer position_no;
    private Boolean is_set_flg;
    private long character_id;
    private Long skill_id;
    private Long dungeon_id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient LEARNED_SKILLDao myDao;

    private SKILL sKILL;
    private Long sKILL__resolvedKey;

    private DUNGEON dUNGEON;
    private Long dUNGEON__resolvedKey;


    public LEARNED_SKILL() {
    }

    public LEARNED_SKILL(Long id) {
        this.id = id;
    }

    public LEARNED_SKILL(Long id, Integer position_no, Boolean is_set_flg, long character_id, Long skill_id, Long dungeon_id) {
        this.id = id;
        this.position_no = position_no;
        this.is_set_flg = is_set_flg;
        this.character_id = character_id;
        this.skill_id = skill_id;
        this.dungeon_id = dungeon_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLEARNED_SKILLDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosition_no() {
        return position_no;
    }

    public void setPosition_no(Integer position_no) {
        this.position_no = position_no;
    }

    public Boolean getIs_set_flg() {
        return is_set_flg;
    }

    public void setIs_set_flg(Boolean is_set_flg) {
        this.is_set_flg = is_set_flg;
    }

    public long getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(long character_id) {
        this.character_id = character_id;
    }

    public Long getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Long skill_id) {
        this.skill_id = skill_id;
    }

    public Long getDungeon_id() {
        return dungeon_id;
    }

    public void setDungeon_id(Long dungeon_id) {
        this.dungeon_id = dungeon_id;
    }

    /** To-one relationship, resolved on first access. */
    public SKILL getSKILL() {
        Long __key = this.skill_id;
        if (sKILL__resolvedKey == null || !sKILL__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SKILLDao targetDao = daoSession.getSKILLDao();
            SKILL sKILLNew = targetDao.load(__key);
            synchronized (this) {
                sKILL = sKILLNew;
            	sKILL__resolvedKey = __key;
            }
        }
        return sKILL;
    }

    public void setSKILL(SKILL sKILL) {
        synchronized (this) {
            this.sKILL = sKILL;
            skill_id = sKILL == null ? null : sKILL.getId();
            sKILL__resolvedKey = skill_id;
        }
    }

    /** To-one relationship, resolved on first access. */
    public DUNGEON getDUNGEON() {
        Long __key = this.dungeon_id;
        if (dUNGEON__resolvedKey == null || !dUNGEON__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DUNGEONDao targetDao = daoSession.getDUNGEONDao();
            DUNGEON dUNGEONNew = targetDao.load(__key);
            synchronized (this) {
                dUNGEON = dUNGEONNew;
            	dUNGEON__resolvedKey = __key;
            }
        }
        return dUNGEON;
    }

    public void setDUNGEON(DUNGEON dUNGEON) {
        synchronized (this) {
            this.dUNGEON = dUNGEON;
            dungeon_id = dUNGEON == null ? null : dUNGEON.getId();
            dUNGEON__resolvedKey = dungeon_id;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}