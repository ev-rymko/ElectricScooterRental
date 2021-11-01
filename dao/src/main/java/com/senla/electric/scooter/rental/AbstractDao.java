package com.senla.finalProject;

import com.senla.finalProject.iDao.IGenericDao;
import com.senla.finalProject.model.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDao<T extends AbstractEntity> implements IGenericDao<T>, Serializable {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T getById(Long id) {
        return entityManager.find(getEntityClass(), id);
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(getEntityClass());
        Root<T> root = criteriaQuery.from(getEntityClass());
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }

    @Override
    public T delete(T entity) {
        entityManager.remove(entity);
        return entity;
    }

    @Override
    public T update(Long id, T entity) {
        entity.setId(id);
        return entityManager.merge(entity);
    }
}
