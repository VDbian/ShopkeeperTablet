package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/7/1
 */


public class ResultFoodEntity {

    /**
     * code : 1
     * result : {"food":""}
     */

    private String code;
    private ResultBean result;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ResultBean {
        /**
         * food :
         */

        private String food;
        @SerializedName("Spec")
        private String spec;
        @SerializedName("Kouwei")
        private String kouWei;
        private String foodType;
        @SerializedName("Season")
        private String season;
        @SerializedName("ProductKouWei")
        private String productKouWei;

        public String getFood() {
            return food;
        }

        public void setFood(String food) {
            this.food = food;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getKouWei() {
            return kouWei;
        }

        public void setKouWei(String kouWei) {
            this.kouWei = kouWei;
        }

        public String getFoodType() {
            return foodType;
        }

        public void setFoodType(String foodType) {
            this.foodType = foodType;
        }

        public String getSeason() {
            return season;
        }

        public void setSeason(String season) {
            this.season = season;
        }

        public String getProductKouWei() {
            return productKouWei;
        }

        public void setProductKouWei(String productKouWei) {
            this.productKouWei = productKouWei;
        }
    }
}
