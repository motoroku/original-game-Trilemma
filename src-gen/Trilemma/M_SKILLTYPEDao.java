package Trilemma;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import Trilemma.M_SKILLTYPE;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table M__SKILLTYPE.
*/
public class M_SKILLTYPEDao extends AbstractDao<M_SKILLTYPE, Long> {

    public static final String TABLENAME = "M__SKILLTYPE";

    /**
     * Properties of entity M_SKILLTYPE.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Skill_type_name = new Property(1, String.class, "skill_type_name", false, "SKILL_TYPE_NAME");
        public final static Property Action_status_id = new Property(2, Long.class, "action_status_id", false, "ACTION_STATUS_ID");
    };

    private DaoSession daoSession;


    public M_SKILLTYPEDao(DaoConfig config) {
        super(config);
    }
    
    public M_SKILLTYPEDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'M__SKILLTYPE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'SKILL_TYPE_NAME' TEXT," + // 1: skill_type_name
                "'ACTION_STATUS_ID' INTEGER);"); // 2: action_status_id
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'M__SKILLTYPE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, M_SKILLTYPE entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String skill_type_name = entity.getSkill_type_name();
        if (skill_type_name != null) {
            stmt.bindString(2, skill_type_name);
        }
 
        Long action_status_id = entity.getAction_status_id();
        if (action_status_id != null) {
            stmt.bindLong(3, action_status_id);
        }
    }

    @Override
    protected void attachEntity(M_SKILLTYPE entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public M_SKILLTYPE readEntity(Cursor cursor, int offset) {
        M_SKILLTYPE entity = new M_SKILLTYPE( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // skill_type_name
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // action_status_id
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, M_SKILLTYPE entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSkill_type_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAction_status_id(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(M_SKILLTYPE entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(M_SKILLTYPE entity) {
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
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getM_ACTIONSTATUSDao().getAllColumns());
            builder.append(" FROM M__SKILLTYPE T");
            builder.append(" LEFT JOIN M__ACTIONSTATUS T0 ON T.'ACTION_STATUS_ID'=T0.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected M_SKILLTYPE loadCurrentDeep(Cursor cursor, boolean lock) {
        M_SKILLTYPE entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        M_ACTIONSTATUS m_ACTIONSTATUS = loadCurrentOther(daoSession.getM_ACTIONSTATUSDao(), cursor, offset);
        entity.setM_ACTIONSTATUS(m_ACTIONSTATUS);

        return entity;    
    }

    public M_SKILLTYPE loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<M_SKILLTYPE> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<M_SKILLTYPE> list = new ArrayList<M_SKILLTYPE>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<M_SKILLTYPE> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<M_SKILLTYPE> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}