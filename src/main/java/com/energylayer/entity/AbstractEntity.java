package com.energylayer.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author: rkotelnikov
 */
@MappedSuperclass
public class AbstractEntity implements Entity<Long> {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
