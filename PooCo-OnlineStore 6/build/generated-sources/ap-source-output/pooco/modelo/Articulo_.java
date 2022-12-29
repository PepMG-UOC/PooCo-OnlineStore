package pooco.modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import pooco.modelo.Pedido;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-12-29T01:07:10", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Articulo.class)
public class Articulo_ { 

    public static volatile SingularAttribute<Articulo, String> idArticulo;
    public static volatile SingularAttribute<Articulo, String> descripcion;
    public static volatile SingularAttribute<Articulo, Integer> tiempoPreparacion;
    public static volatile ListAttribute<Articulo, Pedido> pedidoList;
    public static volatile SingularAttribute<Articulo, Float> gastosEnvio;
    public static volatile SingularAttribute<Articulo, Float> pvpVenta;

}