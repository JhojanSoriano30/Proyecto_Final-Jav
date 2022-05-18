/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.logic;

import com.edu.sise.dao.DAOManager;
import com.edu.sise.dao.CompraDao;
import com.edu.sise.entidades.Compra;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CLIENTE
 */
public class CompraLogic {
    List<Compra> lista;
CompraDao dao = DAOManager.getInstancia().getCompraDao();
   


public DefaultTableModel getModelo(){
        //cargarLista();
        
        //lista = dao.getAll();
        
        
        lista = DAOManager.getInstancia().getCompraDao().getAll();
        DefaultTableModel modelo = new DefaultTableModel();
        
   modelo.addColumn("ID_COMPRA");
   modelo.addColumn("CLIENTE");
     modelo.addColumn("PRODUCTO");
   modelo.addColumn("CATEGORÍA");
   modelo.addColumn("PRECIO");
   modelo.addColumn("ID_PRODUCTO");
   modelo.addColumn("CANTIDAD");
  
   for(Compra obj : lista){
       Object data[]=
       {
       obj.getId_compra(),
           obj.getCliente(),
             obj.getProducto(),
           obj.getCategoria(),
           obj.getPrecio(),
           obj.getId_producto(),
           obj.getCantidad()
       
       
       };
   
       modelo.addRow(data);
   }
    
    return modelo;
}

    
    
      public DefaultTableModel getModelo(String nombre){
        //cargarLista();
        
        lista =dao.getSearch(nombre);
        
        DefaultTableModel modelo = new DefaultTableModel();
modelo.addColumn("ID_COMPRA");
   modelo.addColumn("CLIENTE");
     modelo.addColumn("PRODUCTO");
   modelo.addColumn("CATEGORÍA");
   modelo.addColumn("PRECIO");
  // modelo.addColumn("ID_PRODUCTO");
   modelo.addColumn("CANTIDAD");
  
   for(Compra obj : lista){
       Object data[]=
       {
       obj.getId_compra(),
           obj.getCliente(),
             obj.getProducto(),
           obj.getCategoria(),
           obj.getPrecio(),
          // obj.getId_producto(),
           obj.getCantidad()
       
       
       };
   
       modelo.addRow(data);
   }
    
    return modelo;
}

    
      
      
      


public void imprimir(JTable tabla, String nombre){
    tabla.setModel(getModelo(nombre));
}

public void imprimir(JTable tabla){
    tabla.setModel(getModelo());
}

 public boolean agegarCompra(Compra o){
     return dao.agegarCompra(o);
 }

 
 public boolean modificarCompra(Compra o){
     return dao.modificarCompra(o);
 }

 
 public boolean eliminarCompra(Integer id){
     return dao.eliminarCompra(id);
 }


 
 
}