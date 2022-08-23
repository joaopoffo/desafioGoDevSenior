package com.example.godevsenior.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


public class DescontoModel {

    public Integer order;
    public double percentualDiscount;
}