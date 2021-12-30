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
@Table(name = "ordertable")
public class Ordertable implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idorder;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_user")
    private Client fk_client;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_delivery")
    private Delivery fk_delivery;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_address")
    private Address fk_address;
    
    @Column(name = "isdelivered", nullable = false)
    private boolean isdelivered;
}
