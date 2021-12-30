/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.ProductDTO;
import com.ipn.mx.modelo.dto.RestaurantDTO;
import com.ipn.mx.modelo.entidades.Product;
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
public class ProductDAO {
    
    public void create(ProductDTO dto){
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
    
    public void update(ProductDTO dto){
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
    
    public void delete(ProductDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.delete(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdproducto()));
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
            Query q = s.createQuery("from Product");
            for(Product c: (List<Product>)q.list()){
                ProductDTO dto = new ProductDTO();
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
    
    public ProductDTO read(ProductDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdproducto()));
            t.commit();
        } catch (HibernateException e) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
        return dto;
    }
    
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        ProductDTO dto = new ProductDTO();
        
        RestaurantDAO rdao = new RestaurantDAO();
        RestaurantDTO rdto = new RestaurantDTO();
        
        rdto.getEntidad().setIdrestaurant(3);
        
        dto.getEntidad().setNombre("Pizza Pepperoni");
        dto.getEntidad().setDescripcion("Pizza Mediana de 2 ingredientes");
        dto.getEntidad().setPrecio(130.00);
        dto.getEntidad().setStock(245);
        dto.getEntidad().setFk_restaurant(rdao.read(rdto).getEntidad());
        
        dao.create(dto);
        System.out.println(dao.readAll());
    }
}
