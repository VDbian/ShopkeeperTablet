package com.administrator.shopkeepertablet.model.entity.bean;

import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;

import java.util.List;

/**
 * Description:
 * Author CC
 * Time 2018/7/22
 */


public class EventFastPayBean {
    private List<OrderFoodEntity> mList;
    private String roomName;
    private String order;
    private int flag;
    private String tableId;


    public List<OrderFoodEntity> getmList() {
        return mList;
    }

    public void setmList(List<OrderFoodEntity> mList) {
        this.mList = mList;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }


    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
