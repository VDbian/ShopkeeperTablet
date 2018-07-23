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

    @Favor("name")
    @Default("")
    String getName();

    @Favor("name")
    void setName(String name);

    @Favor("id")
    @Default("")
    String getId();

    @Favor("id")
    void setId(String id);

    @Favor("userId")
    @Default("")
    String getUserId();

    @Favor("userId")
    void setUserId(String userId);


    @Favor("permissionName")
    @Default("")
    String getPermissionName();

    @Favor("permissionName")
    void setPermissionName(String permissionName);

}
