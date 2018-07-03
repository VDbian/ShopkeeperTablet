package com.administrator.shopkeepertablet.di.parish;


import com.administrator.shopkeepertablet.di.Activity;
import com.administrator.shopkeepertablet.di.Fragment;
import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.login.LoginRepertory;
import com.administrator.shopkeepertablet.repository.login.LoginRepertoryImpl;
import com.administrator.shopkeepertablet.repository.parish.ParishRepertory;
import com.administrator.shopkeepertablet.repository.parish.ParishRepertoryImpl;
import com.administrator.shopkeepertablet.view.ui.activity.LoginActivity;
import com.administrator.shopkeepertablet.view.ui.fragment.ParishFoodFragment;
import com.administrator.shopkeepertablet.viewmodel.LoginViewModel;
import com.administrator.shopkeepertablet.viewmodel.parish.ParishFoodViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
@Module
public class ParishFragmentModule {
    private ParishFoodFragment parishFoodFragment;


    public ParishFragmentModule(ParishFoodFragment parishFoodFragment) {
        this.parishFoodFragment = parishFoodFragment;
    }

    @Provides
    @Fragment
    ParishRepertory provideParishRepertory(ApiSource apiSource, PreferenceSource preferenceSource) {
        return new ParishRepertoryImpl(apiSource, preferenceSource);
    }

    @Provides
    @Fragment
    ParishFoodViewModel provideParishFoodViewModel(ParishRepertory parishRepertory, PreferenceSource preferenceSource) {
        return new ParishFoodViewModel(parishFoodFragment, parishRepertory, preferenceSource);
    }

}
