package pooco.modelo;

import pooco.persistencia.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import java.time.*;


public class Datos {
    private Articulo articulo;
    private Cliente cliente;
    private Clienteestandard clienteStd;
    private Clientepremium clientePrm;
    private Pedido pedido;  
   
    
    private ArticuloJpaController articuloJPA= new ArticuloJpaController();
    private ClienteJpaController clienteJPA = new ClienteJpaController();
    private ClienteestandardJpaController clienteSTDJPA = new ClienteestandardJpaController();
    private ClientepremiumJpaController clientePRMJPA = new ClientepremiumJpaController();
    private PedidoJpaController pedidoJPA = new PedidoJpaController();
    
    public boolean setArticulo(String codigo, String descripcion, float pvpVenta, float gastosEnviom, int tiempoPreparacion)
    {         
        articulo=new Articulo(codigo,descripcion,pvpVenta,gastosEnviom,tiempoPreparacion);
        return setArticuloJPA(articulo);        
    }
    
    private boolean setArticuloJPA(Articulo articulo) {        
        try {
            
            articuloJPA.create(articulo);
        } catch (Exception ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }    
    
    public Articulo getArticuloByCodigo(String codigo) {
        return articuloJPA.findArticulo(codigo);
    }
    
    public boolean setCliente(String eMail, String nombre, String domicilio, String nif, String tipo){        
        try {
            if (tipo.equals("1")) {  
                cliente = new Cliente(eMail,nombre,domicilio,nif);
                clienteStd = new Clienteestandard(eMail);
                clienteJPA.create(cliente);
                clienteSTDJPA.create(clienteStd);
            } 
            else if(tipo.equals("2")) {            
                cliente = new Cliente(eMail,nombre,domicilio,nif);
                clientePrm = new Clientepremium(eMail); 
                clienteJPA.create(cliente);
                clientePRMJPA.create(clientePrm);
            }
        } catch (Exception ex) {            
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;              
    }
    
    
    
//
//
//    public boolean setPedido(int numPedido, Articulo articulo,int cantidad, Cliente cliente)
//    {   
//        boolean success=false;
//        pedido=new Pedido(numPedido,articulo,cantidad,cliente);
//        DaoPedido dao= new PedidoDAOImpl();
//        try {
//            success = dao.registrar(pedido);            
//        } catch (Exception e) {
//            success = false;
//            throw new RuntimeException(e);
//        }
//        return success;
//    }
//
    public Cliente clienteByEmail(String eMail){
        return clienteJPA.findCliente(eMail);       
    }  
    
    public List<Cliente> getListaClientes(){              
        //Cliente cliente;        
        try {
            List lista = clienteJPA.findClienteEntities();
            return lista;
        } catch (Exception e) {
            throw new RuntimeException(e);            
        }        
    }

    

    public List<Cliente> getListaClientesSTD(){   
        //List<Cliente> lista = new ArrayList<>();         
        try {
            List lista = clienteSTDJPA.findClienteestandardEntities();
            return lista;   
        } catch (Exception e) {
            throw new RuntimeException(e);            
        }
           
    }

    public List<Cliente> getListaClientesPRM(){   
        // List<Cliente> lista = new ArrayList<>();        
        try {
            List lista = clientePRMJPA.findClientepremiumEntities();
            return lista;
        } catch (Exception e) {
            throw new RuntimeException(e);            
        }
              
    }
//
//    public List<Pedido> getListaPedidos() {
//        List<Pedido> lista = new ArrayList<>();
//        DaoPedido dao = new PedidoDAOImpl();
//        try {
//            lista = dao.listarPedidos();
//        } catch (Exception e) {
//            throw new RuntimeException(e);                      
//        }
//        return lista; 
//    }
//
//    public List<Pedido> getEnviadosByCliente(String eMail) {
//        List<Pedido> listaCompleta = new ArrayList<>();
//        List<Pedido> listaByCliente = new ArrayList<>();
//        listaCompleta = getListaPedidos();
//        for(int item=0; item<(listaCompleta.size()); item++){
//            if(pedidoEnviado(listaCompleta, item)){
//                if(listaCompleta.get(item).getCliente().geteMail().equals(eMail)){
//                    listaByCliente.add(listaCompleta.get(item));                
//                }                                
//            }
//        } 
//        return listaByCliente;
//    }
//
//    public List<Pedido> getPendienteByCliente(String eMail) {
//        List<Pedido> listaCompleta = new ArrayList<>();
//        List<Pedido> listaByCliente = new ArrayList<>();
//        listaCompleta = getListaPedidos();
//        for(int item=0; item<(listaCompleta.size()); item++){
//            if(!pedidoEnviado(listaCompleta, item)){
//                if(listaCompleta.get(item).getCliente().geteMail().equals(eMail)){
//                    listaByCliente.add(listaCompleta.get(item));                
//                }                                
//            }
//        } 
//        return listaByCliente;
//    }
//
//    public int getNumeroPedido(){
//        int numPedido=0;
//        DaoPedido dao= new PedidoDAOImpl();
//        try {
//            numPedido=dao.getNumPedido();
//        } catch (Exception e) {
//            throw new RuntimeException(e);            
//        }
//        return numPedido;
//    }
//
//    public int pedidoByNum(int numPedido){
//        List<Pedido> lista = new ArrayList<>();
//        lista = getListaPedidos();
//        for(int item=0; item<(lista.size()); item++) {
//            if (numPedido==(lista.get(item).getNumPedido())){
//                  return item;
//            }
//          }
//          return -1;
//      } 
//    public boolean pedidoEnviado(List<Pedido> lista,int item){
//         LocalDateTime fechahoraPedido;
//         LocalDateTime fechahoraAhora= LocalDateTime.now();  
//         int tiempoPrepara;
//         fechahoraPedido=lista.get(item).getFechaYhora();
//         tiempoPrepara=lista.get(item).getArticulo().getTiempoPreparacion();
//         if (fechahoraPedido.plusMinutes(tiempoPrepara).isBefore(fechahoraAhora)) {
//            return true; 
//         }
//         return false;
//     }
//
//     public void borrarPedido(int id_Pedido) {
//        DaoPedido dao= new PedidoDAOImpl();
//        try
//        {
//            dao.borrarPedido(id_Pedido);
//        } catch (Exception e) {
//            throw  new RuntimeException(e);
//        }
//    }
//        
    
}
