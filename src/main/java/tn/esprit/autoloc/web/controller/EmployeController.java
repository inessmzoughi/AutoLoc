package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@Tag(name = "Employés", description = "Gestion des employés")
@RestController
@RequestMapping("employes")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmployeService service;

    @Operation(summary = "Lister les employés")
    @GetMapping
    public List<Employe> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Consulter un employé")
    @ApiResponse(responseCode = "404", description = "Employé introuvable")
    @GetMapping("{id}")
    public Employe findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Ajouter un employé")
    @ApiResponse(responseCode = "201", description = "Employé créé")
    @ApiResponse(responseCode = "400", description = "Données invalides ou identifiant déjà rempli")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employe create(@RequestBody Employe employe) {
        return service.create(employe);
    }

    @Operation(summary = "Modifier un employé")
    @ApiResponse(responseCode = "404", description = "Employé introuvable")
    @PutMapping("{id}")
    public Employe update(@PathVariable Long id, @RequestBody Employe employe) {
        return service.update(id, employe);
    }

    @Operation(summary = "Supprimer un employé")
    @ApiResponse(responseCode = "204", description = "Employé supprimé")
    @ApiResponse(responseCode = "404", description = "Employé introuvable")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}