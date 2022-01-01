/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.AddressDTO;
import com.ipn.mx.modelo.dto.RestaurantDTO;
import com.ipn.mx.modelo.dto.RestaurantaddressDTO;
import com.ipn.mx.modelo.entidades.Restaurant_address;
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
public class RestaurantaddressDAO {
    public void create(RestaurantaddressDTO dto){
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
    
    public void update(RestaurantaddressDTO dto){
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
    
    public void delete(RestaurantaddressDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.delete(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdrestaurantaddress()));
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
            Query q = s.createQuery("from Restaurant_address");
            for(Restaurant_address c: (List<Restaurant_address>)q.list()){
                RestaurantaddressDTO dto = new RestaurantaddressDTO();
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
    
    public RestaurantaddressDTO read(RestaurantaddressDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdrestaurantaddress()));
            t.commit();
        } catch (HibernateException he) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
        return dto;
    }
    
    public static void main(String[] args) {
        RestaurantaddressDAO dao = new RestaurantaddressDAO();
        RestaurantaddressDTO dto = new RestaurantaddressDTO();
        
        RestaurantDAO daoRes = new RestaurantDAO();
        RestaurantDTO dtoRes = new RestaurantDTO();
        
        AddressDAO daoAdd = new AddressDAO();
        AddressDTO dtoAdd = new AddressDTO();
        
        dtoAdd.getEntidad().setIdaddress(1);
        dtoRes.getEntidad().setIdrestaurant(2);
        
        dto.getEntidad().setFk_address(daoAdd.read(dtoAdd).getEntidad());
        dto.getEntidad().setFk_restaurant(daoRes.read(dtoRes).getEntidad());
        
        dao.create(dto);
        System.out.println(dao.readAll());
    }
}
