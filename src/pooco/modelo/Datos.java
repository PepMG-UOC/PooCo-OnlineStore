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
    
    
    


    public boolean setPedido(int numPedido, Articulo articulo,int cantidad, Cliente cliente)
    {   
        boolean success=false;
        pedido=new Pedido(numPedido,articulo,cantidad,cliente);
        
        try {
            pedidoJPA.create(pedido);         
            success = true;
            //success = dao.registrar(pedido);            
        } catch (Exception e) {
            success = false;
            throw new RuntimeException(e);
        }
        return success;
    }

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
        List<Cliente> lista = new ArrayList<>(); 
        List<Clienteestandard> listaSTD = new ArrayList<>();
        List<Cliente> listaJoin = new ArrayList<>();
        try {
            lista = clienteJPA.findClienteEntities();
            listaSTD = clienteSTDJPA.findClienteestandardEntities();               
        } catch (Exception e) {
            throw new RuntimeException(e);            
        }
        for(int item=0; item<(lista.size()); item++) {
            for(int itemSt=0; itemSt<(listaSTD.size()); itemSt++) {
                if(lista.get(item).getIdeMail().equals(listaSTD.get(itemSt).getIdeMailestandard())) {
                    listaJoin.add(lista.get(item));
                }
            }               
        }
        return listaJoin;
           
    }

    public List<Cliente> getListaClientesPRM(){   
        List<Cliente> lista = new ArrayList<>(); 
        List<Clientepremium> listaPRM = new ArrayList<>();
        List<Cliente> listaJoin = new ArrayList<>();        
        try {
            lista = clienteJPA.findClienteEntities();
            listaPRM = clientePRMJPA.findClientepremiumEntities();            
        } catch (Exception e) {
            throw new RuntimeException(e);            
        }
        for(int item=0; item<(lista.size()); item++) {
            for(int itemSt=0; itemSt<(listaPRM.size()); itemSt++) {
                if(lista.get(item).getIdeMail().equals(listaPRM.get(itemSt).getIdeMailPremium())) {
                    listaJoin.add(lista.get(item));
                }
            }               
        }
        return listaJoin;      
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
    public int getNumeroPedido(){
        int numPedido=0;        
        try {
            numPedido = pedidoJPA.getPedidoCount();            
        } catch (Exception e) {
            throw new RuntimeException(e);            
        }
        return numPedido;
    }
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
    
    
    public Clienteestandard clienteTipoSTD(String eMail) {
        Cliente miCliente;
        miCliente=clienteByEmail(eMail);          
        List<Clienteestandard> listaSTD = new ArrayList<>(); 
         try {            
            listaSTD = clienteSTDJPA.findClienteestandardEntities();                       
        } catch (Exception e) {
            throw new RuntimeException(e);            
        } 
        for(int item=0; item<(listaSTD.size()); item++) {
            if(miCliente.getIdeMail().equals(listaSTD.get(item).getIdeMailestandard()) ) {                
                return listaSTD.get(item);             
            }
        }
        return null;    
    }
    
    public Clientepremium clienteTipoPRM(String eMail) {
        Cliente miCliente;
        miCliente=clienteByEmail(eMail);          
        List<Clientepremium> listaPRM = new ArrayList<>(); 
         try {            
            listaPRM = clientePRMJPA.findClientepremiumEntities();                       
        } catch (Exception e) {
            throw new RuntimeException(e);            
        } 
        for(int item=0; item<(listaPRM.size()); item++) {
            if(miCliente.getIdeMail().equals(listaPRM.get(item).getIdeMailPremium()) ) {                
                return listaPRM.get(item);             
            }
        }
        return null;    
    }
    
    
//    public Cliente cienteTipoByMail(String eMail) {
//        Cliente miCliente;
//        miCliente=clienteByEmail(eMail);      
//        
//        List<Clientepremium> listaPRM = new ArrayList<>();
//        List<Clienteestandard> listaSTD = new ArrayList<>();        
//        //List<Cliente> listaJoin = new ArrayList<>();        
//        try {
//            //lista = clienteJPA.findClienteEntities();
//            listaSTD = clienteSTDJPA.findClienteestandardEntities();
//            listaPRM = clientePRMJPA.findClientepremiumEntities();            
//        } catch (Exception e) {
//            throw new RuntimeException(e);            
//        }    
//       
//        for(int item=0; item<(listaSTD.size()); item++) {
//            if(miCliente.getIdeMail().equals(listaSTD.get(item).getIdeMailestandard()) ) {   
//               
//                return listaSTD.get(item);             
//            }
//        }        
//        for(int item=0; item<(listaPRM.size()); item++) {
//            if(miCliente.getIdeMail().equals(listaPRM.get(item).getIdeMailPremium()) ) {
//                return listaPRM.get(item);
//            }            
//        }            
//        return null;          
//   }
}
