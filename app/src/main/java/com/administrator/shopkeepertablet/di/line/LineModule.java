package com.administrator.shopkeepertablet.di.line;

import com.administrator.shopkeepertablet.di.Fragment;
import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.line.LineUpRepository;
import com.administrator.shopkeepertablet.repository.line.LineUpRepositoryImpl;
import com.administrator.shopkeepertablet.view.ui.fragment.LineUpFragment;
import com.administrator.shopkeepertablet.viewmodel.LineUpViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */

@Module
public class LineModule {
    private LineUpFragment fragment;

    public LineModule(LineUpFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Fragment
    LineUpRepository provideLineUpRepository(ApiSource apiSource, PreferenceSource preferenceSource) {
        return new LineUpRepositoryImpl(apiSource, preferenceSource);
    }

    @Provides
    @Fragment
    LineUpViewModel provideLineUpViewModel(LineUpRepository lineUpRepository, PreferenceSource preferenceSource) {
        return new LineUpViewModel(fragment,lineUpRepository, preferenceSource);
    }
}
