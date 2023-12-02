package com.emergentes.JpaClases;

import com.emergentes.JpaClases.Proveedores;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-12-01T14:03:14")
@StaticMetamodel(Compras.class)
public class Compras_ { 

    public static volatile SingularAttribute<Compras, Proveedores> idProv;
    public static volatile SingularAttribute<Compras, Date> fechaCompra;
    public static volatile SingularAttribute<Compras, String> estado;
    public static volatile SingularAttribute<Compras, Integer> ctdCompra;
    public static volatile SingularAttribute<Compras, Float> monto;
    public static volatile SingularAttribute<Compras, Integer> idCompra;
    public static volatile SingularAttribute<Compras, String> descCompra;

}