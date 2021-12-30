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
@Table(name = "payment")
public class Payment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpayment;
    
    @Column(name = "ntarjeta", length=25, nullable = false)
    private String ntajeta;
    
    @Column(name = "fecha", nullable = false)
    private java.sql.Date fecha;
    
    @Column(name = "cv", nullable = false)
    private int cv;
    
    @Column(name = "isrepartidor", nullable = false)
    private boolean isrepartidor;
    
    @Column(name = "isrestaurant", nullable = false)
    private boolean isrestaurant;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_client")
    private Client fk_client;
    
}
