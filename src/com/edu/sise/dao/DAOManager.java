
package com.edu.sise.dao;

public class DAOManager {
    
    //atributos para Singleton --inicio
    private static final DAOManager instancia = new DAOManager();
    
    
    
    public static DAOManager getInstancia(){
        return instancia;
    }
    //fin de Singleton
    
    //inicio de factory--matricular la clase DAO que quieran utilizar
    
    
    private ProductosDao productDao;
    private ProveedorDao proveedorDao;
    private CompraDao compraDao;

    
    
    //get para validar si clase esta creada en memoria
    public ProductosDao getProductoDao(){
        if(productDao==null)
            productDao=new ProductosDao();
        return productDao;
        
    }
    
    
    
      public ProveedorDao getDepartamentoDao(){
        if(proveedorDao==null)
            proveedorDao=new ProveedorDao();
        return proveedorDao;
        
    }
      public CompraDao getCompraDao(){
        if(compraDao==null)
            compraDao=new CompraDao();
        return compraDao;
        
    }
    
    
    
    
       
    
}
