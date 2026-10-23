package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.IPaiementService;

import java.util.List;

@Tag(name = "Paiements", description = "Consultation des paiements (lecture seule)")
@RestController
@RequestMapping("paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiementService service;

    @Operation(summary = "Lister les paiements")
    @GetMapping
    public List<Paiement> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Consulter un paiement")
    @ApiResponse(responseCode = "404", description = "Paiement introuvable")
    @GetMapping("{id}")
    public Paiement findById(@PathVariable Long id) {
        return service.findById(id);
    }
}