package com.administrator.shopkeepertablet.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.LoginRepertory;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.activity.LoginActivity;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */


public class LoginViewModel extends BaseViewModel {
    public PreferenceSource preferenceSource;
    private LoginRepertory loginRepertory;
    private LoginActivity loginActivity;
    public ObservableField<String> username = new ObservableField<>("");
    public ObservableField<String> password = new ObservableField<>("");

    public LoginViewModel(LoginRepertory loginRepertory, LoginActivity loginActivity, PreferenceSource preferenceSource) {
        this.loginRepertory = loginRepertory;
        this.loginActivity = loginActivity;
        this.preferenceSource = preferenceSource;
    }

    public void loginViewModel(){
        if (!TextUtils.isEmpty(username.get())&&!TextUtils.isEmpty(password.get())){
            loginRepertory.login(username.get(),"4B176F0E-0553-4094-8181-5048641B20EF",password.get())
                    .subscribe(new Consumer<BaseEntity<String>>() {
                        @Override
                        public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                            MLog.d("api",stringBaseEntity.getCode()+"");
                            MLog.d("api",stringBaseEntity.getResult()+"");
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            MLog.e("api",throwable.getMessage());
                        }
                    });

        }else {
            MToast.showToast(loginActivity,"用户名或密码不能为空");
        }



    }
}
