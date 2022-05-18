/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.dao;

import com.edu.sise.entidades.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class ClienteDao {
    
    //atributos
    Connection cn = null;
    Statement st =null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    public ClienteDao(){
        cn = new Conexion().getConn();
    }
    
    private Cliente getRS(ResultSet record) throws SQLException{
        return new Cliente(
                        record.getInt("id_cliente"),
                        record.getInt("DNI"),
                        record.getString("Apellido"),
                        record.getString("Nombre"),
                        record.getString("Sexo"));                   
    }
    
    public List<Cliente> getAll(){
        
        List<Cliente> lista = new ArrayList<>();
        String sql = "select * from cliente";
        try {
            //st = cn.createStatement();
            //rs = st.executeQuery(sql);
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) lista.add(getRS(rs));
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
        }
        return lista;
    }
    //metodo de busqueda por nombre
    public List<Cliente> getSearch(String nombre){
        
        List<Cliente> lista = new ArrayList<>();
        String sql = "select * from carreras where nombre like ?";
        try {
            //st = cn.createStatement();
            //rs = st.executeQuery(sql);
            ps = cn.prepareStatement(sql);
            ps.setString(1, "%"+nombre+"%");
            rs = ps.executeQuery();
            while(rs.next()) lista.add(getRS(rs));
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
        }
        return lista;
   }
    
    //metodo para agregar un registro en base de datos
    public boolean agregarCliente(Cliente o){
        
        int cantidad = 0;
        //crear nuestro script de agregar
        String sql ="insert into cliente(DNI,Nombre,Apellido,Sexo) values(?,?,?,?)";
        
        try{
//            st = cn.createStatement();
//            cantidad = st.executeUpdate(sql);
            ps = cn.prepareStatement(sql);
            ps.setInt(1, o.getDNI());
            ps.setString(2, o.getNombre());
            ps.setString(3, o.getApellido());
            ps.setString(4, o.getSexo());
            
            cantidad = ps.executeUpdate();
        }catch(Exception ex){
             System.out.println("Error SQL: " + ex.getMessage());
        }  
        return (cantidad>0);
    }
    
    //metodo para modificar un registro en base de datos
    public boolean modificarCliente(Cliente o){
        int cantidad = 0;
        //crear nuestro script de modificar
        String sql ="update cliente set DNI = ?,Nombre = ?,Apellido = ?,Sexo = ? where id_cliente = ?,?,?,? " ;
        
        try{
//            st = cn.createStatement();
//            cantidad = st.executeUpdate(sql);
              ps = cn.prepareStatement(sql);
              ps.setInt(1, o.getDNI());
              ps.setString(2, o.getNombre());
              ps.setString(3, o.getApellido());
              ps.setString(4, o.getSexo());
              ps.setInt(5, o.getId_cliente());
              
              cantidad = ps.executeUpdate();
        }catch(Exception ex){
             System.out.println("Error SQL: " + ex.getMessage());
        }      
        return (cantidad>0);
    }
    //metodo para eliminar un registro en base de datos
    public boolean eliminarCliente(Integer id){
       int cantidad = 0;
        //crear nuestro script de eliminar
        String sql ="delete from cliente where id_cliente = ?";
        
        try{
//            st = cn.createStatement();
//            cantidad = st.executeUpdate(sql);
              ps = cn.prepareStatement(sql);
              ps.setInt(1, id);
              cantidad = ps.executeUpdate();
        }catch(Exception ex){
             System.out.println("Error SQL: " + ex.getMessage());
        }  
        return (cantidad>0);
    }
}
