/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.sise.logic;

import com.edu.sise.dao.CompraDao;
import com.edu.sise.dao.ProductosDao;
import com.edu.sise.dao.ProveedorDao;
import com.edu.sise.entidades.Compra;
import com.edu.sise.entidades.Productos;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author CLIENTE
 */
public class ProductosLogic {
    List<Productos> lista;

    public DefaultTableModel getModelo(){
        //cargarLista();
        
        lista = new ProductosDao().getAll();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
   modelo.addColumn("ID");
     modelo.addColumn("NOMBRE");
   modelo.addColumn("CATEGORIA");
   modelo.addColumn("STOCK");
   modelo.addColumn("PRECIO");

  
   for(Productos obj : lista){
       Object data[]=
       {
       obj.getId_prod(),
           obj.getNombre(),
           obj.getCategoria(),
           obj.getStock(),
           obj.getPrecio()
     
       
       
       };
   
       modelo.addRow(data);
   }
    
    return modelo;
}

    
    
      public DefaultTableModel getModelo(String nombre){
        //cargarLista();
        
        lista = new ProductosDao().getSearch(nombre);
        
        DefaultTableModel modelo = new DefaultTableModel();
   modelo.addColumn("ID");
     modelo.addColumn("NOMBRE");
   modelo.addColumn("CATEGORIA");
   modelo.addColumn("STOCK");
   modelo.addColumn("PRECIO");
  
   for(Productos obj : lista){
       Object data[]=
       {
     obj.getId_prod(),
           obj.getNombre(),
           obj.getCategoria(),
           obj.getStock(),
           obj.getPrecio()
       
       
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

 public boolean agegarProductos(Productos o){
     return new ProductosDao().agegarProductos(o);
 }

 
 public boolean modificarProductos(Productos o){
     return new ProductosDao().modificarProductos(o);
 }

 
 public boolean eliminarProductos(Integer id){
     return new ProductosDao().eliminarProductos(id);
 }

 
 public void generarReporte(){
        JasperReport reporte;
        
        //Necesitamos el Jasper
        String ruta = "C:\\Users\\CLIENTE\\Documents\\JASPER PROYECTO FINAL-JAV\\Prod_PFinal-Jav.jasper";
        
        try {
            reporte = (JasperReport)JRLoader.loadObjectFromFile(ruta);         
            
            
            JasperPrint jprint = JasperFillManager.fillReport(reporte,null,
                    new JRBeanCollectionDataSource(new ProductosDao().obtenerTodos()));
            JasperViewer jViewer = new JasperViewer(jprint,false);
            jViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jViewer.setVisible(true);
                    } catch (Exception e) {
            System.out.println("Error JR: " + e.getMessage());
                    }
    }
    

 
 //crear metodo para la  carga masiva
public int cargaMasiva(String rutaArchivo){
    int registros_afectados=0;
        try {
            FileInputStream archivo = new FileInputStream(rutaArchivo);
            DataInputStream entrada = new DataInputStream(archivo);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String linea;
            List<Productos> ListaCargaMa = new ArrayList<>();
            
            while((linea=buffer.readLine())!=null){
                System.out.println(linea);
                
                String[] campos = linea.split("\\|");
                ListaCargaMa.add(new Productos(
                
                -1,
                        campos[0],//dni
                        campos[1],//nombre
                        Integer.parseInt(campos[2]),//P apellido
                        Double.parseDouble(campos[3])//S apellido    
                ));
            }
           
             entrada.close();
            //agregar la logica para interactuar con BD
            
            int[] resultados =new ProductosDao().cargaMasiva(ListaCargaMa);
            for(int i=0; i<resultados.length;i++)
                registros_afectados += resultados[i];
            
        } catch (Exception ex) {
            System.out.println("Error Carga Masiva: " + ex.getMessage());
        }
        
        return registros_afectados;
    }

 



 public void llenarComboProductos(JComboBox cbo){
        lista = new ProductosDao().getAll();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        
         for(Productos obj : lista){
             modelo.addElement(obj);
         }
         
         //actualiza la información en el combo
         cbo.setModel(modelo);
    }
 
 
public void seleccionarItemCbo(JComboBox cbo, Integer id){
        DefaultComboBoxModel modelo = (DefaultComboBoxModel)cbo.getModel();
        
        for(int i=0; i<modelo.getSize();i++){
            if(((Compra)modelo.getElementAt(i)).getId_producto()==id){
                modelo.setSelectedItem(modelo.getElementAt(i));
            }
        }
    }






}