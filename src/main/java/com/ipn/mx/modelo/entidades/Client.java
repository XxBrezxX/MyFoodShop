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
@Table(name = "client")
public class Client implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idclient;
    
    @Column(name="nombre", length = 20, nullable = false)
    private String nombre;
    
    @Column(name="apellido", length = 20, nullable = false)
    private String apellido;
    
    @Column(name="amaterno", length = 20, nullable = false)
    private String amaterno;
    
    @Column(name="correo", length = 50, nullable = false)
    private String correo;
    
    @Column(name="digesto", length = 30, nullable = false)
    private String digesto;
    
    @Column(name="telefono", length = 15, nullable = false)
    private String telefono;
    
}
