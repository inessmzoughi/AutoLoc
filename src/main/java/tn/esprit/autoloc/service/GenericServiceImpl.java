package tn.esprit.autoloc.service;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.exception.ApiException;

import java.util.List;

public abstract class GenericServiceImpl<T, ID> implements IGenericService<T, ID> {

    protected final JpaRepository<T, ID> repository;

    protected GenericServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    protected abstract String getEntityName();

    protected abstract ID getId(T entity);

    // copie les champs de source vers target (entité existante)
    protected abstract void setFields(T source, T target);

    @Override
    public boolean check(T entity, boolean isNew) {
        return entity != null;
    }

    @Override
    public T create(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException(getEntityName() + " est nul");
        }
        if (getId(entity) != null) {
            throw new IllegalArgumentException("L'identifiant de " + getEntityName() + " doit être vide lors de la création");
        }
        if (!check(entity, true)) {
            throw new ApiException("Données invalides pour " + getEntityName());
        }
        return repository.save(entity);
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException(getEntityName() + " introuvable avec l'id " + id));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T update(ID id, T entity) {
        T existing = findById(id);
        if (!check(entity, false)) {
            throw new ApiException("Données invalides pour " + getEntityName());
        }
        setFields(entity, existing);
        return repository.save(existing);
    }

    @Override
    public void deleteById(ID id) {
        if (!repository.existsById(id)) {
            throw new ApiException(getEntityName() + " introuvable avec l'id " + id);
        }
        repository.deleteById(id);
    }
}