package com.pizzahot.pizzahot.models;

import java.util.UUID;

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
    
        private String comments;
    
        //! generated;
        private UUID orderID;
    
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

        public UUID getOrderID() {return orderID;}
        public void setOrderID(UUID orderID) {this.orderID = orderID;}

}
