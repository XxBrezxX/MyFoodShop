/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Bryan Hdz
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sell")
public class Sell implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idsell;
    
    @Column(name = "total", precision = 10, scale = 2, nullable = false)
    private double total;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_order")
    private Ordertable fk_order;
}
