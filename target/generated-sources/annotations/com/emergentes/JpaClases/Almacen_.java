package com.emergentes.JpaClases;

import com.emergentes.JpaClases.Pedidos;
import com.emergentes.JpaClases.Rentas;
import com.emergentes.JpaClases.Ventas;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-12-01T14:03:14")
@StaticMetamodel(Almacen.class)
public class Almacen_ { 

    public static volatile SingularAttribute<Almacen, Integer> stockObj;
    public static volatile ListAttribute<Almacen, Ventas> ventasList;
    public static volatile SingularAttribute<Almacen, Integer> idObj;
    public static volatile ListAttribute<Almacen, Pedidos> pedidosList;
    public static volatile ListAttribute<Almacen, Rentas> rentasList;
    public static volatile SingularAttribute<Almacen, String> nombreObj;

}