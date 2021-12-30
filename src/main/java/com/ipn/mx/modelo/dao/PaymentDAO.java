/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.AddressDTO;
import com.ipn.mx.modelo.dto.ClientDTO;
import com.ipn.mx.modelo.dto.PaymentDTO;
import com.ipn.mx.modelo.entidades.Address;
import com.ipn.mx.modelo.entidades.Payment;
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
public class PaymentDAO {
    
    public void create(PaymentDTO dto){
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
    
    public void update(PaymentDTO dto){
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
    
    public void delete(PaymentDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.delete(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdpayment()));
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
            Query q = s.createQuery("from Payment");
            for(Payment c: (List<Payment>)q.list()){
                PaymentDTO dto = new PaymentDTO();
                dto.setEntidad(c);
                lista.add(dto);
            }
        } catch (HibernateException e) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
        return lista;
    }
    
    public PaymentDTO read(PaymentDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdpayment()));
            t.commit();
        } catch (HibernateException e) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
        return dto;
    }
    
    public static void main(String[] args) {
        PaymentDAO dao = new PaymentDAO();
        PaymentDTO dto = new PaymentDTO();
        
//        ClientDAO daoClient = new ClientDAO();
//        ClientDTO dtoClient = new ClientDTO();
//        dtoClient.getEntidad().setIdclient(7);
//        
//        dto.getEntidad().setNtajeta("4545232378789090");
//        dto.getEntidad().setFecha(java.sql.Date.valueOf("2006-04-30"));
//        dto.getEntidad().setCv(643);
//        dto.getEntidad().setIsrepartidor(false);
//        dto.getEntidad().setIsrestaurant(false);
//        dto.getEntidad().setFk_client(daoClient.read(dtoClient).getEntidad());
        
//        dao.create(dto);

        dto.getEntidad().setIdpayment(8);
        dto.setEntidad(dao.read(dto).getEntidad());
        dto.getEntidad().setIsrestaurant(false);
        dto.getEntidad().setIsrepartidor(true);
        
        dao.update(dto);

        System.out.println(dao.readAll());
    }
}
