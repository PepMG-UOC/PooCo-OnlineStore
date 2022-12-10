/*
 * Aplicación de escritorio basada en Java que se ejecutará en el backend 
 * como alternativa a la aplicación web de un comercio electronico.
 * Persistencia en BBDD con JPA - EclipseLink
 */
package pooco.vista;

public class OnlineStore {

    public static void main(String[] args) {

        GestionOS gestion = new GestionOS();
        gestion.inicio();
    }
}

