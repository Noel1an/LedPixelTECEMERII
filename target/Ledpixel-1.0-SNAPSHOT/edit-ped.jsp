<%@page import="com.emergentes.JpaClases.Almacen"%>
<%@page import="com.emergentes.JpaClases.Empleados"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.JpaClases.Pedidos"%>
<%
    Pedidos pedidos = (Pedidos)request.getAttribute("pedidos");
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
        <h1>Registro de Pedidos</h1>
        <form action="PedidosController" method="post">
            <input type="hidden" name="id" value="<%=pedidos.getIdPedido()%>">
            <table>
                <tr>
                    <td>Nombre del Cliente</td>
                    <td><input type="text" name="nombrecli" value="<%=pedidos.getNombreCli()%>"id="username"></td>
                </tr>
                <tr>
                    <td>Ci del Cliente</td>
                    <td><input type="text" name="cicli" value="<%=pedidos.getCiCli()%>"id="username"></td>
                </tr>
                <tr>
                    <td>Telefono del Cliente</td>
                    <td><input type="text" name="telefono" value="<%=pedidos.getTelefonoCli()%>"id="username"></td>
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
                <td>Objeto Pedido</td>
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
                    <td>Cantidad Pedida</td>
                    <td><input type="number" name="cantidad" value="<%=pedidos.getCtdPed()%>"id="username"></td>
                </tr>
                <tr>
                    <td>Monto de Pago</td>
                    <td><input type="number" name="Pago" value="<%=pedidos.getPago()%>"id="username"></td>
                </tr>
                <tr>
                    <td>Fecha de Entrega</td>
                    <td><input type="date" name="fecha" value="<%=pedidos.getFechaEntrega()%>"id="username"></td>
                </tr>
                <tr>
                    
                    </td>
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
