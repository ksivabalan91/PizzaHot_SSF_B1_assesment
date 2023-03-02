package com.pizzahot.pizzahot.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.pizzahot.pizzahot.models.Delivery;
import com.pizzahot.pizzahot.models.PizzaOrder;

@Repository
public class PizzaRepo {


    @Autowired
    private RedisTemplate<String,String> template;

    //todo different method
    private Map<String,PizzaOrder> pizzaMap = new HashMap<>();
    
    
    public void savePizza(PizzaOrder pizza){
        System.out.println("PizzaRepo addPizza");
        pizzaMap.put("temp", pizza);
    }
    
    public boolean saveOrder(Delivery delivery){
        System.out.println("PizzaRepo saveOrder");

        template.opsForValue().set(delivery.getOrderID(),delivery.toString());

        return true;
    }

    public Map<String,String> getOrderByID(String orderID){
        
        Map<String,String> respMap = new HashMap<>();

            respMap.put("exists", template.hasKey(orderID).toString());
            respMap.put("payload", template.opsForValue().get(orderID));       
        
    return respMap;

    }
    
}
