package com.administrator.shopkeepertablet.repository.impl;

import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.BaseRepertory;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */


public class BaseRepertoryImpl implements BaseRepertory {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public BaseRepertoryImpl(ApiSource apiSource, PreferenceSource preferenceSource) {
        this.apiSource = apiSource;
        this.preferenceSource = preferenceSource;
    }
}
