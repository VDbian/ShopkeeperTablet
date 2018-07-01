package com.administrator.shopkeepertablet.model.entity;

import java.util.List;

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

    public static class ResultBean {
        /**
         * food :
         */

        private String food;

        public String getFood() {
            return food;
        }

        public void setFood(String food) {
            this.food = food;
        }
    }
}
