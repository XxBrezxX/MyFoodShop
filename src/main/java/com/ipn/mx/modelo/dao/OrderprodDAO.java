/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.ClientDTO;
import com.ipn.mx.modelo.dto.OrderprodDTO;
import com.ipn.mx.modelo.dto.OrdertableDTO;
import com.ipn.mx.modelo.dto.ProductDTO;
import com.ipn.mx.modelo.entidades.Order_prod;
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
public class OrderprodDAO {
    
    
    public void create(OrderprodDTO dto){
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
    
    public void update(OrderprodDTO dto){
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
    
    public void delete(OrderprodDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.delete(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdorderprod()));
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
            Query q = s.createQuery("from Order_prod");
            for(Order_prod c: (List<Order_prod>)q.list()){
                OrderprodDTO dto = new OrderprodDTO();
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
    
    public List<OrderprodDTO> getShopWheel(OrdertableDTO dtoClient){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        List<OrderprodDTO> lista = new ArrayList<>();
        try {
            t.begin();
            Query q = s.createNativeQuery("SELECT * FROM Order_prod WHERE fk_order = :vidorder", Order_prod.class)
                    .setParameter("vidorder", dtoClient.getEntidad().getIdorder());
            for (Order_prod c : (List<Order_prod>) q.list()) {
                OrderprodDTO dto2 = new OrderprodDTO();
                dto2.setEntidad(c);
                lista.add(dto2);
            }
            t.commit();
        } catch (Exception e) {
        }
        return lista;
    }
    
    public OrderprodDTO read(OrderprodDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdorderprod()));
            t.commit();
        } catch (HibernateException he) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
        return dto;
    }
    
    public static void main(String[] args) {
        OrderprodDAO dao = new OrderprodDAO();
        OrderprodDTO dto = new OrderprodDTO();
        
        OrdertableDAO daoOrder = new OrdertableDAO();
        OrdertableDTO dtoOrder = new OrdertableDTO();
        dtoOrder.getEntidad().setIdorder(8);
        dtoOrder.setEntidad(daoOrder.read(dtoOrder).getEntidad());
        
        ProductDAO daoProd = new ProductDAO();
        ProductDTO dtoProd = new ProductDTO();
        
        dtoProd.getEntidad().setIdproducto(3);
        dtoProd.setEntidad(daoProd.read(dtoProd).getEntidad());
        
        dto.getEntidad().setCantidad(5);
        dto.getEntidad().setFk_order(dtoOrder.getEntidad());
        dto.getEntidad().setFk_product(dtoProd.getEntidad());
        
        List res = dao.readAll();
        System.out.println(res);
    }
}
