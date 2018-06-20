package com.administrator.shopkeepertablet.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.administrator.shopkeepertablet.AppApplication;
import com.administrator.shopkeepertablet.di.app.AppComponent;


/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
public abstract class BaseFragment extends Fragment{
    protected abstract void setupFragmentComponent(AppComponent appComponent);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFragmentComponent(AppApplication.get(getActivity()).getAppComponent());
    }
}
