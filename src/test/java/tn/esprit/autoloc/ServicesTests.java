package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.exception.ApiException;
import tn.esprit.autoloc.service.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServicesTests {

    @Autowired private IAgenceService agenceService;
    @Autowired private IVehiculeService vehiculeService;
    @Autowired private IClientService clientService;
    @Autowired private IContratService contratService;
    @Autowired private IEmployeService employeService;
    @Autowired private IEquipementService equipementService;
    @Autowired private IMaintenanceService maintenanceService;
    @Autowired private IReservationService reservationService;
    @Autowired private IPaiementService paiementService;

    private Agence nouvelleAgence() {
        Agence a = new Agence();
        a.setNom("Agence Test");
        a.setVille("Sousse");
        a.setAdresse("1 Rue Test");
        a.setTelephone("73000000");
        return a;
    }

    @Test
    void tousLesServicesSontInjectes() {
        assertNotNull(agenceService);
        assertNotNull(vehiculeService);
        assertNotNull(clientService);
        assertNotNull(contratService);
        assertNotNull(employeService);
        assertNotNull(equipementService);
        assertNotNull(maintenanceService);
        assertNotNull(reservationService);
        assertNotNull(paiementService);
    }

    @Test
    void findAllFonctionnePourTous() {
        assertNotNull(agenceService.findAll());
        assertNotNull(vehiculeService.findAll());
        assertNotNull(clientService.findAll());
        assertNotNull(contratService.findAll());
        assertNotNull(employeService.findAll());
        assertNotNull(equipementService.findAll());
        assertNotNull(maintenanceService.findAll());
        assertNotNull(reservationService.findAll());
        assertNotNull(paiementService.findAll());
    }

    @Test
    void createAvecIdEchoue() {
        Agence a = nouvelleAgence();
        a.setIdAgence(5L);
        assertThrows(IllegalArgumentException.class, () -> agenceService.create(a));
    }

    @Test
    void createAvecDonneesInvalidesEchoue() {
        Agence a = nouvelleAgence();
        a.setNom("   ");
        assertThrows(ApiException.class, () -> agenceService.create(a));
    }

    @Test
    void findByIdIntrouvableEchoue() {
        assertThrows(ApiException.class, () -> agenceService.findById(999999L));
    }

    @Test
    void deleteIntrouvableEchoue() {
        assertThrows(ApiException.class, () -> agenceService.deleteById(999999L));
    }

    @Test
    void cycleCreationModificationSuppressionAgence() {
        Agence saved = agenceService.create(nouvelleAgence());
        assertNotNull(saved.getIdAgence());

        Agence modif = nouvelleAgence();
        modif.setNom("Agence Modifiee");
        Agence updated = agenceService.update(saved.getIdAgence(), modif);
        assertEquals("Agence Modifiee", updated.getNom());
        assertFalse(agenceService.hasVehicules(saved.getIdAgence()));

        agenceService.deleteById(saved.getIdAgence());
        assertThrows(ApiException.class, () -> agenceService.findById(saved.getIdAgence()));
    }

    @Test
    void creationClient() {
        Client c = new Client();
        c.setNom("Ben Ali");
        c.setPrenom("Sami");
        c.setEmail("sami" + System.currentTimeMillis() + "@mail.com");
        c.setTelephone("20123456");
        c.setNumPermis("P" + System.currentTimeMillis());
        c.setDateInscription(LocalDate.now());

        Client saved = clientService.create(c);
        assertNotNull(saved.getIdClient());
    }

    @Test
    void creationEquipement() {
        Equipement e = new Equipement();
        e.setLibelle("GPS");

        Equipement saved = equipementService.create(e);
        assertNotNull(saved.getIdEquipement());
    }
}