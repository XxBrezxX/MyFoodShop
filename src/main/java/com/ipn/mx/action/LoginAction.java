/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.action;

import com.ipn.mx.modelo.dao.ClientDAO;
import com.ipn.mx.modelo.dto.ClientDTO;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Bryan Hdz
 */
public class LoginAction extends ActionSupport{
    private String correo;
    private String digesto;
    
    public String execute(){
        String answer = ERROR;
        ClientDAO dao = new ClientDAO();
        ClientDTO dto = new ClientDTO();
        
        dto.getEntidad().setCorreo(correo);
        dto.getEntidad().setDigesto(digesto);
        System.out.println(dto);
        
        try {
            dto = dao.verifyEmailPassword(dto);
            if(dto != null) answer = SUCCESS;
        } catch (Exception e) {
        }
        return answer;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDigesto() {
        return digesto;
    }

    public void setDigesto(String digesto) {
        this.digesto = digesto;
    }
}
