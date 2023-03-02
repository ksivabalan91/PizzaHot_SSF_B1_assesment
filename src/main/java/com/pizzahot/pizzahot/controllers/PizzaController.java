package com.pizzahot.pizzahot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pizzahot.pizzahot.models.Delivery;
import com.pizzahot.pizzahot.models.PizzaOrder;
import com.pizzahot.pizzahot.services.PizzaService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PizzaController {

    @Autowired
    private PizzaService pizzaSvc;

    @GetMapping(value={"/", "/index.html","/index"})
    public String getHome(Model model){
        System.out.println("PizzaController getHome");
        model.addAttribute("pizzaorder", new PizzaOrder());
        return "index";        
    }

    @PostMapping(path="/pizza")
    // public String postPizza(@Valid PizzaOrder pizzaOrder, BindingResult result,Model model, MultiValueMap<String,String> form){
    public String postPizza(@RequestBody MultiValueMap<String,String> form, Model model){
        System.out.println("PizzaController postPizza");        
        // model.addAttribute("pizzaorder", pizzaOrder);
        // if(result.hasErrors()){
        //     return "index";
        // }
        
        String orderList = form.getFirst("pizza")+","+form.getFirst("size")+","+form.getFirst("quantity");
        
        model.addAttribute("orderList", orderList);
        model.addAttribute("delivery", new Delivery());
        return "deliveryform";       
    }
    
    @PostMapping(path="/pizza/order")
    public String postOrder(@RequestBody MultiValueMap<String,String> form){        
        
        //! read form data
        String name = form.getFirst("name");
        String address = form.getFirst("address");
        int phoneNo = Integer.parseInt(form.getFirst("phoneNo"));
        String comments = form.getFirst("comments");
        boolean rush = false;
        //! check rush input
        if(Boolean.parseBoolean(form.getFirst("rush"))){rush = true;}           
        
        //! create pizzaOrder object
        String orderList[] = form.getFirst("orderList").split(",");
        String pizza = orderList[0];
        String size = orderList[1];
        int quantity = Integer.parseInt(orderList[2]);       
        
        PizzaOrder pizzaOrder=pizzaSvc.createPizza(pizza,size,quantity);

        //! Create delivery object
        Delivery delivery = pizzaSvc.createOrder(name, address, phoneNo, rush, comments, pizzaOrder);
        //! save order to redis
        pizzaSvc.saveOrder(delivery);
        
        return "summary";
    }

}
