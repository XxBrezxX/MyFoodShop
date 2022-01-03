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
    
    
    public OrdertableDTO giveAddresses(ClientDTO dtoClient){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        List<OrdertableDTO> lista = new ArrayList<>();
        try {
            t.begin();
            Query q = s.createNativeQuery("SELECT * FROM Ordertable WHERE fk_user = :vidclient", Ordertable.class)
                    .setParameter("vidclient", dtoClient.getEntidad().getIdclient());
            for (Ordertable c : (List<Ordertable>) q.list()) {
                OrdertableDTO dto2 = new OrdertableDTO();
                dto2.setEntidad(c);
                lista.add(dto2);
            }
            t.commit();
            OrdertableDTO aux = null;
            for(OrdertableDTO dto: lista){
                System.out.println(dto.getEntidad().isIsdelivered());
                if(!dto.getEntidad().isIsdelivered()){
                    aux = dto;
                }
            }
            System.out.println(aux);
            return aux;
        } catch (Exception e) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        OrdertableDAO dao = new OrdertableDAO();
        OrdertableDTO dto = new OrdertableDTO();
        
        ClientDAO cdao = new ClientDAO();
        ClientDTO cdto = new ClientDTO();
        
        cdto.getEntidad().setIdclient(3);
        cdto = cdao.read(cdto);
        System.out.println(dao.giveAddresses(cdto));
        System.out.println(cdto);
    }
}
