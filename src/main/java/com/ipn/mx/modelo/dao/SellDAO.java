/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.OrdertableDTO;
import com.ipn.mx.modelo.dto.SellDTO;
import com.ipn.mx.modelo.entidades.Sell;
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
public class SellDAO {
    
    public void create(SellDTO dto){
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
    
    public void update(SellDTO dto){
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
    
    public void delete(SellDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.delete(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdsell()));
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
            Query q = s.createQuery("from Sell");
            for(Sell c: (List<Sell>)q.list()){
                SellDTO dto = new SellDTO();
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
    
    public SellDTO read(SellDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdsell()));
            t.commit();
        } catch (HibernateException e) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
        return dto;
    }
    
    public static void main(String[] args) {
        SellDAO dao = new SellDAO();
        SellDTO dto = new SellDTO();
        
        OrdertableDAO odao = new OrdertableDAO();
        OrdertableDTO odto = new OrdertableDTO();
        
        odto.getEntidad().setIdorder(8);
        
        dto.getEntidad().setTotal(123);
        dto.getEntidad().setFk_order(odao.read(odto).getEntidad());
        
        dao.create(dto);
        System.out.println(dao.readAll());
    }
}
