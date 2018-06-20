package com.administrator.shopkeepertablet.view.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.model.api.ApiSourceImpl;
import com.administrator.shopkeepertablet.repository.BaseRepertory;
import com.administrator.shopkeepertablet.repository.impl.BaseRepertoryImpl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
