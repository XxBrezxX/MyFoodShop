/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.AddressDTO;
import com.ipn.mx.modelo.dto.ClientDTO;
import com.ipn.mx.modelo.dto.ClientaddressDTO;
import com.ipn.mx.modelo.entidades.Client;
import com.ipn.mx.modelo.entidades.Client_address;
import com.ipn.mx.utilerias.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Bryan Hdz
 */
public class ClientaddressDAO {
    
    public void create(ClientaddressDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.save(dto.getEntidad());
            t.commit();
        } catch (HibernateException e) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
    }
    
    public void update(ClientaddressDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.update(dto.getEntidad());
            t.commit();
        } catch (HibernateException he) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
    }
    
    public void delete(ClientaddressDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.delete(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdclientaddress()));
            t.commit();
        } catch (HibernateException he) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
    }
    
    public List readAll(){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        List lista = new ArrayList();
        try {
            t.begin();
            Query q = s.createQuery("from Client_address");
            for(Client_address c: (List<Client_address>)q.list()){
                ClientaddressDTO dto = new ClientaddressDTO();
                dto.setEntidad(c);
                lista.add(dto);
            }
            t.commit();
        } catch (HibernateException he) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
        return lista;
    }
    
    public ClientaddressDTO read(ClientaddressDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdclientaddress()));
            t.commit();
        } catch (HibernateException he) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
        return dto;
    }
    
    public ClientaddressDTO giveAddresses(ClientDTO dtoClient){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        List<ClientaddressDTO> lista = new ArrayList<>();
        try {
            t.begin();
            Query q = s.createNativeQuery("SELECT * FROM Client_address WHERE fk_client = :vidclient", Client_address.class)
                    .setParameter("vidclient", dtoClient.getEntidad().getIdclient());
            for (Client_address c : (List<Client_address>) q.list()) {
                ClientaddressDTO dto2 = new ClientaddressDTO();
                dto2.setEntidad(c);
                lista.add(dto2);
            }
            t.commit();
            return lista.get(0);
        } catch (Exception e) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        ClientaddressDAO dao = new ClientaddressDAO();
        ClientaddressDTO dto = new ClientaddressDTO();
        
        AddressDAO daoAdd = new AddressDAO();
        AddressDTO dtoAdd = new AddressDTO();
        dtoAdd.getEntidad().setIdaddress(2);
        
        ClientDAO daoCli = new ClientDAO();
        ClientDTO dtoCli = new ClientDTO();
        dtoCli.getEntidad().setIdclient(3);
        
        dto.getEntidad().setFk_address(daoAdd.read(dtoAdd).getEntidad());
        dto.getEntidad().setFk_client(daoCli.read(dtoCli).getEntidad());
        dao.create(dto);
        
        System.out.println(dao.readAll());
    }
    
}

