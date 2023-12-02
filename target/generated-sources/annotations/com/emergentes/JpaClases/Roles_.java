package com.emergentes.JpaClases;

import com.emergentes.JpaClases.Empleados;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-12-01T14:03:14")
@StaticMetamodel(Roles.class)
public class Roles_ { 

    public static volatile SingularAttribute<Roles, Integer> idRol;
    public static volatile ListAttribute<Roles, Empleados> empleadosList;
    public static volatile SingularAttribute<Roles, String> nombreRol;
    public static volatile SingularAttribute<Roles, String> codRol;

}