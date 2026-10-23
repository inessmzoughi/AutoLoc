package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@Tag(name = "Équipements", description = "Gestion des équipements")
@RestController
@RequestMapping("equipements")
@RequiredArgsConstructor
public class EquipementController {

    private final IEquipementService service;

    @Operation(summary = "Lister les équipements")
    @GetMapping
    public List<Equipement> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Consulter un équipement")
    @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    @GetMapping("{id}")
    public Equipement findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Ajouter un équipement")
    @ApiResponse(responseCode = "201", description = "Équipement créé")
    @ApiResponse(responseCode = "400", description = "Données invalides ou identifiant déjà rempli")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Equipement create(@RequestBody Equipement equipement) {
        return service.create(equipement);
    }

    @Operation(summary = "Modifier un équipement")
    @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    @PutMapping("{id}")
    public Equipement update(@PathVariable Long id, @RequestBody Equipement equipement) {
        return service.update(id, equipement);
    }

    @Operation(summary = "Supprimer un équipement")
    @ApiResponse(responseCode = "204", description = "Équipement supprimé")
    @ApiResponse(responseCode = "404", description = "Équipement introuvable")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}