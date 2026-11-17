
package tn.esprit.autoloc.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.calcul.VehiculeOffre;

@Primary
@Service
public class AgenceServiceImpl extends GenericServiceImpl<Agence, Long>
        implements IAgenceService {

    private final IVehiculeService vehiculeService;

    // Constructeur avec injection des dépendances
    public AgenceServiceImpl(IAgenceRepository repository,
                             IVehiculeService vehiculeService) {
        super(repository);
        this.vehiculeService = vehiculeService;
    }

    @Override
    protected String getEntityName() {
        return "Agence";
    }

    @Override
    protected Long getId(Agence agence) {
        return agence.getIdAgence();
    }

    @Override
    protected void setFields(Agence source, Agence target) {
        target.setNom(source.getNom());
        target.setVille(source.getVille());
        target.setAdresse(source.getAdresse());
        target.setTelephone(source.getTelephone());
    }

    @Override
    public boolean check(Agence agence, boolean isNew) {
        return agence != null
                && Utils.isNotEmpty(agence.getNom())
                && Utils.isNotEmpty(agence.getVille())
                && Utils.isNotEmpty(agence.getAdresse())
                && Utils.isNotEmpty(agence.getTelephone());
    }

    @Override
    public boolean hasVehicules(Long id) {
        return !findById(id).getVehicules().isEmpty();
    }

    @Override
    public String simuler(Long id, int jours, VehiculeOffre offre) {

        Agence agence = findById(id);

        double total = 0;

        for (Vehicule v : agence.getVehicules()) {
            total += vehiculeService.calculTarif(v, jours, offre);
        }

        return "Agence : id=" + agence.getIdAgence()
                + ", nom=" + agence.getNom()
                + ", tél=" + agence.getTelephone()
                + "\nTotal des tarifs (" + jours
                + " jours, offre " + offre + ") : "
                + String.format(java.util.Locale.US, "%.2f", total);
    }
}
