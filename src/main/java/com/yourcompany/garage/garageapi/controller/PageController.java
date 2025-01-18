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

    /**
     * Database Status Page
     * GET /api/databaseStatus
     */
    @GetMapping("/databaseStatus")
    public String databaseStatusPage() {
        return "databaseStatus";
    }

    /**
     * Search Auto Page
     * GET /api/searchAuto
     */
    @GetMapping("/searchAuto")
    public String searchAutoPage() {
        return "searchAuto";
    }

    /**
     * Add Car Page
     * GET /api/addCar
     */
    @GetMapping("/addCar")
    public String addCarPage() {
        return "addCar";
    }

    /**
     * Mechanics List Page
     * GET /api/mechanics
     */
    @GetMapping("/mechanics")
    public String mechanicsPage() {
        return "mechanics";
    }
}