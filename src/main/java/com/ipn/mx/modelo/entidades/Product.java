/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idproducto;

    @Column(name = "nombre", length = 25, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 60, nullable = false)
    private String descripcion;

    @Column(name = "precio", precision = 10, scale = 2, nullable = false)
    private double precio;

    @Column(name = "stock", nullable = false)
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_restaurant")
    private Restaurant fk_restaurant;
    
//    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "order_prod",
//            joinColumns = { @JoinColumn(name = "fk_product") },
//            inverseJoinColumns = { @JoinColumn(name = "fk_order") },
//            uniqueConstraints = {
//                @UniqueConstraint {
//                    columnNames = {"fk_product","fk_order"}
//                }
//              }
//    )
////    Set<Ordertable> orders = new HashSet<>();
//    
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "order_prod",
//            joinColumns = @JoinColumn(name = "fk_product"),
//            inverseJoinColumns = @JoinColumn(name = "fk_order"),
//            uniqueConstraints = {
//                @UniqueConstraint(
//                        columnNames = {"fk_product", "fk_order"}
//                )
//            }
//    )
//    private List<Ordertable> order_prod;
}
