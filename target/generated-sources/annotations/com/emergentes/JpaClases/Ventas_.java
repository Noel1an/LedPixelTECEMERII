package com.emergentes.JpaClases;

import com.emergentes.JpaClases.Almacen;
import com.emergentes.JpaClases.Empleados;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-12-01T14:03:14")
@StaticMetamodel(Ventas.class)
public class Ventas_ { 

    public static volatile SingularAttribute<Ventas, Date> fecha;
    public static volatile SingularAttribute<Ventas, Integer> idVen;
    public static volatile SingularAttribute<Ventas, Empleados> idEmp;
    public static volatile SingularAttribute<Ventas, Almacen> idObjVen;
    public static volatile SingularAttribute<Ventas, Integer> ctdVen;

}