/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.dao;
import com.edu.sise.entidades.Productos;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.Date;


/**
 *
 * @author CLIENTE
 */
public class ProductosDao {
    
    Connection cn = null;
    
    Statement st = null;
    ResultSet rs = null;
    
    CallableStatement cs = null;
    public ProductosDao(){
        
        cn = new Conexion().getConn();
        
        
    }
 
    public List<Productos> getAll(){
        List<Productos> lista = new ArrayList<>();
        String sql = "{call pa_listar_productos()}";
        
        try {
            //st = cn.createStatement();
            cs=(CallableStatement) cn.prepareCall(sql);
            rs = cs.executeQuery();
            while(rs.next()){
                lista.add(new Productos(
                rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getInt("stock"),
                        rs.getDouble("precio")
                     ));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error en SQL: " + ex.getMessage());
        }
       return lista;
    }
   
    
    public boolean agegarProductos(Productos o){
        
        int cantidad=0;
        //cresr scrip
        String sql ="{call pa_insertar_productos(?,?,?,?)}";
       try{ 
        cs = cn.prepareCall(sql);
        int x=1;
        cs.setString(x++, o.getNombre());
        cs.setString(x++, o.getCategoria());
        cs.setInt(x++, o.getStock());
        cs.setDouble(x++, o.getPrecio());
      
       cantidad = cs.executeUpdate();
        
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
    
    
    
    
    
    
     public List<Productos> getSearch(String nombre){
        List<Productos> lista = new ArrayList<>();
        String sql = "{call pa_buscar_producto(?)}";
        
        try {
            cs=cn.prepareCall(sql);
          cs.setString(1, nombre);
          rs=cs.executeQuery();
        while(rs.next()){
               lista.add(new Productos(
                rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getInt("stock"),
                        rs.getDouble("precio")
                     ));
            }
        

     } catch (SQLException ex) {
            System.out.println("Error en SQL: " + ex.getMessage());
        }
       return lista;
    }
    
    
    
    
    
    
    
     public boolean modificarProductos(Productos o){
        
        int cantidad=0;
        //cresr scrip
        String sql ="{call pa_modificar_producto(?,?,?,?,?)}";
       try{ 
         cs = cn.prepareCall(sql);
        int x=1;
        cs.setString(x++, o.getNombre());
        cs.setString(x++, o.getCategoria());
        cs.setInt(x++, o.getStock());
        cs.setDouble(x++, o.getPrecio());
        cs.setInt(x++, o.getId_prod());
      
       cantidad = cs.executeUpdate();
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
    
    
    
     
     public boolean eliminarProductos(Integer id){
        
        int cantidad=0;
        //cresr scrip
        String sql ="{call pa_eliminar_producto(?)}";
       try{ 
        cs= cn.prepareCall(sql);
        cs.setInt(1, id);
        cantidad = cs.executeUpdate();
        
       } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
       
       return (cantidad > 0);
    }
     
     
     public int[] cargaMasiva(List<Productos> lista)
     {
         
         int[] rpta = null;
          String sql ="{call pa_insertar_productos(?,?,?,?)}";
         
         try {
             cs=cn.prepareCall(sql);
         for(Productos o: lista){
             
               int x=1;
       cs.setString(x++, o.getNombre());
        cs.setString(x++, o.getCategoria());
        cs.setInt(x++, o.getStock());
        cs.setDouble(x++, o.getPrecio());
        cs.addBatch();
             
             
         }    
             
         rpta= cs.executeBatch();
             
             
         
          } catch (Exception ex){
           System.out.println("Error SQL: " + ex.getMessage());
       }
          
         return rpta;
         
     }
     
     
     
     
     public List<Productos> obtenerTodos(){
        
        //crar la sentencia sql q quiero ejecutar
        String sql = "{call pa_listar_productos()}";
        List<Productos> lista = new ArrayList<>();
        
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
                lista.add(new Productos(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getInt("stock"),
                        rs.getDouble("precio")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("ProductosDao: "+ex.getMessage());
        }
        return lista;
    }
     
     
     
    
}
