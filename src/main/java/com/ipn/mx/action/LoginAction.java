/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.action;

import com.ipn.mx.modelo.dao.ClientDAO;
import com.ipn.mx.modelo.dto.ClientDTO;
import com.ipn.mx.utilerias.Singleton;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Bryan Hdz
 */
public class LoginAction extends ActionSupport implements SessionAware {

    private String client;
    private String correo;
    private String digesto;
    private SessionMap<String, Object> sessionMap;

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap) map;
    }

    public String execute() {
        String answer = ERROR;
        ClientDAO dao = new ClientDAO();
        ClientDTO dto = new ClientDTO();

        dto.getEntidad().setCorreo(correo);
        dto.getEntidad().setDigesto(digesto);
        System.out.println(dto);

        try {
            dto = dao.verifyEmailPassword(dto);
            if (dto != null) {
                answer = SUCCESS;
            }
        } catch (Exception e) {
        }
        Singleton sing = Singleton.getInstance(dto);
        sessionMap.put("dtoU", dto.getEntidad().getIdclient());
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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

}
