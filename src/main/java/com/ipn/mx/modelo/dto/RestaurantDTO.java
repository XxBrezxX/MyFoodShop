/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Bryan Hdz
 */
@Data
@AllArgsConstructor
public class RestaurantDTO {
    private Restaurant entidad;

    public RestaurantDTO() {
        this.entidad = new Restaurant();
    }
    
}
