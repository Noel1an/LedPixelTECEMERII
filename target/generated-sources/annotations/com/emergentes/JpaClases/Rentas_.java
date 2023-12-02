package com.emergentes.JpaClases;

import com.emergentes.JpaClases.Almacen;
import com.emergentes.JpaClases.Empleados;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-12-01T14:03:14")
@StaticMetamodel(Rentas.class)
public class Rentas_ { 

    public static volatile SingularAttribute<Rentas, Almacen> idObjRen;
    public static volatile SingularAttribute<Rentas, Float> monto;
    public static volatile SingularAttribute<Rentas, Integer> idRen;
    public static volatile SingularAttribute<Rentas, String> ci;
    public static volatile SingularAttribute<Rentas, Date> fechaEntrega;
    public static volatile SingularAttribute<Rentas, Integer> ctdRen;
    public static volatile SingularAttribute<Rentas, Date> fechaDev;
    public static volatile SingularAttribute<Rentas, Empleados> idEmp;
    public static volatile SingularAttribute<Rentas, String> telefono;
    public static volatile SingularAttribute<Rentas, String> nombreCliR;

}