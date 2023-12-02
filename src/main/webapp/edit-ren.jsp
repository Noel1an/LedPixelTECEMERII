
<%@page import="com.emergentes.JpaClases.Almacen"%>
<%@page import="com.emergentes.JpaClases.Empleados"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.JpaClases.Rentas"%>

<%
    Rentas ventas = (Rentas)request.getAttribute("rentas");
    List<Empleados>empleados=(List<Empleados>)request.getAttribute("empleado");
    List<Almacen>almacen=(List<Almacen>)request.getAttribute("almacen");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="contenedor1">
        <h1>Registro de Rentas</h1>
        <form action="RentasController" method="post">
            <input type="hidden" name="id" value="<%=ventas.getIdRen()%>">
            <table>
                 <tr>
                <td>Objeto Rentado</td>
                    <td>
                        <select name="idobj"id="username">
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
                    <td>Cantidad Rentada</td>
                    <td><input type="number" name="cantidad" value="<%=ventas.getCtdRen()%>"id="username"></td>
                </tr>
               <tr>
                <td>Empleado</td>
                    <td>
                        <select name="idemp"id="username">
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
                    <td>Pago por Renta</td>
                    <td><input type="float" name="monto" value="<%=ventas.getMonto()%>"id="username"></td>
                </tr>
                <tr>
                    <td>Fecha de Entrega</td>
                    <td><input type="date" name="fechae" value="<%=ventas.getFechaEntrega()%>"id="username"></td>
                </tr>
                <tr>
                    <td>Fecha de Devolucion</td>
                    <td><input type="date" name="fechad" value="<%=ventas.getFechaDev()%>"id="username"></td>
                </tr>
                <tr>
                    <td>Nombre de Cliente</td>
                    <td><input type="text" name="nombrecli" value="<%=ventas.getNombreCliR()%>"id="username"></td>
                </tr>
                <tr>
                    <td>CI de Cliente</td>
                    <td><input type="text" name="cicli" value="<%=ventas.getCi()%>"id="username"></td>
                </tr>
                <tr>
                    <td>Telefono de Cliente</td>
                    <td><input type="text" name="telefonocli" value="<%=ventas.getTelefono()%>"id="username"></td>
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