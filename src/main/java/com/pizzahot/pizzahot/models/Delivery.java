package com.pizzahot.pizzahot.models;

import java.util.UUID;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Delivery {
        //! From order page
        @Size(min=3, message = "name must be at least 3 characters long")
        private String name;
        
        
        @NotNull(message="Address cannot be blank")
        private String address;
    
        @Digits(message="need 8 digits", fraction = 0, integer = 8)
        private int phoneNo;
    
        private boolean rush =false;
    
        private String comments="nil";
        
        //! generated;
        private String orderID;
        private PizzaOrder pizzaOrder;
        private double totalCost;
        
    
        public double getTotalCost() {return totalCost;}
        public void setTotalCost(double totalCost) {this.totalCost = totalCost;}

        public PizzaOrder getPizzaOrder() {return pizzaOrder;}
        public void setPizzaOrder(PizzaOrder pizzaOrder) {this.pizzaOrder = pizzaOrder;}

        public String getName() {return name;}
        public void setName(String name) {this.name = name;}

        public String getAddress() {return address;}
        public void setAddress(String address) {this.address = address;}

        public int getPhoneNo() {return phoneNo;}
        public void setPhoneNo(int phoneNo) {this.phoneNo = phoneNo;}

        public boolean isRush() {return rush;}
        public void setRush(boolean rush) {this.rush = rush;}

        public String getComments() {return comments;}
        public void setComments(String comments) {this.comments = comments;}

        public String getOrderID() {return orderID;}
        public void setOrderID(String orderID) {this.orderID = orderID;}

        public void calculateTotalCost(){
                if(rush){
                        this.totalCost=this.pizzaOrder.getCost()+2;
                }
        }
        public void createOrderID(){
                this.orderID = UUID.randomUUID().toString().substring(0,8);        
        }
        @Override
        public String toString() {

                JsonObject json = Json.createObjectBuilder()
                        .add("orderID", orderID)
                        .add("name", name)
                        .add("address", address)
                        .add("address", address)
                        .add("phoneNo", phoneNo)
                        .add("rush", rush)
                        .add("comments", comments)
                        .add("pizza", pizzaOrder.getPizza())
                        .add("size", pizzaOrder.getSize())
                        .add("quantity", pizzaOrder.getQuantity())
                        .add("pizzaCost", pizzaOrder.getCost())
                        .add("total cost",totalCost)
                        .build();

                return json.toString();
        }

        


        

}
