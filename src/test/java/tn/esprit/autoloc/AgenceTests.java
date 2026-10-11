package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class AgenceTests {

    @Autowired
    private AgenceRepositoryMock basicAgenceRepository;

    @Autowired
    private IAgenceRepository fullAgenceRepository;

    // ---------- Ajout ----------

    private void addAgence(CrudRepository<Agence, Long> repository) {
        // information supplémentaire pour éviter les duplications d'immatriculation
        int suffixe = (int) System.currentTimeMillis();

        Agence agence = new Agence();
        agence.setNom("Agence Tunis Centre");
        agence.setVille("Tunis");
        agence.setAdresse("12 Avenue Habib Bourguiba");
        agence.setTelephone("71123456");

        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("123TU" + suffixe);
        v1.setMarque("Renault");
        v1.setModele("Clio");
        v1.setCategorie(CategorieVehicule.CITADINE);
        v1.setTarifJournalier(new BigDecimal("60.00"));
        v1.setStatut(StatutVehicule.DISPONIBLE);
        v1.setAgence(agence);

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("456TU" + suffixe);
        v2.setMarque("Peugeot");
        v2.setModele("308");
        v2.setCategorie(CategorieVehicule.BERLINE);
        v2.setTarifJournalier(new BigDecimal("90.00"));
        v2.setStatut(StatutVehicule.DISPONIBLE);
        v2.setAgence(agence);

        Set<Vehicule> vehicules = new HashSet<>();
        vehicules.add(v1);
        vehicules.add(v2);
        agence.setVehicules(vehicules);

        repository.save(agence);
    }

    @Test
    void basicAddAgence() {
        addAgence(basicAgenceRepository);
    }

    @Test
    void fullAddAgence() {
        addAgence(fullAgenceRepository);
    }

    // ---------- Chargement ----------

    private void loadAgence(CrudRepository<Agence, Long> repository, String typeDepot) {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("Dépôt utilisé = ").append(typeDepot).append("\n\n");

        for (Agence a : repository.findAll()) {
            sb.append("Agence id = ").append(a.getIdAgence()).append("\n");
            sb.append("Nom = ").append(a.getNom()).append("\n");
            sb.append("Nombre de véhicules = ").append(a.getVehicules().size()).append("\n");
            for (Vehicule v : a.getVehicules()) {
                sb.append("   - Véhicule id = ").append(v.getIdVehicule())
                        .append(", immatriculation = ").append(v.getImmatriculation()).append("\n");
            }
            sb.append("\n");
        }

        fail(sb.toString()); // échoue toujours, pour afficher le résultat
    }

    @Test
    void basicLoadAgence() {
        loadAgence(basicAgenceRepository, "CrudRepository (basic)");
    }

    @Test
    void fullLoadAgence() {
        loadAgence(fullAgenceRepository, "JpaRepository (full)");
    }

    // ---------- Tri ----------

    @Test
    void loadSortedAgences() {
        List<Agence> agences = fullAgenceRepository.findAll(Sort.by(Sort.Direction.DESC, "idAgence"));

        StringBuilder sb = new StringBuilder("\n");
        for (Agence a : agences) {
            sb.append("Agence id = ").append(a.getIdAgence())
                    .append(", nom = ").append(a.getNom())
                    .append(", ville = ").append(a.getVille()).append("\n");
        }

        fail(sb.toString());
    }

    // ---------- Pagination ----------

    @Test
    void loadPagedAgences() {
        Page<Agence> page = fullAgenceRepository.findAll(
                PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "idAgence")));

        StringBuilder sb = new StringBuilder("\n");
        sb.append("Nombre total de pages = ").append(page.getTotalPages()).append("\n");
        sb.append("Page en cours = ").append(page.getNumber()).append("\n\n");
        for (Agence a : page.getContent()) {
            sb.append("Agence id = ").append(a.getIdAgence())
                    .append(", nom = ").append(a.getNom())
                    .append(", ville = ").append(a.getVille()).append("\n");
        }

        fail(sb.toString());
    }
}

interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}