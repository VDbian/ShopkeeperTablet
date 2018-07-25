package com.administrator.shopkeepertablet.repository.main;

import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.BaseRepertoryImpl;

/**
 * Description:
 * Author CC
 * Time 2018/7/1
 */


public class MainRepertoryImpl extends BaseRepertoryImpl implements MainRepertory {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public MainRepertoryImpl(ApiSource apiSource, PreferenceSource preferenceSource) {
        super(apiSource, preferenceSource);
        this.apiSource = apiSource;
        this.preferenceSource = preferenceSource;
    }


}
