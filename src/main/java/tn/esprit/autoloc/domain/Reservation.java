package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @JsonIgnoreProperties("reservations")
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonIgnoreProperties({"agence", "reservations", "equipements"})
    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;

    @JsonIgnoreProperties("paiements")
    @OneToOne
    @JoinColumn(name = "contrat_id", unique = true)
    private Contrat contrat;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutReservation statut;
}