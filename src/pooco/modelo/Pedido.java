package pooco.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.sql.*;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pedido")
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByIdPedido", query = "SELECT p FROM Pedido p WHERE p.idPedido = :idPedido"),
    @NamedQuery(name = "Pedido.findByCantidad", query = "SELECT p FROM Pedido p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Pedido.findByFechaHora", query = "SELECT p FROM Pedido p WHERE p.fechaHora = :fechaHora")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPedido")
    private Integer idPedido;
    @Column(name = "Cantidad")
    private Integer cantidad;
    @Column(name = "FechaHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaHora;
    @JoinColumn(name = "idArticuloPedido", referencedColumnName = "idArticulo")
    @ManyToOne
    private Articulo idArticuloPedido;
    @JoinColumn(name = "id_eMailPedido", referencedColumnName = "id_eMail")
    @ManyToOne
    private Cliente ideMailPedido;

    //Timestamp ts = new Timestamp(millis);
    
    public Pedido() {
    }

    public Pedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
     public Pedido(int numPedido, Articulo articulo, int cantidad, Cliente cliente) {
        this.idPedido = numPedido;
        this.idArticuloPedido = articulo;
        this.cantidad = cantidad;
        this.ideMailPedido = cliente;
        LocalDateTime localDateTime = LocalDateTime.now();
        this.fechaHora = Timestamp.valueOf(localDateTime);       
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Articulo getIdArticuloPedido() {
        return idArticuloPedido;
    }

    public void setIdArticuloPedido(Articulo idArticuloPedido) {
        this.idArticuloPedido = idArticuloPedido;
    }

    public Cliente getIdeMailPedido() {
        return ideMailPedido;
    }

    public void setIdeMailPedido(Cliente ideMailPedido) {
        this.ideMailPedido = ideMailPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return "\n" 
             + "Numero de Pedido: " + this.idPedido + "\n"
             + "Fecha y hora: " + this.fechaHora  + "\n"            
             + "NIF del Cliente: " + this.ideMailPedido + "\n"
           //  + "Nombre Cliente: " + this.cliente.getNombre() + "\n"
             + "Codigo Articulo: " + this.idArticuloPedido + "\n" 
           //  + "Descripcion Articulo: " + this.articulo.getDescripcion() + "\n"
             + "Cantidad: " + this.cantidad + "\n" 
          //   + "Pvp Articulo: " + String.valueOf(this.articulo.getPvpVenta())  + "\n"
           //  + "Coste envio: " + String.valueOf(this.articulo.getGastosEnvio()) + "\n"
           //  + "Pvp Total: " + String.valueOf(cantidad*this.articulo.getPvpVenta()) 
                + "\n";              
    }  
    
}
