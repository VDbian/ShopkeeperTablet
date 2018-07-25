package com.administrator.shopkeepertablet.repository.message;

import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.BaseRepertoryImpl;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */


public class MessageRepositoryImpl extends BaseRepertoryImpl implements MessageRepository {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public MessageRepositoryImpl(ApiSource apiSource, PreferenceSource preferenceSource) {
        super(apiSource, preferenceSource);
        this.apiSource = apiSource;
        this.preferenceSource =preferenceSource;
    }
}
