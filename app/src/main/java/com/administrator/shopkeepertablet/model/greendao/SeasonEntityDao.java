package com.administrator.shopkeepertablet.model.greendao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.administrator.shopkeepertablet.model.entity.SeasonEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SEASON_ENTITY".
*/
public class SeasonEntityDao extends AbstractDao<SeasonEntity, String> {

    public static final String TABLENAME = "SEASON_ENTITY";

    /**
     * Properties of entity SeasonEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property SeasonId = new Property(0, String.class, "seasonId", true, "SEASON_ID");
        public final static Property ProductId = new Property(1, String.class, "productId", false, "PRODUCT_ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Price = new Property(3, String.class, "price", false, "PRICE");
        public final static Property RestaurantId = new Property(4, String.class, "restaurantId", false, "RESTAURANT_ID");
        public final static Property ProductName = new Property(5, String.class, "productName", false, "PRODUCT_NAME");
        public final static Property Type = new Property(6, String.class, "type", false, "TYPE");
    }

    private Query<SeasonEntity> foodEntity_SeasonEntityListQuery;

    public SeasonEntityDao(DaoConfig config) {
        super(config);
    }
    
    public SeasonEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SEASON_ENTITY\" (" + //
                "\"SEASON_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: seasonId
                "\"PRODUCT_ID\" TEXT," + // 1: productId
                "\"NAME\" TEXT," + // 2: name
                "\"PRICE\" TEXT," + // 3: price
                "\"RESTAURANT_ID\" TEXT," + // 4: restaurantId
                "\"PRODUCT_NAME\" TEXT," + // 5: productName
                "\"TYPE\" TEXT);"); // 6: type
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SEASON_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SeasonEntity entity) {
        stmt.clearBindings();
 
        String seasonId = entity.getSeasonId();
        if (seasonId != null) {
            stmt.bindString(1, seasonId);
        }
 
        String productId = entity.getProductId();
        if (productId != null) {
            stmt.bindString(2, productId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(4, price);
        }
 
        String restaurantId = entity.getRestaurantId();
        if (restaurantId != null) {
            stmt.bindString(5, restaurantId);
        }
 
        String productName = entity.getProductName();
        if (productName != null) {
            stmt.bindString(6, productName);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(7, type);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SeasonEntity entity) {
        stmt.clearBindings();
 
        String seasonId = entity.getSeasonId();
        if (seasonId != null) {
            stmt.bindString(1, seasonId);
        }
 
        String productId = entity.getProductId();
        if (productId != null) {
            stmt.bindString(2, productId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(4, price);
        }
 
        String restaurantId = entity.getRestaurantId();
        if (restaurantId != null) {
            stmt.bindString(5, restaurantId);
        }
 
        String productName = entity.getProductName();
        if (productName != null) {
            stmt.bindString(6, productName);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(7, type);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public SeasonEntity readEntity(Cursor cursor, int offset) {
        SeasonEntity entity = new SeasonEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // seasonId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // productId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // price
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // restaurantId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // productName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // type
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SeasonEntity entity, int offset) {
        entity.setSeasonId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setProductId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPrice(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setRestaurantId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setProductName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setType(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final String updateKeyAfterInsert(SeasonEntity entity, long rowId) {
        return entity.getSeasonId();
    }
    
    @Override
    public String getKey(SeasonEntity entity) {
        if(entity != null) {
            return entity.getSeasonId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SeasonEntity entity) {
        return entity.getSeasonId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "seasonEntityList" to-many relationship of FoodEntity. */
    public List<SeasonEntity> _queryFoodEntity_SeasonEntityList(String productId) {
        synchronized (this) {
            if (foodEntity_SeasonEntityListQuery == null) {
                QueryBuilder<SeasonEntity> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ProductId.eq(null));
                foodEntity_SeasonEntityListQuery = queryBuilder.build();
            }
        }
        Query<SeasonEntity> query = foodEntity_SeasonEntityListQuery.forCurrentThread();
        query.setParameter(0, productId);
        return query.list();
    }

}
