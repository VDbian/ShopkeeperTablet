package com.administrator.shopkeepertablet.viewmodel;

import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.recharge.RechargeRepository;
import com.administrator.shopkeepertablet.view.ui.fragment.RechargeFragment;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/25
 */

public class RechargeViewModel extends BaseViewModel {

    private RechargeFragment fragment;
    private RechargeRepository repository;
    private PreferenceSource preferenceSource;

    public RechargeViewModel(RechargeFragment fragment, RechargeRepository repository, PreferenceSource preferenceSource) {
        this.fragment = fragment;
        this.repository = repository;
        this.preferenceSource = preferenceSource;
    }
}
