package com.administrator.shopkeepertablet.di.fast;

import com.administrator.shopkeepertablet.di.Fragment;
import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.fast.FastRepository;
import com.administrator.shopkeepertablet.repository.fast.FastRepositoryImpl;
import com.administrator.shopkeepertablet.view.ui.fragment.FastFoodFragment;
import com.administrator.shopkeepertablet.viewmodel.FastViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */

@Module
public class FastModule {
    private FastFoodFragment fragment;

    public FastModule(FastFoodFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Fragment
    FastRepository provideFastRepository(ApiSource apiSource, PreferenceSource preferenceSource) {
        return new FastRepositoryImpl(apiSource, preferenceSource);
    }

    @Provides
    @Fragment
    FastViewModel provideFastViewModel(FastRepository fastRepository, PreferenceSource preferenceSource) {
        return new FastViewModel(fragment, preferenceSource,fastRepository);
    }
}
