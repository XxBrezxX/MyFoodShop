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
@Table(name = "Restaurant")
public class Restaurant implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idrestaurant;
    
    @Column(name = "razon_social", length=20, nullable = false)
    private String razon_social;
    
    @Column(name = "telefono", nullable = false)
    private String telefono;
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_payment")
    private Payment fk_payment;
}
