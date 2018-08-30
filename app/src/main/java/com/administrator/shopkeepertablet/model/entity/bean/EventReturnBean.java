package com.administrator.shopkeepertablet.model.entity.bean;

import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.view.widget.PermissionRemissionDialog;

import java.util.List;

/**
 * Description:
 * Author CC
 * Time 2018/7/28
 */


public class EventReturnBean {
    private OrderEntity orderEntity;
    private TableEntity tableEntity;
    private List<OrderFoodEntity> orderFoodEntities;

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public List<OrderFoodEntity> getOrderFoodEntities() {
        return orderFoodEntities;
    }

    public void setOrderFoodEntities(List<OrderFoodEntity> orderFoodEntities) {
        this.orderFoodEntities = orderFoodEntities;
    }

    public TableEntity getTableEntity() {
        return tableEntity;
    }

    public void setTableEntity(TableEntity tableEntity) {
        this.tableEntity = tableEntity;
    }
}
