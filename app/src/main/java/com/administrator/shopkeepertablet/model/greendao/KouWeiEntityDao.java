package com.administrator.shopkeepertablet.model.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.administrator.shopkeepertablet.model.entity.KouWeiEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "KOU_WEI_ENTITY".
*/
public class KouWeiEntityDao extends AbstractDao<KouWeiEntity, String> {

    public static final String TABLENAME = "KOU_WEI_ENTITY";

    /**
     * Properties of entity KouWeiEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property GuId = new Property(0, String.class, "guId", true, "GU_ID");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property No = new Property(2, String.class, "no", false, "NO");
        public final static Property RestaurantId = new Property(3, String.class, "restaurantId", false, "RESTAURANT_ID");
        public final static Property PatientId = new Property(4, String.class, "patientId", false, "PATIENT_ID");
        public final static Property Accord = new Property(5, String.class, "accord", false, "ACCORD");
        public final static Property Select = new Property(6, boolean.class, "select", false, "SELECT");
    }


    public KouWeiEntityDao(DaoConfig config) {
        super(config);
    }
    
    public KouWeiEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"KOU_WEI_ENTITY\" (" + //
                "\"GU_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: guId
                "\"NAME\" TEXT," + // 1: name
                "\"NO\" TEXT," + // 2: no
                "\"RESTAURANT_ID\" TEXT," + // 3: restaurantId
                "\"PATIENT_ID\" TEXT," + // 4: patientId
                "\"ACCORD\" TEXT," + // 5: accord
                "\"SELECT\" INTEGER NOT NULL );"); // 6: select
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"KOU_WEI_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, KouWeiEntity entity) {
        stmt.clearBindings();
 
        String guId = entity.getGuId();
        if (guId != null) {
            stmt.bindString(1, guId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String no = entity.getNo();
        if (no != null) {
            stmt.bindString(3, no);
        }
 
        String restaurantId = entity.getRestaurantId();
        if (restaurantId != null) {
            stmt.bindString(4, restaurantId);
        }
 
        String patientId = entity.getPatientId();
        if (patientId != null) {
            stmt.bindString(5, patientId);
        }
 
        String accord = entity.getAccord();
        if (accord != null) {
            stmt.bindString(6, accord);
        }
        stmt.bindLong(7, entity.getSelect() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, KouWeiEntity entity) {
        stmt.clearBindings();
 
        String guId = entity.getGuId();
        if (guId != null) {
            stmt.bindString(1, guId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String no = entity.getNo();
        if (no != null) {
            stmt.bindString(3, no);
        }
 
        String restaurantId = entity.getRestaurantId();
        if (restaurantId != null) {
            stmt.bindString(4, restaurantId);
        }
 
        String patientId = entity.getPatientId();
        if (patientId != null) {
            stmt.bindString(5, patientId);
        }
 
        String accord = entity.getAccord();
        if (accord != null) {
            stmt.bindString(6, accord);
        }
        stmt.bindLong(7, entity.getSelect() ? 1L: 0L);
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public KouWeiEntity readEntity(Cursor cursor, int offset) {
        KouWeiEntity entity = new KouWeiEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // guId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // no
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // restaurantId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // patientId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // accord
            cursor.getShort(offset + 6) != 0 // select
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, KouWeiEntity entity, int offset) {
        entity.setGuId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNo(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setRestaurantId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPatientId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAccord(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSelect(cursor.getShort(offset + 6) != 0);
     }
    
    @Override
    protected final String updateKeyAfterInsert(KouWeiEntity entity, long rowId) {
        return entity.getGuId();
    }
    
    @Override
    public String getKey(KouWeiEntity entity) {
        if(entity != null) {
            return entity.getGuId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(KouWeiEntity entity) {
        return entity.getGuId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
