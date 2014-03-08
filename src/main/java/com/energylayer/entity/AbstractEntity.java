package com.energylayer.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author: rkotelnikov
 */
@MappedSuperclass
public class AbstractEntity<Pk extends Serializable> implements Entity<Pk> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Pk id;

    @Override
    public Pk getId() {
        return id;
    }

    @Override
    public void setId(Pk id) {
        this.id = id;
    }
}
