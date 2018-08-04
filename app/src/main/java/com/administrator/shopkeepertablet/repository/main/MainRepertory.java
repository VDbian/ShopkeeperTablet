package com.administrator.shopkeepertablet.repository.main;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.repository.BaseRepertory;

import io.reactivex.Observable;

/**
 * Description:
 * Author CC
 * Time 2018/7/1
 */


public interface MainRepertory extends BaseRepertory {

    /**
     * 交班打印
     * @param s 6
     * @param s1 是否本地打印 1是 2否
     * @param shopID 店铺Id
     * @param name 操作员
     * @param s2 金额
     * @param id 操作员Id
     * @return
     */
    Observable<BaseEntity<String>> jiaoBanPrint(String s, String s1, String shopID, String name, String s2, String id);

}
