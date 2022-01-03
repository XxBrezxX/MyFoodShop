/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.action;

import antlr.debug.Event;
import com.ipn.mx.modelo.dao.ClientDAO;
import com.ipn.mx.modelo.dao.ClientaddressDAO;
import com.ipn.mx.modelo.dao.DeliveryDAO;
import com.ipn.mx.modelo.dao.OrderprodDAO;
import com.ipn.mx.modelo.dao.OrdertableDAO;
import com.ipn.mx.modelo.dao.ProductDAO;
import com.ipn.mx.modelo.dto.ClientDTO;
import com.ipn.mx.modelo.dto.ClientaddressDTO;
import com.ipn.mx.modelo.dto.DeliveryDTO;
import com.ipn.mx.modelo.dto.OrderprodDTO;
import com.ipn.mx.modelo.dto.OrdertableDTO;
import com.ipn.mx.modelo.dto.ProductDTO;
import com.ipn.mx.modelo.entidades.Order_prod;
import com.ipn.mx.modelo.entidades.Ordertable;
import com.ipn.mx.modelo.entidades.Product;
import com.ipn.mx.utilerias.EnviarMail;
import com.ipn.mx.utilerias.Singleton;
import java.util.Locale;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

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
    private HashMap reportParams = new HashMap();
    private List<Map<String, String>> filas = new ArrayList<>();

    private final ProductDAO dao = new ProductDAO();
    private final OrderprodDAO opda = new OrderprodDAO();

    public List getFilas() {
        return filas;
    }

    public void setFilas(List filas) {
        this.filas = filas;
    }
    
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

    public HashMap getReportParams() {
        return reportParams;
    }

    public void setReportParams(HashMap reportParams) {
        this.reportParams = reportParams;
    }

    public String execute() {
        return SUCCESS;
    }

    public String Start() {
        return SUCCESS;
    }

    public String Inicio() {
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        System.out.println(session.getAttribute("dtoU"));
        ClientDTO aux = new ClientDTO();
        ClientDAO aux1 = new ClientDAO();
        int id = (int) session.getAttribute("dtoU");
        aux.getEntidad().setIdclient(id);
        this.dtoClient = aux1.read(aux);
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
        String estado = SUCCESS;
        this.carrito = new ArrayList<>();

        HttpSession session = ServletActionContext.getRequest().getSession(false);
        ClientDTO aux = new ClientDTO();
        ClientDAO aux1 = new ClientDAO();
        int id = (int) session.getAttribute("dtoU");
        aux.getEntidad().setIdclient(id);
        this.dtoClient = aux1.read(aux);

        ClientaddressDAO daoAdd = new ClientaddressDAO();
        this.dtoClientAdd = daoAdd.giveAddresses(this.dtoClient);

        // Buscar la orden actual del usuario
        OrdertableDAO otda = new OrdertableDAO();
        OrdertableDTO otdt = new OrdertableDTO();

        otdt = otda.giveAddresses(this.dtoClient);

        // Obtener listado de articulos en la orden
        OrderprodDAO opda = new OrderprodDAO();

        if(otdt != null)
            this.carrito = opda.getShopWheel(otdt); 
        else
            this.carrito = new ArrayList<>();

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
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        System.out.println(session.getAttribute("dtoU"));
        ClientDTO aux = new ClientDTO();
        ClientDAO aux1 = new ClientDAO();
        int id = (int) session.getAttribute("dtoU");
        aux.getEntidad().setIdclient(id);
        this.dtoClient = aux1.read(aux);

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

    public String EliminarProducto() {

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

    public String viewShortReport() {
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        System.out.println(session.getAttribute("dtoU"));
        int id = (int) session.getAttribute("dtoU");
        
        this.reportParams.put("vid", id);
        return SUCCESS;
    }

    public String RealizarPedido() {
        String estado = SUCCESS;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        System.out.println(session.getAttribute("dtoU"));
        ClientDTO aux = new ClientDTO();
        ClientDAO aux1 = new ClientDAO();
        int id = (int) session.getAttribute("dtoU");
        aux.getEntidad().setIdclient(id);
        this.dtoClient = aux1.read(aux);

        OrdertableDAO otda = new OrdertableDAO();
        OrdertableDTO otdt = new OrdertableDTO();

        otdt = otda.giveAddresses(this.dtoClient);

        DeliveryDAO dda = new DeliveryDAO();
        List<DeliveryDTO> resultados = dda.readAll();

        Random r = new Random();
        int indice = r.nextInt(resultados.size() - 0) + 0;

        otdt.getEntidad().setFk_delivery(resultados.get(indice).getEntidad());
        otdt.getEntidad().setIsdelivered(true);

        otda.update(otdt);

        EnviarMail em = new EnviarMail();
        em.enviarCorreo(
                this.dtoClient.getEntidad().getCorreo(),
                "Lotus Team Sender",
                "Los articulos se han enviado, en caso de existir un error comunicarse con soporte al cliente."
        );

        return estado;
    }
}
