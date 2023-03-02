package com.pizzahot.pizzahot.models;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
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

    private double pizzaCost=0.0;
    
    public PizzaOrder() {}
    
    public double getCost() {return pizzaCost;}
    public void setCost(double pizzaCost) {this.pizzaCost = pizzaCost;}

    public String getPizza() {return pizza;}
    public void setPizza(String pizza) {this.pizza = pizza;}
    
    public String getSize() {return size;}
    public void setSize(String size) {this.size = size;}
    
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}

    public void calculateCost(){
        
        this.pizzaCost = switch(this.pizza){
            case "bella","marinara","spianata"->30.0;
            case "margherita" ->22.0;
            case "trio"->25.0;
            default -> 0.0;
        };
        this.pizzaCost *= switch(this.pizza){
            case "small"->1;
            case "medium" ->1.2;
            case "large"->1.5;
            default -> 1;
        };

        this.pizzaCost*=this.quantity;
    }
    
    @Override
    public String toString() {

        JsonObject json = Json.createObjectBuilder()
            .add("pizza", pizza)
            .add("size", size)
            .add("quantity", quantity)
            .build();
        return json.toString();
    }

    

  

    
}
