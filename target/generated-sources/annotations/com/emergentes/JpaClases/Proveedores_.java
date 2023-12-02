package com.emergentes.JpaClases;

import com.emergentes.JpaClases.Compras;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-12-01T14:03:14")
@StaticMetamodel(Proveedores.class)
public class Proveedores_ { 

    public static volatile SingularAttribute<Proveedores, Integer> idProv;
    public static volatile ListAttribute<Proveedores, Compras> comprasList;
    public static volatile SingularAttribute<Proveedores, String> direccion;
    public static volatile SingularAttribute<Proveedores, String> nombreProv;
    public static volatile SingularAttribute<Proveedores, String> telefono;

}