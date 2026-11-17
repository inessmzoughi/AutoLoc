package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@Tag(name = "Agences", description = "Gestion des agences")
@RestController
@RequestMapping("agences")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;

    @Operation(summary = "Lister toutes les agences")
    @GetMapping
    public List<Agence> findAll() {
        return agenceService.findAll();
    }

    @Operation(summary = "Consulter une agence par son identifiant")
    @ApiResponse(responseCode = "200", description = "Agence trouvée")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @GetMapping("{id}")
    public Agence findById(@PathVariable Long id) {
        return agenceService.findById(id);
    }

    @Operation(summary = "Ajouter une agence")
    @ApiResponse(responseCode = "201", description = "Agence créée")
    @ApiResponse(responseCode = "400", description = "Données invalides ou identifiant déjà rempli")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agence create(@RequestBody Agence agence) {
        return agenceService.create(agence);
    }

    @Operation(summary = "Modifier une agence")
    @ApiResponse(responseCode = "200", description = "Agence modifiée")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @PutMapping("{id}")
    public Agence update(@PathVariable Long id, @RequestBody Agence agence) {
        return agenceService.update(id, agence);
    }

    @Operation(summary = "Supprimer une agence")
    @ApiResponse(responseCode = "204", description = "Agence supprimée")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        agenceService.deleteById(id);
    }

    @Operation(summary = "Simuler le coût de location des véhicules d'une agence")
    @ApiResponse(responseCode = "200", description = "Rapport de simulation")
    @ApiResponse(responseCode = "404", description = "Agence introuvable")
    @GetMapping("{id}/simulation")
    public String simuler(@PathVariable Long id,
                          @RequestParam int jours,
                          @RequestParam VehiculeOffre offre) {
        return agenceService.simuler(id, jours, offre);
    }
}