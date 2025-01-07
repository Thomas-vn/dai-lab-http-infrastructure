package com.yourcompany.garage.garageapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class ApiController {

    /**
     * Health Check Endpoint
     * GET /api/health
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "Garage API is up and running!";
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
     * API Information Endpoint
     * GET /api/info
     */
    @GetMapping("/info")
    public ApiInfo getApiInfo() {
        return new ApiInfo(
                "Garage Management API",
                "API for managing garage reparations, vehicles, and locations.",
                "1.0",
                "Terms of service",
                "contact@yourcompany.com",
                "License of API",
                "API license URL"
        );
    }

    // Inner class to represent API information
    static class ApiInfo {
        private String title;
        private String description;
        private String version;
        private String termsOfService;
        private String contact;
        private String license;
        private String licenseUrl;

        public ApiInfo(String title, String description, String version, String termsOfService, String contact, String license, String licenseUrl) {
            this.title = title;
            this.description = description;
            this.version = version;
            this.termsOfService = termsOfService;
            this.contact = contact;
            this.license = license;
            this.licenseUrl = licenseUrl;
        }

        // Getters and Setters

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTermsOfService() {
            return termsOfService;
        }

        public void setTermsOfService(String termsOfService) {
            this.termsOfService = termsOfService;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getLicenseUrl() {
            return licenseUrl;
        }

        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }
    }
}