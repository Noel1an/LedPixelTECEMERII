<%@page import="com.emergentes.JpaClases.Roles"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.JpaClases.Empleados"%>
<%

    Empleados empleado = (Empleados)request.getAttribute("empleado");
    List<Roles> roles= (List<Roles>)request.getAttribute("roles");
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <div id="contenedor1">
        <h1>Registro de empleados</h1>
        <form action="MainController" method="post">
            <input type="hidden" name="idEmp" value="<%=empleado.getIdEmp()%>">
            <section>
            <table>
                <tr>
                    <td>Nombre</td>
                    <td><input type="text" name="NombreEmp" value="<%=empleado.getNombreEmp()%>"id="username" ></td>
                </tr>
                <tr>
                    <td>Apellido</td>
                    <td><input type="text" name="ApellidoEmp" value="<%=empleado.getApellidoEmp()%>"id="username"></td>
                </tr>
                <tr>
                    <td>CI</td>
                    <td><input type="text" name="ciEmp" value="<%=empleado.getCiEmp()%>"id="username" ></td>
                </tr>
                <tr>
                    <td>Correo</td>
                    <td><input type="text" name="CorreoEmp" value="<%=empleado.getCorreoEmp()%>"id="username"></td>
                </tr>
                <tr>
                    <td>Usuario</td>
                    <td><input type="text" name="Usuario" value="<%=empleado.getUsuario()%>"id="username"></td>
                </tr>
                <tr>
                    <td>Contrasenia</td>
                    <td><input type="text" name="Contrasenia" value="<%=empleado.getContrasenia()%>"id="username"></td>
                </tr>
                <tr>
                    <td>Rol</td>
                    <td>
                        <select name="rol_id" id="username">
                            <%
                            for(Roles item : roles){
                            %>
                            <option value="<%=item.getIdRol()%>"
                                    ><%=item.getNombreRol()%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Sueldo</td>
                    <td><input type="float" name="Sueldo" value="<%=empleado.getSueldo()%>" id="username"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" id="BOTON"></td>
                </tr>
            </table>
            </section>
        </form>
       </div>
    </body>
</html>
