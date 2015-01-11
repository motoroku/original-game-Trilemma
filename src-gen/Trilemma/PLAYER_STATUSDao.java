package Trilemma;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import Trilemma.PLAYER_STATUS;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table PLAYER__STATUS.
*/
public class PLAYER_STATUSDao extends AbstractDao<PLAYER_STATUS, Long> {

    public static final String TABLENAME = "PLAYER__STATUS";

    /**
     * Properties of entity PLAYER_STATUS.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Status_name = new Property(1, String.class, "status_name", false, "STATUS_NAME");
        public final static Property Status_value = new Property(2, String.class, "status_value", false, "STATUS_VALUE");
    };


    public PLAYER_STATUSDao(DaoConfig config) {
        super(config);
    }
    
    public PLAYER_STATUSDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'PLAYER__STATUS' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'STATUS_NAME' TEXT UNIQUE ," + // 1: status_name
                "'STATUS_VALUE' TEXT);"); // 2: status_value
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'PLAYER__STATUS'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, PLAYER_STATUS entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String status_name = entity.getStatus_name();
        if (status_name != null) {
            stmt.bindString(2, status_name);
        }
 
        String status_value = entity.getStatus_value();
        if (status_value != null) {
            stmt.bindString(3, status_value);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public PLAYER_STATUS readEntity(Cursor cursor, int offset) {
        PLAYER_STATUS entity = new PLAYER_STATUS( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // status_name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // status_value
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, PLAYER_STATUS entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStatus_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStatus_value(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(PLAYER_STATUS entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(PLAYER_STATUS entity) {
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
