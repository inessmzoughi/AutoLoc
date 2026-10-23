package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@Tag(name = "Réservations", description = "Gestion des réservations")
@RestController
@RequestMapping("reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService service;

    @Operation(summary = "Lister les réservations")
    @GetMapping
    public List<Reservation> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Consulter une réservation")
    @ApiResponse(responseCode = "404", description = "Réservation introuvable")
    @GetMapping("{id}")
    public Reservation findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Ajouter une réservation")
    @ApiResponse(responseCode = "201", description = "Réservation créée")
    @ApiResponse(responseCode = "400", description = "Données invalides ou identifiant déjà rempli")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation create(@RequestBody Reservation reservation) {
        return service.create(reservation);
    }

    @Operation(summary = "Modifier une réservation")
    @ApiResponse(responseCode = "404", description = "Réservation introuvable")
    @PutMapping("{id}")
    public Reservation update(@PathVariable Long id, @RequestBody Reservation reservation) {
        return service.update(id, reservation);
    }

    @Operation(summary = "Supprimer une réservation")
    @ApiResponse(responseCode = "204", description = "Réservation supprimée")
    @ApiResponse(responseCode = "404", description = "Réservation introuvable")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}