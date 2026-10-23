package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@Tag(name = "Clients", description = "Gestion des clients")
@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final IClientService service;

    @Operation(summary = "Lister les clients")
    @GetMapping
    public List<Client> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Consulter un client")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @GetMapping("{id}")
    public Client findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Ajouter un client")
    @ApiResponse(responseCode = "201", description = "Client créé")
    @ApiResponse(responseCode = "400", description = "Données invalides ou identifiant déjà rempli")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client) {
        return service.create(client);
    }

    @Operation(summary = "Modifier un client")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @PutMapping("{id}")
    public Client update(@PathVariable Long id, @RequestBody Client client) {
        return service.update(id, client);
    }

    @Operation(summary = "Supprimer un client")
    @ApiResponse(responseCode = "204", description = "Client supprimé")
    @ApiResponse(responseCode = "404", description = "Client introuvable")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}