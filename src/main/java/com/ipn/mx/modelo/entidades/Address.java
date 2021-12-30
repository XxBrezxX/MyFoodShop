/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "address")
public class Address implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idaddress;
    
    @Column(name="calle", length = 20, nullable = false)
    private String calle;
    
    @Column(name="colonia", length = 25, nullable = false)
    private String colonia;
    
    @Column(name="numero", nullable = false)
    private int numero;
    
    @Column(name="cp", nullable = false)
    private int cp;
    
    @Column(name="ciudad", length = 30, nullable = false)
    private String ciudad;
    
    @Column(name="municipio", length = 30, nullable = false)
    private String municipio;
}
