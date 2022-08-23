package com.example.godevsenior.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "item")

public class PedidoModel {
    @Id
    public Integer idPedido;
    @Column(nullable = false)
    public int number;
    @Column(nullable = false)
    public DateTimeFormat date;
    @Column(nullable = false)
    public double percentualDiscount;
    @Column(nullable = false)
    public double totalValue;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public DateTimeFormat getDate() {
        return date;
    }

    public void setDate(DateTimeFormat date) {
        this.date = date;
    }

    public double getPercentualDiscount() {
        return percentualDiscount;
    }

    public void setPercentualDiscount(double percentualDiscount) {
        this.percentualDiscount = percentualDiscount;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
}