package com.example.godevsenior.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "item")

public class ItemModel {
    @Id
    public Integer idItem;
    @Column(nullable = false)
    public String description;
    @Column(nullable = false)
    public double value;
    @Column(nullable = false)
    public char type;

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }
}