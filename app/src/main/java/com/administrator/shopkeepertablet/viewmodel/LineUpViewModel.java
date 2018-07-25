package com.administrator.shopkeepertablet.viewmodel;

import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.line.LineUpRepository;
import com.administrator.shopkeepertablet.view.ui.fragment.LineUpFragment;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/25
 */

public class LineUpViewModel extends BaseViewModel{

    private LineUpFragment fragment;
    private LineUpRepository repository;
    private PreferenceSource preferenceSource;

    public LineUpViewModel(LineUpFragment fragment, LineUpRepository repository, PreferenceSource preferenceSource) {
        this.fragment = fragment;
        this.repository = repository;
        this.preferenceSource = preferenceSource;
    }
}
