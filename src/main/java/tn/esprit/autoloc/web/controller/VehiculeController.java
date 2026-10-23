package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@Tag(name = "Véhicules", description = "Gestion des véhicules")
@RestController
@RequestMapping("vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehiculeService service;

    @Operation(summary = "Lister les véhicules")
    @GetMapping
    public List<Vehicule> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Consulter un véhicule")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    @GetMapping("{id}")
    public Vehicule findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Ajouter un véhicule")
    @ApiResponse(responseCode = "201", description = "Véhicule créé")
    @ApiResponse(responseCode = "400", description = "Données invalides ou identifiant déjà rempli")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicule create(@RequestBody Vehicule vehicule) {
        return service.create(vehicule);
    }

    @Operation(summary = "Modifier un véhicule")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    @PutMapping("{id}")
    public Vehicule update(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return service.update(id, vehicule);
    }

    @Operation(summary = "Supprimer un véhicule")
    @ApiResponse(responseCode = "204", description = "Véhicule supprimé")
    @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}