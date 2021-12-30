package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.AddressDTO;
import com.ipn.mx.modelo.dto.ClientDTO;
import com.ipn.mx.modelo.dto.DeliveryDTO;
import com.ipn.mx.modelo.dto.OrdertableDTO;
import com.ipn.mx.modelo.entidades.Ordertable;
import com.ipn.mx.utilerias.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bryan Hdz
 */
public class OrdertableDAO {
    
    public void create(OrdertableDTO dto){
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
    
    public void update(OrdertableDTO dto){
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
    
    public void delete(OrdertableDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.delete(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdorder()));
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
            Query q = s.createQuery("from Ordertable");
            for(Ordertable c: (List<Ordertable>)q.list()){
                OrdertableDTO dto = new OrdertableDTO();
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
    
    public OrdertableDTO read(OrdertableDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdorder()));
            t.commit();
        } catch (HibernateException e) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
        return dto;
    }
    
    public static void main(String[] args) {
        OrdertableDAO dao = new OrdertableDAO();
        OrdertableDTO dto = new OrdertableDTO();
        
        ClientDAO cdao = new ClientDAO();
        ClientDTO cdto = new ClientDTO();
        
        DeliveryDAO ddao = new DeliveryDAO();
        DeliveryDTO ddto = new DeliveryDTO();
        
        AddressDAO adao = new AddressDAO();
        AddressDTO adto = new AddressDTO();
        
        cdto.getEntidad().setIdclient(2);
        ddto.getEntidad().setIddelivery(4);
        adto.getEntidad().setIdaddress(2);
        
        dto.getEntidad().setFk_client(cdao.read(cdto).getEntidad());
        dto.getEntidad().setFk_delivery(ddao.read(ddto).getEntidad());
        dto.getEntidad().setFk_address(adao.read(adto).getEntidad());
        dto.getEntidad().setIsdelivered(true);
        
        dao.create(dto);
        System.out.println(dao.readAll());
    }
}
