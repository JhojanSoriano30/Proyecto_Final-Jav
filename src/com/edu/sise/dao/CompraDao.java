/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.dao;

import com.edu.sise.entidades.Compra;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.Date;
import javax.accessibility.AccessibleState;


/**
 *
 * @author CLIENTE
 */
public class CompraDao {
    
    Connection cn = null;
    
    Statement st = null;
    ResultSet rs = null;
    
    CallableStatement cs = null;
    
    final String INSERTAR = "{call pa_insertar_compra(?,?,?,?,?)}";
    final String MODIFICAR = "{call pa_modificar_compra(?,?,?,?,?,?)}";
    final String ELIMINAR = "{call pa_eliminar_compra(?)}";
    final String TODOS = "{call pa_listar_compra()}";
    final String FILTRO = "{call pa_buscar_compra(?)}";
    
    public CompraDao(){
        
        cn = new Conexion().getConn();
       

        
    }
 
    public List<Compra> getAll(){
        List<Compra> lista = new ArrayList<>();
       // String sql = "{call pa_listar_tutores()}";
        
        try {
            //st = cn.createStatement();
            cs=(CallableStatement) cn.prepareCall(TODOS);
            rs = cs.executeQuery();
            while(rs.next()){
                lista.add(new Compra(
                rs.getInt("id_compra"),
                        rs.getString("cliente"),
                        rs.getString("producto"),
                        rs.getString("categoria"),
                        rs.getDouble("precio"),
                        rs.getInt("id_producto"),
                        rs.getInt("cantidad")));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en SQL: " + ex.getMessage());
        }
       return lista;
    }
   
    
    public boolean agegarCompra(Compra o){
        
        int cantidad=0;
        //cresr scrip
       // String sql ="{call pa_insertar_tutores(?,?,?,?,?,?,?)}";
       try{ 
        cs = cn.prepareCall(INSERTAR);
        int x=1;
        cs.setString(x++, o.getCliente());
        cs.setString(x++, o.getProducto());
        cs.setString(x++, o.getCategoria());
        cs.setDouble(x++, o.getPrecio());
        cs.setInt(x++, o.getId_producto());
        cs.setInt(x++, o.getCantidad());
      
       cantidad = cs.executeUpdate();
        
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
    
    
    
    
    
    
     public List<Compra> getSearch(String nombre){
        List<Compra> lista = new ArrayList<>();
       // String sql = "{call pa_buscar_tutores(?)}";
        
        try {
            cs=cn.prepareCall(FILTRO);
          cs.setString(1, nombre);
          rs=cs.executeQuery();
        while(rs.next()){
                lista.add(new Compra(
             rs.getInt("id_compra"),
                        rs.getString("cliente"),
                        rs.getString("producto"),
                        rs.getString("categoria"),
                        rs.getDouble("precio"),
                        rs.getInt("id_producto"),
                        rs.getInt("cantidad")));
                
            
            }
        

     } catch (SQLException ex) {
            System.out.println("Error en SQL: " + ex.getMessage());
        }
       return lista;
    }
    
    
    
    
    
    
    
     public boolean modificarCompra(Compra o){
        
        int cantidad=0;
        //cresr scrip
        //String sql ="{call pa_modificar_tutor(?,?,?,?,?,?,?,?)}";
       try{ 
         cs = cn.prepareCall(MODIFICAR);
        int x=1;
      cs.setString(x++, o.getCliente());
        cs.setString(x++, o.getProducto());
        cs.setString(x++, o.getCategoria());
        cs.setDouble(x++, o.getPrecio());
        cs.setInt(x++, o.getId_producto());   
        cs.setInt(x++, o.getId_compra());
         cs.setInt(x++, o.getCantidad());
      
       cantidad = cs.executeUpdate();
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
    
    
    
     
     public boolean eliminarCompra(Integer id){
        
        int cantidad=0;
       try{ 
        cs= cn.prepareCall(ELIMINAR);
        cs.setInt(1, id);
        cantidad = cs.executeUpdate();
        
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
     
     
     public int[] cargaMasiva(List<Compra> lista)
     {
         
         int[] rpta = null;
         
         try {
             cs=cn.prepareCall(INSERTAR);
         for(Compra o: lista){
             
               int x=1;
       cs.setString(x++, o.getCliente());
        cs.setString(x++, o.getProducto());
        cs.setString(x++, o.getCategoria());
        cs.setDouble(x++, o.getPrecio());
        cs.setInt(x++, o.getId_producto());
        cs.setInt(x++, o.getCantidad());
        cs.addBatch();
             
             
         }    
             
         rpta= cs.executeBatch();
             
             
         
          } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
          
         return rpta;
         
     }
     
     
     
     
    
}
