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
     * Clients List Page
     * GET /api/clients
     */
    @GetMapping("/clients")
    public String clientsPage() {
        return "clients";
    }

    /**
     * Cars for Sale Page
     * GET /api/cars-for-sale
     */
    @GetMapping("/cars-for-sale")
    public String carsForSalePage() {
        return "carsForSale";
    }

    /**
     * Mechanics List Page
     * GET /api/mechanics
     */
    @GetMapping("/mechanics")
    public String mechanicsPage() {
        return "mechanics";
    }

    /**
     * Services List Page
     * GET /api/services
     */
    @GetMapping("/service")
    public String servicePage() {
        return "service";
    }

    /**
     * Reparations List Page
     * GET /api/reparations
     */
    @GetMapping("/reparation")
    public String reparationPage() {
        return "reparation";
    }

    /**
     * Types of Services List Page
     * GET /api/type-services
     */
    @GetMapping("/type-service")
    public String typeServicePage() {
        return "typeService";
    }
}