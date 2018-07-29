package com.administrator.shopkeepertablet.model.entity.bean;

import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.view.widget.PermissionRemissionDialog;

import java.util.List;

/**
 * Description:
 * Author CC
 * Time 2018/7/28
 */


public class EventReturnBean {
    private OrderEntity orderEntity;
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
}
