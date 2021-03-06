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


public class EventPayBean {
    private TableEntity tableEntity;
    private List<OrderFoodEntity> mList;
    private String roomName;
    private List<TableEntity> tableEntityList;
    private OrderEntity order;
    private int flag;
    private String id;
    private String name;
    private Double price;

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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
