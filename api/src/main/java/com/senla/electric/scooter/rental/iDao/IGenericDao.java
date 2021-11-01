package com.senla.finalProject.iDao;

import com.senla.finalProject.model.AbstractEntity;

import java.util.List;

public interface IGenericDao<T extends AbstractEntity>{

    Class<T> getEntityClass();
    T save(T entity);
    T getById(Long id);
    List<T> getAll();
    T delete(T entity);
    T update(Long id, T entity);
}
