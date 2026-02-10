package com.rabbit.SpringRabbit;

public class OrderCreateEvent {

    private String orderId;

    public OrderCreateEvent() {
    }

    public OrderCreateEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
