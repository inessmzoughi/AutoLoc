package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class AgenceTests {

    @Autowired
    private AgenceRepositoryMock agenceRepository;

    @Test
    void addAgence() {
        Agence agence = new Agence();
        agence.setNom("Agence Tunis Centre");
        agence.setVille("Tunis");
        agence.setAdresse("12 Avenue Habib Bourguiba");
        agence.setTelephone("71123456");

        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("123TU4567");
        v1.setMarque("Renault");
        v1.setModele("Clio");
        v1.setCategorie(CategorieVehicule.CITADINE);
        v1.setTarifJournalier(new BigDecimal("60.00"));
        v1.setStatut(StatutVehicule.DISPONIBLE);
        v1.setAgence(agence);

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("456TU7890");
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

        agenceRepository.save(agence);
    }

    @Test
    void loadAgence() {
        StringBuilder sb = new StringBuilder("\n");

        for (Agence a : agenceRepository.findAll()) {
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
}

interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}