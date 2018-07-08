package com.administrator.shopkeepertablet.viewmodel;

import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.main.MainRepertory;
import com.administrator.shopkeepertablet.view.ui.activity.MainActivity;

/**
 * Description:
 * Author CC
 * Time 2018/6/23
 */


public class MainViewModel extends BaseViewModel {

    private MainActivity mainActivity;
    private MainRepertory mainRepertory;
    private PreferenceSource preferenceSource;

    public MainViewModel(MainActivity mainActivity, MainRepertory mainRepertory, PreferenceSource preferenceSource) {
        this.mainActivity = mainActivity;
        this.mainRepertory = mainRepertory;
        this.preferenceSource = preferenceSource;
    }

    public String getUserName(){
        return preferenceSource.getName();
    }
}
