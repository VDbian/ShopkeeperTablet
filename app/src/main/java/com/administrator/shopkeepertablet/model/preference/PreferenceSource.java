package com.administrator.shopkeepertablet.model.preference;

import com.cocosw.favor.AllFavor;
import com.cocosw.favor.Default;
import com.cocosw.favor.Favor;

/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
@AllFavor
public interface PreferenceSource {

    @Favor("token")
    @Default("")
    String getToken();

    @Favor("token")
    void setToken(String token);
}
