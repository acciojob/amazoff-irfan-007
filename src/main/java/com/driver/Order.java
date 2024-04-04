package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        String[] splitTime=deliveryTime.split(":");
        int hh=Integer.parseInt(splitTime[0]);
        int mm=Integer.parseInt(splitTime[1]);
        this.deliveryTime= mm + (hh*60);
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}

