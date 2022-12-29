package pooco.modelo;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pooco.modelo.Articulo;
import pooco.modelo.Cliente;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-12-29T01:07:10", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, Date> fechaHora;
    public static volatile SingularAttribute<Pedido, Cliente> ideMailPedido;
    public static volatile SingularAttribute<Pedido, Integer> cantidad;
    public static volatile SingularAttribute<Pedido, Articulo> idArticuloPedido;
    public static volatile SingularAttribute<Pedido, Integer> idPedido;

}