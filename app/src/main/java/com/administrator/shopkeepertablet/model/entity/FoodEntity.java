package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.administrator.shopkeepertablet.model.greendao.DaoSession;
import com.administrator.shopkeepertablet.model.greendao.SeasonEntityDao;
import com.administrator.shopkeepertablet.model.greendao.SpecEntityDao;
import com.administrator.shopkeepertablet.model.greendao.FoodEntityDao;


/**
 * Description:
 * Author CC
 * Time 2018/7/1
 */

@Entity
public class FoodEntity {

    /**
     * PRODUCTID : 1fffea9a-2cfe-4dfd-840d-033a36ba8f2b
     * RESTAURANTID : 4b176f0e-0553-4094-8181-5048641b20ef
     * ID : 1
     * PRODUCTNAME : 香芋味奶茶
     * PINYIN : xywnc香芋味奶茶
     * UNIT : 杯
     * MINUNIT : 1
     * PRODUCTTYPEID : c4ddb67d-7e70-436b-b3b3-5bea97b8bbb8
     * PRODUCTTYPENAME : 奶茶
     * PRICE : 1.0
     * PRODUCTFile : 香芋味奶茶.jpg
     * PRODUCTIMAGE : null
     * STATE : 1
     * REMARK :
     * TasteID : null
     * IsDaZhe : null
     * DaZhe : null
     * WarCount : 5
     * IsClose : 0
     * IsCloseName : 否
     * ProductCount : 1
     * ChuCaiType : 0
     * CanDiscount : 0
     * MemberPice : 0.01
     * SalesType : 1
     * AccordIng : 0
     * ProtuctShuXing : 0
     * ProductGive : null
     * TasteType : 0
     * PrintWay : 1
     */
    @Id
    @SerializedName("PRODUCTID")
    private String productId;

    @SerializedName("RESTAURANTID")
    private String restaurantId;

    @SerializedName("ID")
    private String id;

    @SerializedName("PRODUCTNAME")
    private String productName;

    @SerializedName("PINYIN")
    private String pinYin;

    @SerializedName("UNIT")
    private String unit;

    @SerializedName("MINUNIT")
    private String minUnit;

    @SerializedName("PRODUCTTYPEID")
    private String productTypeId;

    @SerializedName("PRODUCTTYPENAME")
    private String productTypeName;

    @SerializedName("PRICE")
    private double price;

    @SerializedName("PRODUCTFile")
    private String productFile;

    @SerializedName("PRODUCTIMAGE")
    private String productImage;

    @SerializedName("STATE")
    private int state;

    @SerializedName("REMARK")
    private String remark;

    @SerializedName("TasteID")
    private String tasteID;

    @SerializedName("isDaZhe")
    private String IsDaZhe;

    @SerializedName("DaZhe")
    private String daZhe;

    @SerializedName("WarCount")
    private String warCount;

    @SerializedName("isClose")
    private int IsClose;

    @SerializedName("IsCloseName")
    private String isCloseName;

    @SerializedName("ProductCount")
    private int productCount;

    @SerializedName("ChuCaiType")
    private String chuCaiType;

    @SerializedName("CanDiscount")
    private int canDiscount;

    @SerializedName("MemberPice")
    private double memberPice;

    @SerializedName("SalesType")
    private int salesType;

    @SerializedName("AccordIng")
    private String accordIng;

    @SerializedName("ProtuctShuXing")
    private String productProperty;

    @SerializedName("ProductGive")
    private String productGive;

    @SerializedName("TasteType")
    private String tasteType;

    @SerializedName("PrintWay")
    private String printWay;

    @ToMany(referencedJoinProperty = "productId")
    private List<SpecEntity> specEntityList;

    @ToMany(referencedJoinProperty = "productId")
    private List<SeasonEntity> seasonEntityList;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 587034604)
    private transient FoodEntityDao myDao;


    @Generated(hash = 832036182)
    public FoodEntity(String productId, String restaurantId, String id,
            String productName, String pinYin, String unit, String minUnit,
            String productTypeId, String productTypeName, double price,
            String productFile, String productImage, int state, String remark,
            String tasteID, String IsDaZhe, String daZhe, String warCount,
            int IsClose, String isCloseName, int productCount, String chuCaiType,
            int canDiscount, double memberPice, int salesType, String accordIng,
            String productProperty, String productGive, String tasteType,
            String printWay) {
        this.productId = productId;
        this.restaurantId = restaurantId;
        this.id = id;
        this.productName = productName;
        this.pinYin = pinYin;
        this.unit = unit;
        this.minUnit = minUnit;
        this.productTypeId = productTypeId;
        this.productTypeName = productTypeName;
        this.price = price;
        this.productFile = productFile;
        this.productImage = productImage;
        this.state = state;
        this.remark = remark;
        this.tasteID = tasteID;
        this.IsDaZhe = IsDaZhe;
        this.daZhe = daZhe;
        this.warCount = warCount;
        this.IsClose = IsClose;
        this.isCloseName = isCloseName;
        this.productCount = productCount;
        this.chuCaiType = chuCaiType;
        this.canDiscount = canDiscount;
        this.memberPice = memberPice;
        this.salesType = salesType;
        this.accordIng = accordIng;
        this.productProperty = productProperty;
        this.productGive = productGive;
        this.tasteType = tasteType;
        this.printWay = printWay;
    }

    @Generated(hash = 2051124127)
    public FoodEntity() {
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? "" : productId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId == null ? "" : restaurantId;
    }

//    public String getuId() {
//        return uId;
//    }
//
//    public void setuId(String uId) {
//        this.uId  = uId == null ? "" : uId;
//    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? "" : productName;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin == null ? "" : pinYin;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? "" : unit;
    }

    public String getMinUnit() {
        return minUnit;
    }

    public void setMinUnit(String minUnit) {
        this.minUnit = minUnit == null ? "" : minUnit;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId == null ? "" : productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName == null ? "" : productTypeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductFile() {
        return productFile;
    }

    public void setProductFile(String productFile) {
        this.productFile = productFile == null ? "" : productFile;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage == null ? "" : productImage;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? "" : remark;
    }

    public String getTasteID() {
        return tasteID;
    }

    public void setTasteID(String tasteID) {
        this.tasteID = tasteID == null ? "" : tasteID;
    }

    public String getIsDaZhe() {
        return IsDaZhe;
    }

    public void setIsDaZhe(String isDaZhe) {
        IsDaZhe = isDaZhe == null ? "" : isDaZhe;
    }

    public String getDaZhe() {
        return daZhe;
    }

    public void setDaZhe(String daZhe) {
        this.daZhe = daZhe == null ? "" : daZhe;
    }

    public String getWarCount() {
        return warCount;
    }

    public void setWarCount(String warCount) {
        this.warCount = warCount == null ? "" : warCount;
    }

    public int getIsClose() {
        return IsClose;
    }

    public void setIsClose(int isClose) {
        this.IsClose = isClose;
    }

    public String getIsCloseName() {
        return isCloseName;
    }

    public void setIsCloseName(String isCloseName) {
        this.isCloseName = isCloseName == null ? "" : isCloseName;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public String getChuCaiType() {
        return chuCaiType;
    }

    public void setChuCaiType(String chuCaiType) {
        this.chuCaiType = chuCaiType == null ? "" : chuCaiType;
    }

    public int getCanDiscount() {
        return canDiscount;
    }

    public void setCanDiscount(int canDiscount) {
        this.canDiscount = canDiscount;
    }

    public double getMemberPice() {
        return memberPice;
    }

    public void setMemberPice(double memberPice) {
        this.memberPice = memberPice;
    }

    public int getSalesType() {
        return salesType;
    }

    public void setSalesType(int salesType) {
        this.salesType = salesType;
    }

    public String getAccordIng() {
        return accordIng;
    }

    public void setAccordIng(String accordIng) {
        this.accordIng = accordIng == null ? "" : accordIng;
    }

    public String getProductProperty() {
        return productProperty;
    }

    public void setProductProperty(String productProperty) {
        this.productProperty = productProperty == null ? "" : productProperty;
    }

    public String getProductGive() {
        return productGive;
    }

    public void setProductGive(String productGive) {
        this.productGive = productGive == null ? "" : productGive;
    }

    public String getTasteType() {
        return tasteType;
    }

    public void setTasteType(String tasteType) {
        this.tasteType = tasteType == null ? "" : tasteType;
    }

    public String getPrintWay() {
        return printWay;
    }

    public void setPrintWay(String printWay) {
        this.printWay = printWay == null ? "" : printWay;
    }

    @Override
    public String toString() {
        return "FoodEntity{" +
                ", productId='" + productId + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
//                ", uId='" + uId + '\'' +
                ", productName='" + productName + '\'' +
                ", pinYin='" + pinYin + '\'' +
                ", unit='" + unit + '\'' +
                ", minUnit='" + minUnit + '\'' +
                ", productTypeId='" + productTypeId + '\'' +
                ", productTypeName='" + productTypeName + '\'' +
                ", price=" + price +
                ", productFile='" + productFile + '\'' +
                ", productImage='" + productImage + '\'' +
                ", state=" + state +
                ", remark='" + remark + '\'' +
                ", tasteID='" + tasteID + '\'' +
                ", IsDaZhe='" + IsDaZhe + '\'' +
                ", daZhe='" + daZhe + '\'' +
                ", warCount='" + warCount + '\'' +
                ", IsClose=" + IsClose +
                ", isCloseName='" + isCloseName + '\'' +
                ", productCount=" + productCount +
                ", chuCaiType='" + chuCaiType + '\'' +
                ", canDiscount=" + canDiscount +
                ", memberPice=" + memberPice +
                ", salesType=" + salesType +
                ", accordIng='" + accordIng + '\'' +
                ", productProperty='" + productProperty + '\'' +
                ", productGive='" + productGive + '\'' +
                ", tasteType='" + tasteType + '\'' +
                ", printWay='" + printWay + '\'' +
                '}';
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 159539771)
    public List<SpecEntity> getSpecEntityList() {
        if (specEntityList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SpecEntityDao targetDao = daoSession.getSpecEntityDao();
            List<SpecEntity> specEntityListNew = targetDao
                    ._queryFoodEntity_SpecEntityList(productId);
            synchronized (this) {
                if (specEntityList == null) {
                    specEntityList = specEntityListNew;
                }
            }
        }
        return specEntityList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1400662394)
    public synchronized void resetSpecEntityList() {
        specEntityList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 856850312)
    public List<SeasonEntity> getSeasonEntityList() {
        if (seasonEntityList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SeasonEntityDao targetDao = daoSession.getSeasonEntityDao();
            List<SeasonEntity> seasonEntityListNew = targetDao
                    ._queryFoodEntity_SeasonEntityList(productId);
            synchronized (this) {
                if (seasonEntityList == null) {
                    seasonEntityList = seasonEntityListNew;
                }
            }
        }
        return seasonEntityList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2113497747)
    public synchronized void resetSeasonEntityList() {
        seasonEntityList = null;
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
    @Generated(hash = 211642632)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFoodEntityDao() : null;
    }
}
