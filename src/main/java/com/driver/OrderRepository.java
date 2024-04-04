package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private HashMap<String, Order> orderMap;
    private HashMap<String, DeliveryPartner> partnerMap;
    private HashMap<String, HashSet<String>> partnerToOrderMap;
    private HashMap<String, String> orderToPartnerMap;
    private HashSet<String> unassignedOrders;
    public OrderRepository(){
        this.orderMap = new HashMap<String, Order>();
        this.partnerMap = new HashMap<String, DeliveryPartner>();
        this.partnerToOrderMap = new HashMap<String, HashSet<String>>();
        this.orderToPartnerMap = new HashMap<String, String>();
        this.unassignedOrders = new HashSet<String>();
    }

    public void saveOrder(Order order){
        // your code here
        orderMap.put(order.getId(),order);
    }

    public void savePartner(String partnerId){
        // your code here
        // create a new partner with given partnerId and save it
        partnerMap.put(partnerId,new DeliveryPartner(partnerId));
    }

    public void saveOrderPartnerMap(String orderId, String partnerId){
        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
            // your code here
            //add order to given partner's order list
            //increase order count of partner
            //assign partner to this order
            orderToPartnerMap.put(orderId,partnerId);
            HashSet<String> hs=partnerToOrderMap.getOrDefault(partnerId,new HashSet<>());
            hs.add(orderId);
            partnerToOrderMap.put(partnerId,hs);
            DeliveryPartner p=partnerMap.get(partnerId);
            p.setNumberOfOrders(p.getNumberOfOrders()+1);

        }
    }

    public Order findOrderById(String orderId){
        // your code here
        if(orderMap.containsKey(orderId))
            return orderMap.get(orderId);
        return null;
    }

    public DeliveryPartner findPartnerById(String partnerId){
        // your code here
        if(partnerMap.containsKey(partnerId))
            return partnerMap.get(partnerId);
        return null;
    }

    public Integer findOrderCountByPartnerId(String partnerId){
        // your code here
        return partnerMap.get(partnerId).getNumberOfOrders();
    }

    public List<String> findOrdersByPartnerId(String partnerId){
        // your code here
        List<String> ls=new ArrayList<>(partnerToOrderMap.get(partnerId));
        return ls;
    }

    public List<String> findAllOrders(){
        // your code here
        // return list of all orders
        return new ArrayList<>(orderMap.keySet());
    }

    public void deletePartner(String partnerId){
        // your code here
        // delete partner by ID
        partnerMap.remove(partnerId);
        partnerToOrderMap.remove(partnerId);
       for(String order:orderToPartnerMap.keySet()){
           if(orderToPartnerMap.get(order)==partnerId){
               unassignedOrders.add(order);
               orderToPartnerMap.remove(order);
           }
       }
    }

    public void deleteOrder(String orderId){
        // your code here
        // delete order by ID
        orderMap.remove(orderId);
    }

    public Integer findCountOfUnassignedOrders(){
        // your code here
        return unassignedOrders.size();
    }

    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String timeString, String partnerId){
        // your code here
    }

    public String findLastDeliveryTimeByPartnerId(String partnerId){
        // your code here
        // code should return string in format HH:MM
    }
}
