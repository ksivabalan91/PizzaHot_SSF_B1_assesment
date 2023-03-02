package com.pizzahot.pizzahot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzahot.pizzahot.models.Delivery;
import com.pizzahot.pizzahot.models.PizzaOrder;
import com.pizzahot.pizzahot.repositories.PizzaRepo;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepo pizzaRepo;
    
    public PizzaOrder createPizza(String pizza, String size, int quantity){
        System.out.println("PizzaService createPizza");
        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setPizza(pizza);
        pizzaOrder.setSize(size);
        pizzaOrder.setQuantity(quantity);      
        pizzaOrder.calculateCost();  
        return pizzaOrder;
    }
    public Delivery createOrder(String name, String address, int phoneNo, boolean rush, String comments, PizzaOrder pizzaOrder){
        System.out.println("PizzaService createOrder");
        Delivery delivery = new Delivery();
        delivery.setName(name);
        delivery.setAddress(address);
        delivery.setPhoneNo(phoneNo);
        if(rush){delivery.setRush(true);}
        delivery.setPizzaOrder(pizzaOrder);
        delivery.createOrderID();
        delivery.calculateTotalCost();


        return delivery;
    }

    public boolean saveOrder(Delivery delivery){
        return pizzaRepo.saveOrder(delivery);
    }

    
}
