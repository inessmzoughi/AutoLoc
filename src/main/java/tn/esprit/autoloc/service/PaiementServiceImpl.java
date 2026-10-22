package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.exception.ApiException;
import tn.esprit.autoloc.repository.IPaiementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository repository;

    @Override
    public Paiement findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException("Paiement introuvable avec l'id " + id));
    }

    @Override
    public List<Paiement> findAll() {
        return repository.findAll();
    }
}