package com.pizzahot.pizzahot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pizzahot.pizzahot.models.Delivery;
import com.pizzahot.pizzahot.models.PizzaOrder;
import com.pizzahot.pizzahot.services.PizzaService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PizzaController {

    @Autowired
    private PizzaService pizzaSvc;

    @GetMapping(path={"/", "/index.html"})
    public String getHome(Model model,HttpSession sess){
        System.out.println("PizzaController getHome");
        sess.invalidate();
        model.addAttribute("pizzaorder", new PizzaOrder());
        return "index";        
    }

    @PostMapping(path="/pizza")
    public String postPizza(Model model, HttpSession sess, @Valid PizzaOrder pizzaOrder, BindingResult result){
        System.out.println("PizzaController postPizza");
        if(result.hasErrors()){
            // model.addAttribute("pizzaorder",pizzaOrder);
            return "index";
        }
        sess.setAttribute("pizza", pizzaOrder);
        model.addAttribute("delivery", new Delivery());

        return "deliveryform";
        
    }    
}
