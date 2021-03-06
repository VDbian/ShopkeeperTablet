package com.administrator.shopkeepertablet.model.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.KouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.ProductKouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.SeasonEntity;
import com.administrator.shopkeepertablet.model.entity.SpecEntity;

import com.administrator.shopkeepertablet.model.greendao.FoodEntityDao;
import com.administrator.shopkeepertablet.model.greendao.FoodTypeEntityDao;
import com.administrator.shopkeepertablet.model.greendao.KouWeiEntityDao;
import com.administrator.shopkeepertablet.model.greendao.ProductKouWeiEntityDao;
import com.administrator.shopkeepertablet.model.greendao.SeasonEntityDao;
import com.administrator.shopkeepertablet.model.greendao.SpecEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig foodEntityDaoConfig;
    private final DaoConfig foodTypeEntityDaoConfig;
    private final DaoConfig kouWeiEntityDaoConfig;
    private final DaoConfig productKouWeiEntityDaoConfig;
    private final DaoConfig seasonEntityDaoConfig;
    private final DaoConfig specEntityDaoConfig;

    private final FoodEntityDao foodEntityDao;
    private final FoodTypeEntityDao foodTypeEntityDao;
    private final KouWeiEntityDao kouWeiEntityDao;
    private final ProductKouWeiEntityDao productKouWeiEntityDao;
    private final SeasonEntityDao seasonEntityDao;
    private final SpecEntityDao specEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        foodEntityDaoConfig = daoConfigMap.get(FoodEntityDao.class).clone();
        foodEntityDaoConfig.initIdentityScope(type);

        foodTypeEntityDaoConfig = daoConfigMap.get(FoodTypeEntityDao.class).clone();
        foodTypeEntityDaoConfig.initIdentityScope(type);

        kouWeiEntityDaoConfig = daoConfigMap.get(KouWeiEntityDao.class).clone();
        kouWeiEntityDaoConfig.initIdentityScope(type);

        productKouWeiEntityDaoConfig = daoConfigMap.get(ProductKouWeiEntityDao.class).clone();
        productKouWeiEntityDaoConfig.initIdentityScope(type);

        seasonEntityDaoConfig = daoConfigMap.get(SeasonEntityDao.class).clone();
        seasonEntityDaoConfig.initIdentityScope(type);

        specEntityDaoConfig = daoConfigMap.get(SpecEntityDao.class).clone();
        specEntityDaoConfig.initIdentityScope(type);

        foodEntityDao = new FoodEntityDao(foodEntityDaoConfig, this);
        foodTypeEntityDao = new FoodTypeEntityDao(foodTypeEntityDaoConfig, this);
        kouWeiEntityDao = new KouWeiEntityDao(kouWeiEntityDaoConfig, this);
        productKouWeiEntityDao = new ProductKouWeiEntityDao(productKouWeiEntityDaoConfig, this);
        seasonEntityDao = new SeasonEntityDao(seasonEntityDaoConfig, this);
        specEntityDao = new SpecEntityDao(specEntityDaoConfig, this);

        registerDao(FoodEntity.class, foodEntityDao);
        registerDao(FoodTypeEntity.class, foodTypeEntityDao);
        registerDao(KouWeiEntity.class, kouWeiEntityDao);
        registerDao(ProductKouWeiEntity.class, productKouWeiEntityDao);
        registerDao(SeasonEntity.class, seasonEntityDao);
        registerDao(SpecEntity.class, specEntityDao);
    }
    
    public void clear() {
        foodEntityDaoConfig.clearIdentityScope();
        foodTypeEntityDaoConfig.clearIdentityScope();
        kouWeiEntityDaoConfig.clearIdentityScope();
        productKouWeiEntityDaoConfig.clearIdentityScope();
        seasonEntityDaoConfig.clearIdentityScope();
        specEntityDaoConfig.clearIdentityScope();
    }

    public FoodEntityDao getFoodEntityDao() {
        return foodEntityDao;
    }

    public FoodTypeEntityDao getFoodTypeEntityDao() {
        return foodTypeEntityDao;
    }

    public KouWeiEntityDao getKouWeiEntityDao() {
        return kouWeiEntityDao;
    }

    public ProductKouWeiEntityDao getProductKouWeiEntityDao() {
        return productKouWeiEntityDao;
    }

    public SeasonEntityDao getSeasonEntityDao() {
        return seasonEntityDao;
    }

    public SpecEntityDao getSpecEntityDao() {
        return specEntityDao;
    }

}
