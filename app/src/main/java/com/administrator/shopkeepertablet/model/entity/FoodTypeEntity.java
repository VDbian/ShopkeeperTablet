package com.administrator.shopkeepertablet.model.entity;

import com.administrator.shopkeepertablet.model.greendao.DaoSession;
import com.administrator.shopkeepertablet.model.greendao.FoodEntityDao;
import com.administrator.shopkeepertablet.model.greendao.FoodTypeEntityDao;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Description:
 * Author CC
 * Time 2018/7/9
 */

@Entity
public class FoodTypeEntity {
    @Id
    @SerializedName("PRODUCTTYPEID")
    private String productTypeId;

    @SerializedName("RESTAURANTID")
    private String restaurantId;

    @SerializedName("PRODUCTTYPENAME")
    private String productTypeName;

    @SerializedName("ORDERNO")
    private String orderNo;


//    private String PRODUCTTYPENAME;
//    private String PRODUCTTYPEID;
//    private String RESTAURANTID;
//    private int ORDERNO;
    @SerializedName("Type")
    private boolean type;


    @ToMany(referencedJoinProperty = "productTypeId")
    private List<FoodEntity> foodEntityList;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1225180916)
    private transient FoodTypeEntityDao myDao;

    @Generated(hash = 1012388691)
    public FoodTypeEntity(String productTypeId, String restaurantId, String productTypeName,
            String orderNo, boolean type) {
        this.productTypeId = productTypeId;
        this.restaurantId = restaurantId;
        this.productTypeName = productTypeName;
        this.orderNo = orderNo;
        this.type = type;
    }

    @Generated(hash = 431357427)
    public FoodTypeEntity() {
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "FoodTypeEntity{" +
                "productTypeId='" + productTypeId + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", productTypeName='" + productTypeName + '\'' +
                ", orderNo='" + orderNo + '\'' +
                '}';
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 631427572)
    public List<FoodEntity> getFoodEntityList() {
        if (foodEntityList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FoodEntityDao targetDao = daoSession.getFoodEntityDao();
            List<FoodEntity> foodEntityListNew = targetDao
                    ._queryFoodTypeEntity_FoodEntityList(productTypeId);
            synchronized (this) {
                if (foodEntityList == null) {
                    foodEntityList = foodEntityListNew;
                }
            }
        }
        return foodEntityList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1847149399)
    public synchronized void resetFoodEntityList() {
        foodEntityList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 774968030)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFoodTypeEntityDao() : null;
    }

    public boolean getType() {
        return this.type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
