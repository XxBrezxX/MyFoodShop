/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.action;

import com.ipn.mx.modelo.dao.ClientaddressDAO;
import com.ipn.mx.modelo.dao.OrderprodDAO;
import com.ipn.mx.modelo.dao.OrdertableDAO;
import com.ipn.mx.modelo.dao.ProductDAO;
import com.ipn.mx.modelo.dto.ClientDTO;
import com.ipn.mx.modelo.dto.ClientaddressDTO;
import com.ipn.mx.modelo.dto.OrderprodDTO;
import com.ipn.mx.modelo.dto.OrdertableDTO;
import com.ipn.mx.modelo.dto.ProductDTO;
import com.ipn.mx.modelo.entidades.Order_prod;
import com.ipn.mx.modelo.entidades.Ordertable;
import com.ipn.mx.modelo.entidades.Product;
import com.ipn.mx.utilerias.Singleton;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Bryan Hdz
 */
public class SiteAction extends ActionSupport {

    private List<Product> productos;
    private ClientDTO dtoClient;
    private ClientaddressDTO dtoClientAdd;
    private Product product;
    private Order_prod order_prod;
    private List<OrderprodDTO> carrito;

    private final ProductDAO dao = new ProductDAO();
    private final OrderprodDAO opda = new OrderprodDAO();

    public List<Product> getProductos() {
        return productos;
    }

    public void setProductos(List<Product> productos) {
        this.productos = productos;
    }

    public ClientDTO getDtoClient() {
        return dtoClient;
    }

    public void setDtoClient(ClientDTO dtoClient) {
        this.dtoClient = dtoClient;
    }

    public ClientaddressDTO getDtoClientAdd() {
        return dtoClientAdd;
    }

    public void setDtoClientAdd(ClientaddressDTO dtoClientAdd) {
        this.dtoClientAdd = dtoClientAdd;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<OrderprodDTO> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<OrderprodDTO> carrito) {
        this.carrito = carrito;
    }

    public Order_prod getOrder_prod() {
        return order_prod;
    }

    public void setOrder_prod(Order_prod order_prod) {
        this.order_prod = order_prod;
    }

    public String execute() {
        return SUCCESS;
    }

    public String Start() {
        return SUCCESS;
    }

    public String Inicio() {
        ClientDTO dto = new ClientDTO();
        Singleton sing = Singleton.getInstance(dto);
        System.out.println(sing.client);
        return SUCCESS;
    }

    public String Productos() {
        this.productos = this.dao.readAll();
        return SUCCESS;
    }

    public String Reviews() {
        return SUCCESS;
    }

    public String Follow() {
        this.carrito = new ArrayList<>();
        String estado = SUCCESS;
        Singleton sing = Singleton.getInstance(dtoClient);
        this.dtoClient = sing.client;
        System.out.println(this.dtoClient);
        ClientaddressDAO daoAdd = new ClientaddressDAO();
        dtoClientAdd = daoAdd.giveAddresses(this.dtoClient);
        
        // Buscar la orden actual del usuario
        try {
            OrdertableDAO otda = new OrdertableDAO();
            OrdertableDTO otdt = new OrdertableDTO();
            
            otdt = otda.giveAddresses(this.dtoClient);
            
            // Obtener listado de articulos en la orden
            OrderprodDAO opda = new OrderprodDAO();
            OrderprodDTO opdt = new OrderprodDTO();
            
            this.carrito = opda.getShopWheel(otdt);
            
        } catch (Exception e) {
            estado = ERROR;
        }

        return estado;
    }

    public String Stats() {
        return SUCCESS;
    }

    public String Account() {
        return SUCCESS;
    }

    public String AgregarProducto() {
        OrdertableDAO daoOrderTable = new OrdertableDAO();
        Singleton sing = Singleton.getInstance(dtoClient);
        this.dtoClient = sing.client;

        OrdertableDTO dtoOrderTable = new OrdertableDTO();
        dtoOrderTable = daoOrderTable.giveAddresses(this.dtoClient);
        if (dtoOrderTable == null) { // No hay ninguna orden a este usuario
            
            dtoOrderTable = new OrdertableDTO();
            ClientaddressDAO daoAdd = new ClientaddressDAO();
            this.dtoClientAdd = daoAdd.giveAddresses(this.dtoClient);

            // Crear la Orden sin agregar ningun repartidor actual.
            dtoOrderTable.getEntidad().setFk_client(this.dtoClient.getEntidad());
            dtoOrderTable.getEntidad().setFk_address(this.dtoClientAdd.getEntidad().getFk_address());
            dtoOrderTable.getEntidad().setFk_delivery(null);
            dtoOrderTable.getEntidad().setIsdelivered(false);

            daoOrderTable.create(dtoOrderTable);

            // Obtener de regreso el registro creado con esta orden
            dtoOrderTable = daoOrderTable.giveAddresses(this.dtoClient);
            System.out.println(dtoOrderTable);

        }
        // Iniciar la relacion Orden - Producto del producto seleccionado
        OrderprodDAO orderproddao = new OrderprodDAO();
        OrderprodDTO orderproddto = new OrderprodDTO();

        orderproddto.getEntidad().setFk_order(dtoOrderTable.getEntidad());

        // Recuperar el producto seleccionado
        ProductDAO prodDao = new ProductDAO();
        ProductDTO prodDto = new ProductDTO();

        prodDto.setEntidad(this.product);
        prodDto = prodDao.read(prodDto);

        orderproddto.getEntidad().setFk_product(prodDto.getEntidad());
        orderproddto.getEntidad().setCantidad(1);

        // Crear la relacion
        orderproddao.create(orderproddto);
        return SUCCESS;
    }
    
    public String EliminarProducto(){
        
        String estado = ERROR;
                
        OrderprodDTO dto = new OrderprodDTO();
        dto.setEntidad(this.order_prod);
        try {
            this.opda.delete(dto);
            estado = SUCCESS;
        } catch (Exception e) {
        }
        return estado;
    }
}
