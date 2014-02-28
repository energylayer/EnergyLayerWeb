package com.energylayer.entity;

import java.io.Serializable;

/**
 * @author: rkotelnikov
 */
public interface Entity<Pk extends Serializable> extends Serializable {

    Pk getId();

    void setId(Pk id);
}
