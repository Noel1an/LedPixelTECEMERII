<%@page import="com.emergentes.JpaClases.Almacen"%>
<%@page import="com.emergentes.JpaClases.Empleados"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.JpaClases.Ventas"%>
<%
    Ventas ventas = (Ventas)request.getAttribute("venta");
    List<Empleados>empleados=(List<Empleados>)request.getAttribute("empleado");
    List<Almacen>almacen=(List<Almacen>)request.getAttribute("almacen");
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
        <h1>Registro de Ventas</h1>
        <form action="VentaController" method="post">
            <input type="hidden" name="id" value="<%=ventas.getIdVen()%>">
            <table>
                 <tr>
                <td>Objeto Vendido</td>
                    <td>
                        <select name="idobj" id="username">
                            <%
                            for(Almacen item : almacen){
                            %>
                            <option value="<%=item.getIdObj()%>"
                                    ><%=item.getNombreObj()%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Cantidad Vendida</td>
                    <td><input type="number" name="cantidad" value="<%=ventas.getCtdVen()%>" id="username"></td>
                </tr>
               <tr>
                <td>Empleado</td>
                    <td>
                        <select name="idemp" id="username">
                            <%
                            for(Empleados item : empleados){
                            %>
                            <option value="<%=item.getIdEmp()%>"
                                    ><%=item.getNombreEmp()%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Fecha de Venta</td>
                    <td><input type="date" name="fecha" value="<%=ventas.getFecha()%>"id="username"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" id="BOTON"></td>
                </tr>
            </table>
        </form>
                </div>
    </body>
</html>
