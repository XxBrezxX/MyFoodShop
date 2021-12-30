/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.ClientDTO;
import com.ipn.mx.modelo.entidades.Client;
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
public class ClientDAO {
    
    public void create(ClientDTO dto){
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
    
    public void update(ClientDTO dto){
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
    
    public void delete(ClientDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.delete(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdclient()));
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
            Query q = s.createQuery("from Client");
            for(Client c: (List<Client>)q.list()){
                ClientDTO dto = new ClientDTO();
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
    
    public ClientDTO read(ClientDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdclient()));
            t.commit();
        } catch (HibernateException he) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
        return dto;
    }
    
    public static void main(String[] args) {
        ClientDTO dto = new ClientDTO();
        dto.getEntidad().setIdclient(9);
//        dto.getEntidad().setNombre("Patricia");
//        dto.getEntidad().setApellido("Magaña");
//        dto.getEntidad().setAmaterno("Carreño");
//        dto.getEntidad().setCorreo("pato@gmail.com");
//        dto.getEntidad().setDigesto("asdfg");
//        dto.getEntidad().setTelefono("7351273339");  
        
        ClientDAO dao = new ClientDAO();
        //dao.create(dto);
        dto = dao.read(dto);
        dto.getEntidad().setNombre("Veronica");
        dao.update(dto);
        //System.out.println(dao.read(dto));
        //dao.delete(dto);
    }
}
