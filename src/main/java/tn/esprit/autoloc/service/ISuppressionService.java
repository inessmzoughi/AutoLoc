package tn.esprit.autoloc.service;

public interface ISuppressionService<ID> {
    void deleteById(ID id);
}