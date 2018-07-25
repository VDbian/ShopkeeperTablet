package com.administrator.shopkeepertablet.repository.line;

import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.BaseRepertoryImpl;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */


public class LineUpRepositoryImpl extends BaseRepertoryImpl implements LineUpRepository {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public LineUpRepositoryImpl(ApiSource apiSource, PreferenceSource preferenceSource) {
        super(apiSource, preferenceSource);
        this.apiSource = apiSource;
        this.preferenceSource =preferenceSource;
    }
}
