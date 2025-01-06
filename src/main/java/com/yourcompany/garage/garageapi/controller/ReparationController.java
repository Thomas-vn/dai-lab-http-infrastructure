package com.yourcompany.garage.garageapi.controller;

import com.yourcompany.garage.garageapi.entity.Reparation;
import com.yourcompany.garage.garageapi.service.ReparationService;
import com.yourcompany.garage.garageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reparations")
public class ReparationController {

    private final ReparationService reparationService;

    @Autowired
    public ReparationController(ReparationService reparationService) {
        this.reparationService = reparationService;
    }

    // ------------------- CRUD Operations -------------------

    /**
     * Get All Reparations
     * GET /api/reparations
     */
    @GetMapping
    public List<Reparation> getAllReparations() {
        return reparationService.getAllReparations();
    }

    /**
     * Get Reparation by ID
     * GET /api/reparations/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Reparation> getReparationById(@PathVariable(value = "id") Integer reparationID)
            throws ResourceNotFoundException {
        Reparation reparation = reparationService.getReparationById(reparationID)
                .orElseThrow(() -> new ResourceNotFoundException("Reparation not found for this id :: " + reparationID));
        return ResponseEntity.ok().body(reparation);
    }

    /**
     * Create a New Reparation
     * POST /api/reparations
     */
    @PostMapping
    public Reparation createReparation(@RequestBody Reparation reparation) {
        return reparationService.createReparation(reparation);
    }

    /**
     * Update an Existing Reparation
     * PUT /api/reparations/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Reparation> updateReparation(@PathVariable(value = "id") Integer reparationID,
                                                       @RequestBody Reparation reparationDetails) throws ResourceNotFoundException {
        Reparation updatedReparation = reparationService.updateReparation(reparationID, reparationDetails);
        return ResponseEntity.ok(updatedReparation);
    }

    /**
     * Delete a Reparation
     * DELETE /api/reparations/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReparation(@PathVariable(value = "id") Integer reparationID) throws ResourceNotFoundException {
        reparationService.deleteReparation(reparationID);
        return ResponseEntity.noContent().build();
    }

    // ------------------- Custom Query Endpoints -------------------

    /**
     * Get Reparations by Car Chassis Number
     * GET /api/reparations/byChassis/{numeroChassis}
     */
    @GetMapping("/byChassis/{numeroChassis}")
    public List<Reparation> getReparationsByNumeroChassis(@PathVariable String numeroChassis) {
        return reparationService.findByNumeroChassis(numeroChassis);
    }

    /**
     * Get Reparations by Repair Location City
     * GET /api/reparations/byVille/{ville}
     */
    @GetMapping("/byVille/{ville}")
    public List<Reparation> getReparationsByVille(@PathVariable String ville) {
        return reparationService.findByLieuVille(ville);
    }

    /**
     * Get Reparations Within a Price Range
     * GET /api/reparations/prixBetween?min=...&max=...
     */
    @GetMapping("/prixBetween")
    public List<Reparation> getReparationsByPrixBetween(@RequestParam Double min, @RequestParam Double max) {
        return reparationService.findByPrixBetween(min, max);
    }

    /**
     * Get Reparations That Started After a Specific Date
     * GET /api/reparations/dateDebutAfter?date=YYYY-MM-DD
     */
    @GetMapping("/dateDebutAfter")
    public List<Reparation> getReparationsByDateDebutAfter(@RequestParam String dateDebut) {
        LocalDate parsedDate = LocalDate.parse(dateDebut);
        return reparationService.findByDateDebutAfter(parsedDate);
    }

    /**
     * Get Reparations That Ended Before a Specific Date
     * GET /api/reparations/dateFinBefore?date=YYYY-MM-DD
     */
    @GetMapping("/dateFinBefore")
    public List<Reparation> getReparationsByDateFinBefore(@RequestParam String dateFin) {
        LocalDate parsedDate = LocalDate.parse(dateFin);
        return reparationService.findByDateFinBefore(parsedDate);
    }

    /**
     * Get Reparations with No End Date (Ongoing Repairs)
     * GET /api/reparations/dateFinIsNull
     */
    @GetMapping("/dateFinIsNull")
    public List<Reparation> getReparationsByDateFinIsNull() {
        return reparationService.findByDateFinIsNull();
    }

    /**
     * Get Reparation by Reparation ID
     * GET /api/reparations/byReparationID/{reparationID}
     */
    @GetMapping("/byReparationID/{reparationID}")
    public ResponseEntity<Reparation> getReparationByReparationID(@PathVariable Integer reparationID)
            throws ResourceNotFoundException {
        Reparation reparation = reparationService.findByReparationID(reparationID)
                .orElseThrow(() -> new ResourceNotFoundException("Reparation not found for this id :: " + reparationID));
        return ResponseEntity.ok().body(reparation);
    }

    /**
     * Get Reparations with Price Greater Than a Specific Value
     * GET /api/reparations/prixGreaterThan?prix=...
     */
    @GetMapping("/prixGreaterThan")
    public List<Reparation> getReparationsByPrixGreaterThan(@RequestParam Double prix) {
        return reparationService.findByPrixGreaterThan(prix);
    }

    /**
     * Get All Reparations Sorted by Price in Descending Order
     * GET /api/reparations/orderByPrixDesc
     */
    @GetMapping("/orderByPrixDesc")
    public List<Reparation> getReparationsOrderByPrixDesc() {
        return reparationService.findAllOrderByPrixDesc();
    }

    /**
     * Get Completed Reparations for a Specific Car Chassis Number
     * GET /api/reparations/completed/byChassis/{numeroChassis}
     */
    @GetMapping("/completed/byChassis/{numeroChassis}")
    public List<Reparation> getCompletedReparationsByNumeroChassis(@PathVariable String numeroChassis) {
        return reparationService.findCompletedReparationsByNumeroChassis(numeroChassis);
    }

    /**
     * Get Reparations with Start Dates Between Two Dates
     * GET /api/reparations/dateDebutBetween?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD
     */
    @GetMapping("/dateDebutBetween")
    public List<Reparation> getReparationsByDateDebutBetween(@RequestParam String startDate,
                                                             @RequestParam String endDate) {
        LocalDate parsedStartDate = LocalDate.parse(startDate);
        LocalDate parsedEndDate = LocalDate.parse(endDate);
        return reparationService.findByDateDebutBetween(parsedStartDate, parsedEndDate);
    }
}