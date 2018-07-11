package com.administrator.shopkeepertablet.model.entity;

/**
 * Description:
 * Author CC
 * Time 2018/7/11
 */


public class FoodTypeSelectEntity {
    private boolean select;
    private FoodTypeEntity foodTypeEntity;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public FoodTypeEntity getFoodTypeEntity() {
        return foodTypeEntity;
    }

    public void setFoodTypeEntity(FoodTypeEntity foodTypeEntity) {
        this.foodTypeEntity = foodTypeEntity;
    }
}
