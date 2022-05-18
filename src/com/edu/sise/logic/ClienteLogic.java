/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.logic;

import com.edu.sise.dao.ClienteDao;
import com.edu.sise.entidades.Cliente;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClienteLogic {
    List<Cliente> lista;

    public DefaultTableModel getModelo(){
        //cargarLista();      
        lista = new ClienteDao().getAll();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("SEXO");
        
        for(Cliente obj : lista){
             Object data[]={
           obj.getId_cliente(),
           obj.getDNI(),
           obj.getNombre(),
           obj.getApellido(),
           obj.getSexo() 
       };
       
       modelo.addRow(data);
   }
    
    return modelo;

}
  public DefaultTableModel getModelo(String nombre){
        //cargarLista();
        lista = new ClienteDao().getSearch(nombre);
        DefaultTableModel modelo = new DefaultTableModel();
        //crear mis columnas
        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("SEXO");
        for(Cliente obj : lista){
            Object data[] ={
                obj.getId_cliente(),
                obj.getDNI(),
                obj.getNombre(),
                obj.getApellido(),
                obj.getSexo()
            };
            
            modelo.addRow(data);
        }
        return modelo;
    }


    public void imprimir(JTable tabla){
        tabla.setModel(getModelo());
    }
    public void imprimir(JTable tabla, String nombre){
        tabla.setModel(getModelo(nombre));
    }
    public boolean agregarCliente(Cliente o){
        return new ClienteDao().agregarCliente(o);
    }
    
    public boolean modificarCliente(Cliente o){
        return new ClienteDao().modificarCliente(o);
    }
    
    public boolean eliminarCliente(Integer id){
        return new ClienteDao().eliminarCliente(id);
    }
    public void llenarComboCliente(JComboBox cbo){
        lista = new ClienteDao().getAll();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        
         for(Cliente obj : lista){
             modelo.addElement(obj);
         }       
         //actualiza la información en el combo
         cbo.setModel(modelo);
    }   
    public void seleccionarItemCbo(JComboBox cbo, String Sexo){
        DefaultComboBoxModel modelo = (DefaultComboBoxModel)cbo.getModel();
        
        for(int i=0; i<modelo.getSize();i++){
            if(((Cliente)modelo.getElementAt(i)).getSexo()==Sexo){
                modelo.setSelectedItem(modelo.getElementAt(i));
            }
        }
    }
}
