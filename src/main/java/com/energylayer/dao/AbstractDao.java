package com.energylayer.dao;

import com.energylayer.entity.Entity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @author: rkotelnikov
 */
public class AbstractDao<E extends Entity<Pk>, Pk extends Serializable> implements Dao<E, Pk> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<? extends E> entityClass;

    protected AbstractDao(Class<? extends E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E findById(Pk id) {
        return (E) getSession().get(entityClass, id);
    }

    @Override
    public List<E> findAll() {
        return list(criteria());
    }

    @Override
    public int countAll() {
        return count(criteria());
    }

    @Override
    public void saveOrUpdate(E entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(Pk id) {
        getSession().delete(findById(id));
    }

    @Override
    public void delete(E entity) {
        getSession().delete(entity);
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    protected List<E> list(DetachedCriteria criteria){
        return criteria.getExecutableCriteria(getSession())
                .list();
    }

    @SuppressWarnings("unchecked")
    protected List<E> list(DetachedCriteria criteria, int first, int number){
        return criteria.getExecutableCriteria(getSession())
                .setFirstResult(first)
                .setMaxResults(number)
                .list();
    }

    /**
     * Do not use this method with criteria enriched with projections
     */
    protected int count(DetachedCriteria criteria){
        criteria.setProjection(Projections.rowCount());
        int count = ((Number)unique(criteria)).intValue();
        criteria.setProjection(null);
        return count;
    }

    @SuppressWarnings("unchecked")
    protected E unique(DetachedCriteria criteria){
        return (E) criteria.getExecutableCriteria(getSession()).uniqueResult();
    }

    protected DetachedCriteria criteria(){
        return DetachedCriteria.forClass(entityClass);
    }
}
