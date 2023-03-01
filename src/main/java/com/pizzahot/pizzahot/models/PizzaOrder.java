package com.pizzahot.pizzahot.models;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PizzaOrder implements Serializable{
    
    //! From landing page
    @NotNull(message="Please select a pizza")
    private String pizza;    
    
    @NotNull(message="Please select a size")
    private String size;
    
    @Min(value=1, message="Must order at least 1 pizza")
    @Max(value=10, message="You can only order a maximum of 10 pizzas")
    private int quantity;
    
    public PizzaOrder() {}
    
    public String getPizza() {return pizza;}
    public void setPizza(String pizza) {this.pizza = pizza;}
    
    public String getSize() {return size;}
    public void setSize(String size) {this.size = size;}

    public Integer getQuantity() {return quantity;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}

    @Override
    public String toString() {
        return "Pizza{pizza=%s, size=%s, quantity=%d}".formatted(pizza, size, quantity);
    }

    

  

    
}
