/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edu.sise.logic;

import com.edu.sise.dao.ProveedorDao;
import com.edu.sise.entidades.Proveedor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JComboBox;
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
 * @author david
 */
public class ProveedorLogic {
    
    List<Proveedor> lista;
    
    public ProveedorLogic(){
        
        lista = new ArrayList<>();
    }
    public DefaultTableModel getModelo(){
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        //crear las columnas
        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("NUM_BOLETAS_ENTREGADOS");
        modelo.addColumn("NUM_PROD_ENTREGADOS");
        modelo.addColumn("HORAS_TRABAJO");
        modelo.addColumn("SUELDO");
        
//        cargaData();//obtener desde base de datos

        lista = new ProveedorDao().obtenerTodos();
        
        //llenar las filas
        
        for(Proveedor obj : lista){
            Object data[]={
                obj.getId_proveedor(),
                obj.getDni(),
                obj.getNombre(),
                obj.getApellido(),
                obj.getNum_boletas_entregados(),
                obj.getNum_prod_entregados(),
                obj.getHoras_trabajo(),
                obj.getSueldo()
            };
            modelo.addRow(data);
        }
        return modelo;
    }
    
    
    public DefaultTableModel getModelo(String nombre){
         DefaultTableModel modelo = new DefaultTableModel();
         
         //crear las columnas
         modelo.addColumn("ID");
         modelo.addColumn("DNI");
         modelo.addColumn("NOMBRE");
         modelo.addColumn("APELLIDO");
         modelo.addColumn("NUM_BOLETAS_ENTREGADOS");
         modelo.addColumn("NUM_PROD_ENTREGADOS");
         modelo.addColumn("HORAS_TRABAJO");
         modelo.addColumn("SUELDO");
         
//         cargarData(); //obtener desde base de datos
        lista = new ProveedorDao().busqueda(nombre);
         
         //llenar las filas
         
         for(Proveedor obj : lista){
             Object data[] ={
                 obj.getId_proveedor(),
                 obj.getDni(),
                 obj.getNombre(),
                 obj.getApellido(),
                 obj.getNum_boletas_entregados(),
                 obj.getNum_prod_entregados(),
                 obj.getHoras_trabajo(),
                 obj.getSueldo()            
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
    
    public boolean agregarProveedor(Proveedor o){
        return new ProveedorDao().agregarProveedor(o);
    }
    public boolean modificarProveedor(Proveedor o){
        return new ProveedorDao().modificarProveedor(o);
    }
    public boolean eliminarProveedor(Integer id){
        return new ProveedorDao().eliminarProveedor(id);
    }
    public void llenarComboProveedors(JComboBox cbo){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        
        lista = new ProveedorDao().obtenerTodos();
        for(Proveedor obj: lista){
            modelo.addElement(obj);
        }
        cbo.setModel(modelo);
    }
    public void seleccionarCboProveedores(JComboBox cbo,Integer id){
        
        DefaultComboBoxModel modelo = (DefaultComboBoxModel)cbo.getModel();
        for(int i=0;i<modelo.getSize();i++){
            if(((Proveedor)modelo.getElementAt(i)).getId_proveedor()==id)
                modelo.setSelectedItem((Proveedor)modelo.getElementAt(i));
        }
        
    }
    
    
    
    
    
    
     public void generarReporte(){
        JasperReport reporte;
        
        //Necesitamos el Jasper
        String ruta = "C:\\Users\\CLIENTE\\Documents\\JASPER PROYECTO FINAL-JAV\\rp_Proveedor.jasper";
        
        try {
            reporte = (JasperReport)JRLoader.loadObjectFromFile(ruta);         
            
            
            JasperPrint jprint = JasperFillManager.fillReport(reporte,null,
                    new JRBeanCollectionDataSource(new ProveedorDao().obtenerTodos()));
            JasperViewer jViewer = new JasperViewer(jprint,false);
            jViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jViewer.setVisible(true);
                    } catch (Exception e) {
            System.out.println("Error JR: " + e.getMessage());
                    }
    }
    
    
    
    
    
}
