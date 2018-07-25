package com.administrator.shopkeepertablet.di.message;

import com.administrator.shopkeepertablet.di.Fragment;
import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.message.MessageRepository;
import com.administrator.shopkeepertablet.repository.message.MessageRepositoryImpl;
import com.administrator.shopkeepertablet.view.ui.fragment.MessageFragment;
import com.administrator.shopkeepertablet.viewmodel.MessageViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */

@Module
public class MessageModule {
    private MessageFragment fragment;

    public MessageModule(MessageFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Fragment
    MessageRepository provideMessageRepository(ApiSource apiSource, PreferenceSource preferenceSource) {
        return new MessageRepositoryImpl(apiSource, preferenceSource);
    }

    @Provides
    @Fragment
    MessageViewModel provideMessageViewModel(MessageRepository messageRepository, PreferenceSource preferenceSource) {
        return new MessageViewModel(fragment,messageRepository,preferenceSource);
    }
}
