// PageController.java
package com.yourcompany.garage.garageapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // Welcome Page
    @GetMapping("")
    public String welcomePage() {
        return "welcome";
    }

    @GetMapping("/searchAuto")
    public String searchAutoPage() {
        return "searchAuto";
    }

    @GetMapping("/addCar")
    public String addCarPage() {
        return "addCar";
    }

}