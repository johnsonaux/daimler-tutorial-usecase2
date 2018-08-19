package com.exxeta.maau.tutorialmaau.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DailyProduction {

    @Id
    @GeneratedValue
    private Long id;

    private Date localDate;

    @ManyToOne
    @JoinColumn(name = "factory_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Factory factory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Date localDate) {
        this.localDate = localDate;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}
