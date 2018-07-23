package com.administrator.shopkeepertablet.model.entity.bean;

import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;

import java.util.List;

/**
 * Description:
 * Author CC
 * Time 2018/7/22
 */


public class EventPayBean {
    private TableEntity tableEntity;
    private List<OrderFoodEntity> mList;
    private String roomName;
    private List<TableEntity> tableEntityList;

    public TableEntity getTableEntity() {
        return tableEntity;
    }

    public void setTableEntity(TableEntity tableEntity) {
        this.tableEntity = tableEntity;
    }

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

    public List<TableEntity> getTableEntityList() {
        return tableEntityList;
    }

    public void setTableEntityList(List<TableEntity> tableEntityList) {
        this.tableEntityList = tableEntityList;
    }
}
