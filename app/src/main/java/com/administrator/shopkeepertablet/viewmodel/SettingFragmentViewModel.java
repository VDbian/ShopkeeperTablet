package com.administrator.shopkeepertablet.viewmodel;

import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.setting.SettingRepertory;
import com.administrator.shopkeepertablet.view.ui.fragment.SettingFragment;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/25
 */

public class SettingFragmentViewModel extends BaseViewModel {

    private SettingFragment fragment;
    private PreferenceSource preferenceSource;
    private SettingRepertory repertory;

    public SettingFragmentViewModel(SettingFragment fragment, PreferenceSource preferenceSource, SettingRepertory repertory) {
        this.fragment = fragment;
        this.preferenceSource = preferenceSource;
        this.repertory = repertory;
    }
}
