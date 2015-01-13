package Trilemma;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import Trilemma.WEAPON;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table WEAPON.
*/
public class WEAPONDao extends AbstractDao<WEAPON, Long> {

    public static final String TABLENAME = "WEAPON";

    /**
     * Properties of entity WEAPON.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Weapon_name = new Property(1, String.class, "weapon_name", false, "WEAPON_NAME");
        public final static Property Attack_point = new Property(2, Integer.class, "attack_point", false, "ATTACK_POINT");
    };


    public WEAPONDao(DaoConfig config) {
        super(config);
    }
    
    public WEAPONDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'WEAPON' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'WEAPON_NAME' TEXT," + // 1: weapon_name
                "'ATTACK_POINT' INTEGER);"); // 2: attack_point
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'WEAPON'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, WEAPON entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String weapon_name = entity.getWeapon_name();
        if (weapon_name != null) {
            stmt.bindString(2, weapon_name);
        }
 
        Integer attack_point = entity.getAttack_point();
        if (attack_point != null) {
            stmt.bindLong(3, attack_point);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public WEAPON readEntity(Cursor cursor, int offset) {
        WEAPON entity = new WEAPON( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // weapon_name
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2) // attack_point
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, WEAPON entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setWeapon_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAttack_point(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(WEAPON entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(WEAPON entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}