/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.sise.entidades;

/**
 *
 * @author david
 */
public class Proveedor {
    
    private Integer id_proveedor;
    private String dni;
    private String nombre;
    private String apellido;
    private Integer num_boletas_entregados;
    private Integer num_prod_entregados;
    private Integer horas_trabajo;
    private Double sueldo;

    public Proveedor(Integer id_proveedor, String dni, String nombre, String apellido, Integer num_boletas_entregados, Integer num_prod_entregados, Integer horas_trabajo, Double sueldo) {
        this.id_proveedor = id_proveedor;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.num_boletas_entregados = num_boletas_entregados;
        this.num_prod_entregados = num_prod_entregados;
        this.horas_trabajo = horas_trabajo;
        this.sueldo = sueldo;
    }

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getNum_boletas_entregados() {
        return num_boletas_entregados;
    }

    public void setNum_boletas_entregados(Integer num_boletas_entregados) {
        this.num_boletas_entregados = num_boletas_entregados;
    }

    public Integer getNum_prod_entregados() {
        return num_prod_entregados;
    }

    public void setNum_prod_entregados(Integer num_prod_entregados) {
        this.num_prod_entregados = num_prod_entregados;
    }

    public Integer getHoras_trabajo() {
        return horas_trabajo;
    }

    public void setHoras_trabajo(Integer horas_trabajo) {
        this.horas_trabajo = horas_trabajo;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }
    
    
}
