package com.emergentes.JpaClases;

import com.emergentes.JpaClases.Almacen;
import com.emergentes.JpaClases.Empleados;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-12-01T14:03:14")
@StaticMetamodel(Pedidos.class)
public class Pedidos_ { 

    public static volatile SingularAttribute<Pedidos, String> nombreCli;
    public static volatile SingularAttribute<Pedidos, Date> fechaEntrega;
    public static volatile SingularAttribute<Pedidos, String> ciCli;
    public static volatile SingularAttribute<Pedidos, Almacen> idObj;
    public static volatile SingularAttribute<Pedidos, String> telefonoCli;
    public static volatile SingularAttribute<Pedidos, Empleados> idEmp;
    public static volatile SingularAttribute<Pedidos, Integer> idPedido;
    public static volatile SingularAttribute<Pedidos, Integer> ctdPed;
    public static volatile SingularAttribute<Pedidos, Integer> pago;

}