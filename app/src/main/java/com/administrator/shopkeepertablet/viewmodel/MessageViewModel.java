package com.administrator.shopkeepertablet.viewmodel;

import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.message.MessageRepository;
import com.administrator.shopkeepertablet.view.ui.fragment.MessageFragment;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/25
 */

public class MessageViewModel extends BaseViewModel {

    private MessageFragment fragment;
    private MessageRepository repository;
    private PreferenceSource preferenceSource;

    public MessageViewModel(MessageFragment fragment, MessageRepository repository, PreferenceSource preferenceSource) {
        this.fragment = fragment;
        this.repository = repository;
        this.preferenceSource = preferenceSource;
    }
}
