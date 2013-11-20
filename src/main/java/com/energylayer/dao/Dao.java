package com.energylayer.dao;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author: rkotelnikov
 */
public interface Dao<E, Pk extends Serializable> {

    E findByID(Pk id);

    List<E> findAll();

    int countAll();

    void saveOrUpdate(E entity);

    void delete(Pk id);

    void delete(E entity);
}
