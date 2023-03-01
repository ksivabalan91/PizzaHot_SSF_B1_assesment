package com.pizzahot.pizzahot.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizzahot.pizzahot.models.PizzaOrder;

@Repository
public class PizzaRepo {

    private List<PizzaOrder> pizzaList = new LinkedList<>();

    public void addPizza(PizzaOrder pizza){
        System.out.println("PizzaRepo addPizza");
        pizzaList.add(pizza);
    }
    
}
