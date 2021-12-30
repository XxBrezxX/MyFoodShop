/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.AddressDTO;
import com.ipn.mx.modelo.dto.ClientDTO;
import com.ipn.mx.modelo.entidades.Address;
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
public class AddressDAO {
    
    public void create(AddressDTO dto){
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
    
    public void update(AddressDTO dto){
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
    
    public void delete(AddressDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            s.delete(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdaddress()));
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
            Query q = s.createQuery("from Address");
            for(Address c: (List<Address>)q.list()){
                AddressDTO dto = new AddressDTO();
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
    
    public AddressDTO read(AddressDTO dto){
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(), dto.getEntidad().getIdaddress()));
            t.commit();
        } catch (HibernateException e) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
        }
        return dto;
    }
    
    public static void main(String[] args) {
        AddressDAO dao = new AddressDAO();
        AddressDTO dto = new AddressDTO();
        dto.getEntidad().setIdaddress(2);
        System.out.println(dao.read(dto));
    }
}
