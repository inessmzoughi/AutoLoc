package tn.esprit.autoloc.service;

public interface IEcritureService<T, ID> {
    boolean check(T entity, boolean isNew);
    T create(T entity);
    T update(ID id, T entity);
}