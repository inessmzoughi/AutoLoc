package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;
import tn.esprit.autoloc.calcul.VehiculeOffre;
@Service
public class VehiculeServiceImpl extends GenericServiceImpl<Vehicule, Long> implements IVehiculeService {

    public VehiculeServiceImpl(IVehiculeRepository repository) {
        super(repository);
    }

    @Override
    protected String getEntityName() {
        return "Véhicule";
    }

    @Override
    protected Long getId(Vehicule v) {
        return v.getIdVehicule();
    }

    @Override
    protected void setFields(Vehicule source, Vehicule target) {
        target.setImmatriculation(source.getImmatriculation());
        target.setMarque(source.getMarque());
        target.setModele(source.getModele());
        target.setCategorie(source.getCategorie());
        target.setTarifJournalier(source.getTarifJournalier());
        target.setStatut(source.getStatut());
        target.setAgence(source.getAgence());
    }

    @Override
    public boolean check(Vehicule v, boolean isNew) {
        return v != null
                && Utils.isNotEmpty(v.getImmatriculation())
                && Utils.isNotEmpty(v.getMarque())
                && Utils.isNotEmpty(v.getModele())
                && v.getCategorie() != null
                && v.getTarifJournalier() != null
                && v.getStatut() != null;
    }
    @Override
    public double calculTarif(Vehicule vehicule, int jours, VehiculeOffre offre) {
        double tarif = vehicule.getTarifJournalier().doubleValue();
        return switch (offre) {
            case STANDARD -> tarif * jours;
            case WEEKEND -> tarif * 0.80 * jours;
            case LONGUE_DUREE -> {
                double total = tarif * 0.75 * jours;
                yield total > 2000 ? total * 0.95 : total;
            }
        };
    }
}