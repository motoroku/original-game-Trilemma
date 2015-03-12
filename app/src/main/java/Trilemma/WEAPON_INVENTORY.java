package Trilemma;

import Trilemma.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table WEAPON__INVENTORY.
 */
public class WEAPON_INVENTORY {

    private Long id;
    private Integer number;
    private Integer enhanced_point;
    private java.util.Date update_datetime;
    private Long weapon_id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient WEAPON_INVENTORYDao myDao;

    private WEAPON wEAPON;
    private Long wEAPON__resolvedKey;


    public WEAPON_INVENTORY() {
    }

    public WEAPON_INVENTORY(Long id) {
        this.id = id;
    }

    public WEAPON_INVENTORY(Long id, Integer number, Integer enhanced_point, java.util.Date update_datetime, Long weapon_id) {
        this.id = id;
        this.number = number;
        this.enhanced_point = enhanced_point;
        this.update_datetime = update_datetime;
        this.weapon_id = weapon_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getWEAPON_INVENTORYDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getEnhanced_point() {
        return enhanced_point;
    }

    public void setEnhanced_point(Integer enhanced_point) {
        this.enhanced_point = enhanced_point;
    }

    public java.util.Date getUpdate_datetime() {
        return update_datetime;
    }

    public void setUpdate_datetime(java.util.Date update_datetime) {
        this.update_datetime = update_datetime;
    }

    public Long getWeapon_id() {
        return weapon_id;
    }

    public void setWeapon_id(Long weapon_id) {
        this.weapon_id = weapon_id;
    }

    /** To-one relationship, resolved on first access. */
    public WEAPON getWEAPON() {
        Long __key = this.weapon_id;
        if (wEAPON__resolvedKey == null || !wEAPON__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            WEAPONDao targetDao = daoSession.getWEAPONDao();
            WEAPON wEAPONNew = targetDao.load(__key);
            synchronized (this) {
                wEAPON = wEAPONNew;
            	wEAPON__resolvedKey = __key;
            }
        }
        return wEAPON;
    }

    public void setWEAPON(WEAPON wEAPON) {
        synchronized (this) {
            this.wEAPON = wEAPON;
            weapon_id = wEAPON == null ? null : wEAPON.getId();
            wEAPON__resolvedKey = weapon_id;
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