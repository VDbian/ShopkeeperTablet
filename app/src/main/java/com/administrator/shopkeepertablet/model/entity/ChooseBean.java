package com.administrator.shopkeepertablet.model.entity;


import com.administrator.shopkeepertablet.R;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/12
 */

public class ChooseBean {
    private boolean choose;
    private String content;

    public ChooseBean(boolean choose, String content) {
        this.choose = choose;
        this.content = content;
    }

    public boolean isChoose() {
        return choose;
    }

    public void setChoose(boolean choose) {
        this.choose = choose;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int textColor() {
        return choose ? R.color.colorffb001 : R.color.color666666;
    }

    public int backgroundDrawable() {
        return choose ? R.drawable.shape_choose_select : R.drawable.shape_choose_normal;
    }
}
