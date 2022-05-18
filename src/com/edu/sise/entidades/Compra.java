
package com.edu.sise.entidades;

public class Compra {
    
    Integer id_compra;
    String cliente;
    String categoria;
    String producto;
    Double precio;
    Integer cantidad;
Integer id_producto;

    public Compra(Integer id_compra, String cliente, String categoria, String producto, Double precio, Integer cantidad, Integer id_producto) {
        this.id_compra = id_compra;
        this.cliente = cliente;
        this.categoria = categoria;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.id_producto = id_producto;
    }

    public Integer getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }
   
     @Override
    public String toString() {
        return id_compra + " - "+ cliente;
    }
    //prueba
}
