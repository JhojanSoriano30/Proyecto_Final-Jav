/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.sise.dao;


import com.edu.sise.entidades.Proveedor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author david
 */
public class ProveedorDao {
    
    //atributos
    private Connection cn = null;
    private Statement st = null;//nos permite ejecutar sencuencias sql contra BD
    private ResultSet rs = null;//nos permite tener una tabla virtual
   // private PreparedStatement ps = null;
    private CallableStatement cs = null;
    
    
    //constructor
    //constructor por defecto
    public ProveedorDao(){
        //permite establecer la conexion a BD por medio de mi clase conexion.java
        //sucede cuando creamos un nuevo objeto de la clase departamento
        cn = new Conexion().getConn();
    }
    
    public List<Proveedor> obtenerTodos(){
        
        //crar la sentencia sql q quiero ejecutar
        String sql = "{call pa_listar_proveedores()}";
        List<Proveedor> lista = new ArrayList<>();
        
        try {
            //codigo para ejecutar la sentencia en la BD
            
            //establezco la conexion con el state...

            //st = cn.createStatement();
            //ps = cn.prepareStatement(sql);
            cs = cn.prepareCall(sql);
            //rs = ps.executeQuery();
            rs = cs.executeQuery();
            //llenar la lista
            while(rs.next()){//condicion para determinar si existe un sgte dato
                lista.add(new Proveedor(
                        rs.getInt("id_proveedor"),
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("num_boletas_entregados"),
                        rs.getInt("num_prod_entregados"),
                        rs.getInt("horas_trabajo"),
                        rs.getDouble("sueldo")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("ProveedorDao: "+ex.getMessage());
        }
        return lista;
    }
    
    
    
    
    
     public List<Proveedor> busqueda(String nombre){
        
         
         
         
         
         List<Proveedor> lista = new ArrayList<>();
        //crear el sentencia SQL que quiero ejecutar
        String sql ="{call pa_buscar_proveedores(?)}";

        
        try {
           cs=cn.prepareCall(sql);
          cs.setString(1, nombre);
          rs=cs.executeQuery();
            while(rs.next()){  //condición para determinar si existe un siguiente dato
                lista.add(new Proveedor(
                        rs.getInt("id_proveedor"),
                        rs.getString("dni"), 
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("num_boletas_entregados"),
                rs.getInt("num_prod_entregados"),
                        rs.getInt("horas_trabajo"),
                        rs.getDouble("sueldo")));
            }
        } catch (SQLException ex) {
            System.out.println("ProveedorDao: " + ex.getMessage());
        }
        return lista;
    }
     
     
     
     
     
     
     
    
    public boolean agregarProveedor(Proveedor o)
    {String sql = "{call pa_insertar_proveedores(?,?,?,?,?,?,?)}";
    //representar la cantidad de registros afectados
    int c =-1;
    
    try {
        //st = cn.createStatement();
        //ps = cn.prepareStatement(sql);
        cs = cn.prepareCall(sql);
        //ps.setString(1, o.getNombre());
        int x = 1;
        cs.setString(x++, o.getDni());
        cs.setString(x++, o.getNombre());
        cs.setString(x++, o.getApellido());
        cs.setInt(x++, o.getNum_boletas_entregados());
        cs.setInt(x++, o.getNum_prod_entregados());
        cs.setInt(x++, o.getHoras_trabajo());
        cs.setDouble(x++, o.getSueldo());
        //c = ps.executeUpdate(sql);
        //c = ps.executeUpdate();
        c = cs.executeUpdate();
    } catch (Exception e) {
        System.out.println("ProveedorDao: " + e.getMessage());
    }
    return (c>0);
    }
    public boolean modificarProveedor(Proveedor o){
        //String sql = "update carreras set nombre = '"+o.getNombre()+"' where id_depa = "+o.getId_carrera();
        String sql = "{call pa_modificar_proveedores(?,?,?,?,?,?,?,?)}";
    //representar la cantidad de registros afectados
    int c =-1;
    try {
        //st = cn.createStatement();
        cs = cn.prepareCall(sql);
        //ps.setString(1, o.getNombre());
        int x = 1;
        cs.setString(x++, o.getDni());
        cs.setString(x++, o.getNombre());
        cs.setString(x++, o.getApellido());
        cs.setInt(x++, o.getNum_boletas_entregados());
        cs.setInt(x++, o.getNum_prod_entregados());
        cs.setInt(x++, o.getHoras_trabajo());
        cs.setDouble(x++, o.getSueldo());
        cs.setInt(x++, o.getId_proveedor());
        //c = ps.executeUpdate(sql);
        //c = ps.executeUpdate();
        c = cs.executeUpdate();
    } catch (Exception e) {
        System.out.println("ProveedorDao: " + e.getMessage());
    }
    return (c>0);
    }
    public boolean eliminarProveedor(Integer id){
        String sql = "{call pa_eliminar_proveedores(?)}";
    //representar la cantidad de registros afectados
    int c =-1;
    try {
        //st = cn.createStatement();
        //ps = cn.prepareStatement(sql);
        //ps.setInt(1, id);
        cs = cn.prepareCall(sql);
        cs.setInt(1, id);
        //c = stc = ps.executeUpdate();.executeUpdate(sql);
        //c = ps.executeUpdate();
        c = cs.executeUpdate();
    } catch (Exception e) {
        System.out.println("ProveedorDao: " + e.getMessage());
    }
    return (c>0);
    }
}
