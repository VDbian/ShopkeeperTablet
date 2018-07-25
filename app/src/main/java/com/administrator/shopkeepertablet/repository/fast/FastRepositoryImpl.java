package com.administrator.shopkeepertablet.repository.fast;

import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.BaseRepertoryImpl;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */


public class FastRepositoryImpl extends BaseRepertoryImpl implements FastRepository {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public FastRepositoryImpl(ApiSource apiSource, PreferenceSource preferenceSource) {
        super(apiSource, preferenceSource);
        this.apiSource = apiSource;
        this.preferenceSource =preferenceSource;
    }
}
