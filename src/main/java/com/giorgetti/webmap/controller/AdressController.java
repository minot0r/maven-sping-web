package com.giorgetti.webmap.controller;

import com.giorgetti.webmap.model.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdressController {

    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/addresses")
    public String showAddresses(Model model) {
        model.addAttribute("allAddresses", addressRepository.findAll());
        return "addresses";
    }

}
