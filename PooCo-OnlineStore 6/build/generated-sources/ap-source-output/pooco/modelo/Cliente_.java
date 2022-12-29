package pooco.modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pooco.modelo.Clienteestandard;
import pooco.modelo.Clientepremium;
import pooco.modelo.Pedido;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-12-29T01:07:10", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, Clientepremium> clientepremium;
    public static volatile SingularAttribute<Cliente, String> domicilio;
    public static volatile SingularAttribute<Cliente, String> ideMail;
    public static volatile SingularAttribute<Cliente, Clienteestandard> clienteestandard;
    public static volatile ListAttribute<Cliente, Pedido> pedidoList;
    public static volatile SingularAttribute<Cliente, String> nif;
    public static volatile SingularAttribute<Cliente, String> nombre;

}