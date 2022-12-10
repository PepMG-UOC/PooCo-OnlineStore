package pooco.vista;

import pooco.controlador.Controlador;
import java.util.Scanner;


public class GestionOS {
    private Controlador controlador;
    Scanner teclado = new Scanner(System.in);
            
    public GestionOS() {
        controlador = new Controlador(); 
    }
        
    public void inicio() {
               boolean salir = false;
        char opcio;
        do {
            System.out.println(); 
            System.out.println("1. Gestión Articulos");
            System.out.println("2. Gestión Clientes");
            System.out.println("3. Gestión Pedidos");
            System.out.println("0. Salir");
            opcio = pedirOpcion();
            switch (opcio) {
                case '1':
                    controlador.menuArticulo();                                  
                break;
                case '2':
                    controlador.menuCliente();                    
                break;
                case '3':
                    controlador.menuPedido();
                break;
                case '0':
                    salir = true;
                }
            } while (!salir);
    }
    
    char pedirOpcion() {
        String resp;
        System.out.print("Elige una opción (1,2,3 o 0): ");
        resp = teclado.nextLine();
        if (resp.isEmpty()) {
            resp = " ";
        }
        return resp.charAt(0);
    }
}
    


