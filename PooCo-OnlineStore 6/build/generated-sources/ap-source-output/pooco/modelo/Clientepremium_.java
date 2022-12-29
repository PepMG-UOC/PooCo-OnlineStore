package pooco.modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pooco.modelo.Cliente;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-12-29T01:07:10", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Clientepremium.class)
public class Clientepremium_ { 

    public static volatile SingularAttribute<Clientepremium, String> ideMailPremium;
    public static volatile SingularAttribute<Clientepremium, Cliente> cliente;
    public static volatile SingularAttribute<Clientepremium, Float> descuento;
    public static volatile SingularAttribute<Clientepremium, Float> tarifaAnual;

}