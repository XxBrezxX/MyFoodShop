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

    public void create(ClientDTO dto) {
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

    public void update(ClientDTO dto) {
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

    public void delete(ClientDTO dto) {
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

    public List readAll() {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        List lista = new ArrayList();
        try {
            t.begin();
            Query q = s.createQuery("from Client");
            for (Client c : (List<Client>) q.list()) {
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

    public ClientDTO read(ClientDTO dto) {
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

    public ClientDTO verifyEmailPassword(ClientDTO dto) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        List<ClientDTO> lista = new ArrayList<>();
        try {
            t.begin();
            Query q = s.createNativeQuery("SELECT * FROM Client WHERE correo like :vcorreo and digesto like :vdig", Client.class)
                    .setParameter("vcorreo", dto.getEntidad().getCorreo())
                    .setParameter("vdig", dto.getEntidad().getDigesto());
            for (Client c : (List<Client>) q.list()) {
                ClientDTO dto2 = new ClientDTO();
                dto2.setEntidad(c);
                lista.add(dto2);
            }
            t.commit();
            return lista.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        ClientDTO dto = new ClientDTO();
        ClientDAO dao = new ClientDAO();
        dto.getEntidad().setCorreo("myCorreo@gmail.com");
        dto.getEntidad().setDigesto("11111");

        System.out.println(dao.verifyEmailPassword(dto));
    }
}
