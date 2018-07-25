package com.administrator.shopkeepertablet.di.recharge;

import com.administrator.shopkeepertablet.di.Fragment;
import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.recharge.RechargeRepository;
import com.administrator.shopkeepertablet.repository.recharge.RechargeRepositoryImpl;
import com.administrator.shopkeepertablet.view.ui.fragment.RechargeFragment;
import com.administrator.shopkeepertablet.viewmodel.RechargeViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */

@Module
public class RechargeModule {
    private RechargeFragment fragment;

    public RechargeModule(RechargeFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Fragment
    RechargeRepository provideRechargeRepository(ApiSource apiSource, PreferenceSource preferenceSource) {
        return new RechargeRepositoryImpl(apiSource, preferenceSource);
    }

    @Provides
    @Fragment
    RechargeViewModel provideRechargeViewModel(RechargeRepository rechargeRepository, PreferenceSource preferenceSource) {
        return new RechargeViewModel(fragment,rechargeRepository,preferenceSource);
    }
}
