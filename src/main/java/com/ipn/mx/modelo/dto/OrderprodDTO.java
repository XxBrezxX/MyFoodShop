/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Order_prod;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Bryan Hdz
 */
@Data
@AllArgsConstructor
public class OrderprodDTO implements Serializable{
    private Order_prod entidad;
    
    public OrderprodDTO(){
        this.entidad = new Order_prod();
    }
}
