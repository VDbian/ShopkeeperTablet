package com.administrator.shopkeepertablet.utils;

/**
 * Description:
 * Author CC
 * Time 2018/7/7
 */
public class DataEvent {
    int messageTag;
    Object messageData;

    public static DataEvent make() {
        return new DataEvent();
    }

    public static DataEvent make(int tag, Object object) {
        DataEvent message = new DataEvent();
        return message.setMessageData(object).setMessageTag(tag);
    }

    public int getMessageTag() {
        return messageTag;
    }

    public DataEvent setMessageTag(int messageTag) {
        this.messageTag = messageTag;
        return this;
    }

    public Object getMessageData() {
        return messageData;
    }

    public DataEvent setMessageData(Object messageData) {
        this.messageData = messageData;
        return this;
    }
}
