/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.action;

import com.ipn.mx.modelo.dao.ProductDAO;
import com.ipn.mx.modelo.dto.ProductDTO;
import com.ipn.mx.modelo.entidades.Product;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

/**
 *
 * @author Bryan Hdz
 */
public class SiteAction extends ActionSupport{
    private List<Product> productos;

    public List<Product> getProductos() {
        return productos;
    }

    public void setProductos(List<Product> productos) {
        this.productos = productos;
    }
    
    public String execute(){
        return SUCCESS;
    }
    
    public String Inicio(){
        return SUCCESS;
    }
    
    public String Productos(){
        ProductDAO dao = new ProductDAO();
        this.productos = dao.readAll();
        return SUCCESS;
    }
    
    public String Reviews(){
        return SUCCESS;
    }
    
    public String Follow(){
        return SUCCESS;
    }
    
    public String Stats(){
        return SUCCESS;
    }
    
    public String Account(){
        return SUCCESS;
    }
    
    public String AgregarProducto(){
        return SUCCESS;
    }
}
