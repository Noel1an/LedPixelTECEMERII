package com.emergentes.JpaClases;

import com.emergentes.JpaClases.Pedidos;
import com.emergentes.JpaClases.Rentas;
import com.emergentes.JpaClases.Roles;
import com.emergentes.JpaClases.Ventas;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-12-01T14:03:14")
@StaticMetamodel(Empleados.class)
public class Empleados_ { 

    public static volatile SingularAttribute<Empleados, String> apellidoEmp;
    public static volatile SingularAttribute<Empleados, String> ciEmp;
    public static volatile SingularAttribute<Empleados, Roles> idRol;
    public static volatile SingularAttribute<Empleados, Float> sueldo;
    public static volatile ListAttribute<Empleados, Ventas> ventasList;
    public static volatile SingularAttribute<Empleados, String> correoEmp;
    public static volatile SingularAttribute<Empleados, String> usuario;
    public static volatile SingularAttribute<Empleados, String> contrasenia;
    public static volatile SingularAttribute<Empleados, Integer> idEmp;
    public static volatile ListAttribute<Empleados, Pedidos> pedidosList;
    public static volatile ListAttribute<Empleados, Rentas> rentasList;
    public static volatile SingularAttribute<Empleados, String> nombreEmp;

}