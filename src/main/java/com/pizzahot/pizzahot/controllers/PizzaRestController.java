package com.pizzahot.pizzahot.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzahot.pizzahot.repositories.PizzaRepo;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping("/order")
public class PizzaRestController {
    
    @Autowired
    private PizzaRepo pizzaRepo;
    @GetMapping(path="/{orderID}" ,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOrder(@PathVariable String orderID){
        
        Map<String,String> json = pizzaRepo.getOrderByID(orderID);

        if(Boolean.parseBoolean(json.get("exists"))){
            return ResponseEntity.status(201).body(json.get("payload"));
        }

        JsonObject jsonErr = Json.createObjectBuilder()
            .add("message", "Order id %s not found".formatted(orderID))
            .build();

        return ResponseEntity.status(404).body(jsonErr.toString());

    }
}
