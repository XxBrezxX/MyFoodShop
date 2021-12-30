/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Payment;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Bryan Hdz
 */
@Data
@AllArgsConstructor
public class PaymentDTO implements Serializable{
    private Payment entidad;
    
    public PaymentDTO(){
        entidad = new Payment();
    }
}
