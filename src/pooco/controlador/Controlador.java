package pooco.controlador;

import java.io.IOException;
import pooco.modelo.Datos;
import pooco.vista.*;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Controlador {
    private Datos datos;
//    private ArticuloVista articuloView = new ArticuloVista(); 
//    private ClienteVista clienteVista = new ClienteVista();  
//    private PedidoVista pedidoVista = new PedidoVista();

//Variabeles articulo    
    @FXML
    private TextField txtId;
    @FXML
    private Label lblDescripcion;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private Label lblPvpVenta;
    @FXML
    private TextField txtPrecio;
    @FXML
    private Label lblGastoEnvio;
    @FXML
    private TextField txtEnvio;
    @FXML
    private Label lblTiempoPrep;
    @FXML
    private TextField txtPreparacion;
    @FXML
    private TextArea txtResult;
    
//variables cliente
    @FXML
    private TextField txteMail;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDomicilio;
    @FXML
    private TextField txtNif;
    @FXML
    private TextField txtTipo;
    
    
    public Controlador() {       
        datos = new Datos ();       
    }
    
    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {        
        this.datos = datos;
    }
    
    @FXML
    private void btnGestionArticulos(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/MenuArticuloVistaFX.fxml"));
            Pane ventana = (Pane) loader.load();
           
            Scene scene = new Scene(ventana);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();     
 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @FXML        
    private void btnGestionClientes(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/MenuClienteVistaFX.fxml"));
            Pane ventana = (Pane) loader.load();

            Scene scene = new Scene(ventana);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();     
 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML    
    private void btnGestionPedidos(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/MenuPedidoVistaFX.fxml"));
            Pane ventana = (Pane) loader.load();

            Scene scene = new Scene(ventana);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();     
 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML    
    private void btnSalir(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML    
    private void btnAddArticulo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/AddArticuloVistaFX.fxml"));
            Pane ventana = (Pane) loader.load();

            Scene scene = new Scene(ventana);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();     
 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void addArticulo(ActionEvent event) {
        boolean success=false;
        String codigo;
        String descripcionArticulo;
        Float pvpVentaArticulo;
        Float gastosEnvioArticulo;
        Integer tiempoPreparacionArticulo;        
         
        codigo = txtId.getText();
        if (codigo!="") {
            descripcionArticulo = txtDescripcion.getText();
            if (txtPrecio.getText()=="") pvpVentaArticulo=0f;
            else pvpVentaArticulo = Float.parseFloat(txtPrecio.getText());
            if (txtEnvio.getText()=="") gastosEnvioArticulo=0f;
            else gastosEnvioArticulo = Float.parseFloat(txtEnvio.getText());
            if (txtPreparacion.getText()=="") tiempoPreparacionArticulo=0;
            else tiempoPreparacionArticulo = Integer.parseInt(txtPreparacion.getText());
            if (datos.getArticuloByCodigo(codigo)==null) {
                success = datos.setArticulo(codigo, descripcionArticulo, pvpVentaArticulo
                ,gastosEnvioArticulo,tiempoPreparacionArticulo);
            } else {
                txtResult.setVisible(true);
                txtResult.setText("El Artículo " + codigo + "\n" + "ya existe.");             
            }  
            if(success) {
                txtResult.setVisible(true);
                txtResult.setText("El Artículo " + codigo + "\n" + "se ha introducido correctamente.");  
            }
        }
        else {
            txtResult.setVisible(true);
            txtResult.setText("Debe insertar un código.");  
        }      
        txtId.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtEnvio.setText("");
        txtPreparacion.setText("");                
    }
    
    @FXML    
    private void btnMostrarArticulo(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/MostrarArticuloVistaFX.fxml"));
            Pane ventana = (Pane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(ventana);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();     
 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }  
    
     @FXML
    private void mostrarArticulo(ActionEvent event) {   
        String codigo = txtId.getText();
        if (codigo!="") {
            if (datos.getArticuloByCodigo(codigo) != null) {
                txtDescripcion.setText(datos.getArticuloByCodigo(codigo).getDescripcion());
                txtPrecio.setText(datos.getArticuloByCodigo(codigo).getPvpVenta().toString());
                txtEnvio.setText(datos.getArticuloByCodigo(codigo).getGastosEnvio().toString());
                txtPreparacion.setText(datos.getArticuloByCodigo(codigo).getTiempoPreparacion().toString());
                lblDescripcion.setVisible(true);
                txtDescripcion.setVisible(true);
                lblPvpVenta.setVisible(true);
                txtPrecio.setVisible(true);
                lblGastoEnvio.setVisible(true);
                txtEnvio.setVisible(true);
                lblTiempoPrep.setVisible(true);
                txtPreparacion.setVisible(true);

            } else {
                lblDescripcion.setVisible(false);
                txtDescripcion.setVisible(false);
                lblPvpVenta.setVisible(false);
                txtPrecio.setVisible(false);
                lblGastoEnvio.setVisible(false);
                txtEnvio.setVisible(false);
                lblTiempoPrep.setVisible(false);
                txtPreparacion.setVisible(false);
                txtResult.setVisible(true);
                txtResult.setText("No esxite un articulo con este código");   
            }
        } else {
            txtResult.setVisible(true);
            txtResult.setText("Debe insertar un código.");  
        }
        
    }

    @FXML
    private void btnAddCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/AddClienteVistaFX.fxml"));
            Pane ventana = (Pane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(ventana);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();     
 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void addCliente(ActionEvent event) {
         boolean success=false;
        String codigo = txteMail.getText();
        if (codigo!="") {
            String nombreCliente = txtNombre.getText();
            String domicilioCliente = txtDomicilio.getText();
            String nifCliente = txtNif.getText();
            String tipoCliente = txtTipo.getText();
            
            if (datos.clienteByEmail(codigo)==null) {
                success = datos.setCliente(codigo, nombreCliente, domicilioCliente, nifCliente, tipoCliente);
            } else {
                txtResult.setVisible(true);
                txtResult.setText("El Email " + codigo + " ya existe.");             
            }  
            if(success) {
                txtResult.setVisible(true);
                txtResult.setText("El Email " + codigo + " se ha introducido correctamente.");  
            }
        }
        else {
            txtResult.setVisible(true);
            txtResult.setText("Debe insertar un email.");  
        }      
        txteMail.setText("");
        txtNombre.setText("");
        txtDomicilio.setText("");
        txtNif.setText("");
        txtTipo.setText("");                
    }
    
    @FXML
    private void btnShowCliente(ActionEvent event) {
        
    }
    
    @FXML
    private void btnShowEstandar(ActionEvent event) {
        
    }
    
    @FXML
    private void btnShowPremium(ActionEvent event) {
        
    }
    
    @FXML
    private void btnAddPedido(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/AddPedidoVistaFX.fml"));
            Pane ventana = (Pane) loader.load();
            
            Scene scene = new Scene(ventana);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();     
 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void addPedido(ActionEvent event) {

    }

    @FXML    
    private void btnEliminarPedido(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/EliminarPedidoFX.fml"));
            Pane ventana = (Pane) loader.load();
            
            Scene scene = new Scene(ventana);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();     
 
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void EliminarPedido(ActionEvent event) {

    }

    @FXML    
    private void btnMostrarPedidoPE(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/MostrarPedidoPEVistaFX.fml"));
//            Pane ventana = (Pane) loader.load();
//            
//            Scene scene = new Scene(ventana);
//            Stage stage=new Stage();
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setScene(scene);
//            stage.showAndWait();     
// 
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
    }
    
    @FXML
    private void MoostrarPedidoPE(ActionEvent event) {

    }
    
    @FXML
    private void btnMostrarPedidoEnv(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(OnlineStore.class.getResource("/pooco/vista/MostrarPedidoPEVistaFX.fml"));
//            Pane ventana = (Pane) loader.load();
//            
//            Scene scene = new Scene(ventana);
//            Stage stage=new Stage();
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setScene(scene);
//            stage.showAndWait();     
// 
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
    }
    
    @FXML
    private void MostrarPedidoEnv(ActionEvent event) {

    }
    
       
//    private void muestraArticulo() {
//        String codigo;
//        articuloView.showCabecera();
//        codigo=articuloView.codigoArticulo();        
//        if (datos.getArticuloByCodigo(codigo)!=null)
//        {
//            articuloView.showArticulo( datos.getArticuloByCodigo(codigo).toString());
//        } else articuloView.warning(codigo,false);
//    }
//
//    public void añadirCliente() {
//        boolean success=false;
//        String eMail;
//        clienteVista.adCabecera();
//        eMail = clienteVista.eMailCliente();
//        if(datos.clienteByEmail(eMail)==null) {
//            success = datos.setCliente(eMail, clienteVista.nombreCliente(), clienteVista.domicilioCliente()
//                ,clienteVista.nifCliente(), clienteVista.tipoCliente()); 
//        } else {
//            clienteVista.warning(eMail,true);
//        }         
//        clienteVista.introducido(success);             
//    }
//
//    public void añadirPedido()
//    {          
//        int numPedido;
//        String eMail;
//        String codigo;           
//        float gastos;  
//        float descuento=0f;
//        int cantidad;
//        boolean success;
//         
//        pedidoVista.adCabecera();
//        numPedido = datos.getNumeroPedido();
//        numPedido++;
//        pedidoVista.showNumPedido(numPedido);
//        eMail = clienteVista.eMailCliente();        
//        if (datos.clienteByEmail(eMail)==null)
//        {
//            clienteVista.warning(eMail,false);
//            añadirCliente();            
//        } 
//        codigo = articuloView.codigoArticulo();         
//        if (datos.getArticuloByCodigo(codigo)==null)
//        {
//            articuloView.warning(codigo,false);
//            return;
//        } 
//        gastos= datos.getArticuloByCodigo(codigo).getGastosEnvio();       
//       if (datos.clienteTipoSTD(eMail)!=null ) {
//            descuento= datos.clienteTipoSTD(eMail).getDescuento();
//       }
//       else if (datos.clienteTipoPRM(eMail)!=null ) {
//           descuento= datos.clienteTipoPRM(eMail).getDescuento();
//       }    
//        
//        cantidad =  pedidoVista.cantidadPedido();
//        pedidoVista.showpvpVenta(datos.getArticuloByCodigo(codigo).getPvpVenta(), cantidad);        
//        pedidoVista.showGastosEnvio(gastos, descuento);
//        
//        success = datos.setPedido(numPedido,datos.getArticuloByCodigo(codigo), cantidad, datos.clienteByEmail(eMail));
//        pedidoVista.introducido(success);   
//    }
//    
//    public void eliminarPedido(){
//        int numPedido;  
//        boolean eliminado=false;
//        pedidoVista.delCabecera();
//        numPedido = pedidoVista.numPedido();
//        if (datos.pedidoByNum(numPedido)==-1)
//        {
//            pedidoVista.warning(numPedido,false);
//            return;
//        }         
//        if(!datos.pedidoEnviado(datos.pedidoByNum(numPedido))){
//            eliminado = datos.eliminarPedido(numPedido);                       
//        } 
//        pedidoVista.eliminaOk(numPedido,eliminado); 
//    }
//
//    private void muestraClientes() {        
//        clienteVista.showCabecera();
//        List lista = datos.getListaClientes();   
//        if (lista!=null){
//            for(int item=0; item<(lista.size()); item++) {
//                clienteVista.showClientes(lista.get(item).toString());
//            }
//        }  
//    }
//
//    private void showClientesPorTipo(String tipo){
//        if (tipo.equals("Estandard")) {
//            clienteVista.showCabeceraSTD();  
//            List lista = datos.getListaClientesSTD();   
//            if (lista!=null){
//                for(int item=0; item<(lista.size()); item++) {
//                    clienteVista.showClientes(lista.get(item).toString());
//                }
//            }         
//        }
//        else {
//            clienteVista.showCabeceraPRM();  
//            List lista = datos.getListaClientesPRM();   
//            if (lista!=null){
//                for(int item=0; item<(lista.size()); item++) {
//                    clienteVista.showClientes(lista.get(item).toString());
//                }
//            }         
//        }
//    }
//
//    public void pedidosPendientes(){
//        pedidoVista.showPdteCabecera();        
//        char resultado;
//        boolean salir = false;
//        do {
//            resultado = pedidoVista.menuMostrar();
//            switch (resultado) {
//                case '1':
//                    allPedidosPdte();
//                    break;
//                case '2':
//                    pedidoPendienteFiltro();
//                    break;
//
//                }
//                if (resultado == '0') salir = true;
//            } while (!salir);
//        }
//
//    public void pedidosEnviados(){
//        pedidoVista.showEnviosCabecera();        
//        char resultado;
//        boolean salir = false;
//        do {
//            resultado = pedidoVista.menuMostrar();
//            switch (resultado) {
//                case '1':
//                    allPedidosEnviados();
//                    break;
//                case '2':
//                    pedidoEnviadoFiltro();
//                    break;
//
//                }
//            if (resultado == '0') salir = true;
//        } while (!salir);
//    }
//
//    public void allPedidosPdte(){
//        pedidoVista.showPdteCabecera();
//        List lista = datos.getListaPedidos();
//        if (lista.size()>0) {
//            for(int item=0; item<(lista.size()); item++){
//                if(!datos.pedidoEnviado(item)){
//                    pedidoVista.showPedido(lista.get(item).toString()); 
//                } 
//            }
//        } else {
//            pedidoVista.nadaQmostrar("pendientes.");            
//        }
//        
//    }
// 
//    public void pedidoPendienteFiltro(){
//        String eMail;
//        eMail = clienteVista.eMailCliente();
//        if (datos.clienteByEmail(eMail)==null)
//        {
//            clienteVista.warning(eMail,false);
//            return;
//        } 
//        List lista = datos.getPendienteByCliente(eMail); 
//        if (lista.size()>0) {
//            for(int item=0; item<(lista.size()); item++){           
//                pedidoVista.showPedido(lista.get(item).toString());            
//            }  
//        } else {
//            pedidoVista.nadaQmostrar("pendientes.");            
//        }                
//     } 
//
//    public void allPedidosEnviados(){
//        pedidoVista.showEnviosCabecera();
//        List lista = datos.getListaPedidos();
//        if (lista.size()>0) {
//            for(int item=0; item<(lista.size()); item++){
//                if(datos.pedidoEnviado(item)){
//                    pedidoVista.showPedido(lista.get(item).toString());                
//                } 
//            }    
//        } else {
//            pedidoVista.nadaQmostrar("enviados.");            
//        }  
//        
//    }
//
//    public void pedidoEnviadoFiltro(){
//        String eMail;
//        eMail = clienteVista.eMailCliente();
//        if (datos.clienteByEmail(eMail)==null)
//        {
//            clienteVista.warning(eMail,false);
//            return;
//        } 
//        List lista = datos.getEnviadosByCliente(eMail);  
//        if (lista.size()>0) {
//            for(int item=0; item<(lista.size()); item++){           
//                pedidoVista.showPedido(lista.get(item).toString());           
//            }
//        } else {
//            pedidoVista.nadaQmostrar("enviados.");            
//        }         
//          
//    }

       
    
}
