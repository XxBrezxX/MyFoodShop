/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.utilerias;

import com.ipn.mx.modelo.dto.ClientDTO;

/**
 *
 * @author Bryan Hdz
 */
public class Singleton {
    private static Singleton instance;
    public ClientDTO client;

    private Singleton(ClientDTO client) {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.client = client;
    }

    public static Singleton getInstance(ClientDTO client) {
        if (instance == null) {
            instance = new Singleton(client);
        }
        return instance;
    }
}
