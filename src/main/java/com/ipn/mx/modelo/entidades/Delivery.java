/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

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
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddelivery;
    
    @Column(name = "nombre", length=20, nullable = false)
    private String nombre;
    
    @Column(name = "apellido", length=20, nullable = false)
    private String apellido;
    
    @Column(name = "amaterno", length=20, nullable = false)
    private String amaterno;
    
    @Column(name = "correo", length=50, nullable = false)
    private String correo;
    
    @Column(name = "telefono", length=15, nullable = false)
    private String telefono;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_payment")
    private Payment fk_payment;
    
}
