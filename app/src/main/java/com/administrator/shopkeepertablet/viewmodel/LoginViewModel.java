package com.administrator.shopkeepertablet.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;

import com.administrator.shopkeepertablet.AppApplication;
import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.di.Activity;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.UserInfoEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.login.LoginRepertory;
import com.administrator.shopkeepertablet.utils.DialogUtils;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.activity.LoginActivity;
import com.google.gson.Gson;

import io.reactivex.functions.Consumer;
import retrofit2.http.Field;

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

    public void loginViewModel() {
        if (!TextUtils.isEmpty(username.get()) && !TextUtils.isEmpty(password.get())) {
            if (TextUtils.isEmpty(preferenceSource.getId())){
                MToast.showToast(loginActivity,"请先设置店铺ID");
                return;
            }
            DialogUtils.showDialog(loginActivity, "登录中");
//            loginRepertory.login("收银", preferenceSource.getId(), "111")
            loginRepertory.login(username.get(), preferenceSource.getId(), password.get())
                    .subscribe(new Consumer<BaseEntity<String>>() {
                        @Override
                        public void accept(BaseEntity<String> baseEntity) throws Exception {
                            MLog.d("api", baseEntity.toString());
                            DialogUtils.hintDialog();
                            if (baseEntity.getCode() == AppConstant.REQUEST_SUCCESS) {
                                UserInfoEntity entity = new Gson().fromJson(baseEntity.getResult(), UserInfoEntity.class);
                                MLog.d("api", entity.toString());
//                                preferenceSource.setId("4B176F0E-0553-4094-8181-5048641B20EF");
                                preferenceSource.setName(entity.getUserName());
                                preferenceSource.setUserId(entity.getUserId());
                                AppConstant.setUser(entity);
                                loginActivity.intentToMain();
                            } else {
                                MToast.showToast(loginActivity, "登录失败");
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            MLog.e("api", throwable.getMessage());
                            DialogUtils.hintDialog();
                            MToast.showToast(loginActivity,"登录失败");
                        }
                    });

        } else {
            MToast.showToast(loginActivity, "用户名或密码不能为空");
        }
    }

}
