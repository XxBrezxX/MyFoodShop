/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.DeliveryDTO;
import com.ipn.mx.modelo.dto.PaymentDTO;
import com.ipn.mx.modelo.entidades.Delivery;
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
public class DeliveryDAO {
    public void create(DeliveryDTO dto){
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
    
    public void update(DeliveryDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.update(dto.getEntidad());
            t.commit();
        } catch (HibernateException e) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
    }
    
    public void delete(DeliveryDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.delete(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIddelivery()));
            t.commit();
        } catch (HibernateException e) {
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
            Query q = s.createQuery("from Delivery");
            for(Delivery c: (List<Delivery>)q.list()){
                DeliveryDTO dto = new DeliveryDTO();
                dto.setEntidad(c);
                lista.add(dto);
            }
            t.commit();
        } catch (HibernateException e) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
        return lista;
    }
    
    public DeliveryDTO read(DeliveryDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIddelivery()));
            t.commit();
        } catch (HibernateException e) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
        return dto;
    }
    
    public static void main(String[] args) {
        DeliveryDAO dao = new DeliveryDAO();
        DeliveryDTO dto = new DeliveryDTO();
        
        PaymentDAO daoP = new PaymentDAO();
        PaymentDTO dtoP = new PaymentDTO();
        
        dtoP.getEntidad().setIdpayment(8);
        
        dto.getEntidad().setNombre("Gloria");
        dto.getEntidad().setApellido("Galilea");
        dto.getEntidad().setAmaterno("Gonzalez");
        dto.getEntidad().setCorreo("entregasGeniales@hotmail.com");
        dto.getEntidad().setTelefono("5282458249");
        dto.getEntidad().setFk_payment(daoP.read(dtoP).getEntidad());
        
        dao.create(dto);
        System.out.println(dao.readAll());
    }
}
