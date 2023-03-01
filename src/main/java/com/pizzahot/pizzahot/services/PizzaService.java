package com.pizzahot.pizzahot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        pizzaRepo.addPizza(pizzaOrder);
     return pizzaOrder;   
    }

    
}
