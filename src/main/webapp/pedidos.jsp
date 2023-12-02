
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.emergentes.JpaClases.Pedidos"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Pedidos> listap =(List<Pedidos>)request.getAttribute("pedido");
     SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
%>
<html>
    <style>
        .boton_enlace{
    display: block;
    width: 170px;
    font-family: Arial,Helvetica,sans-serif;
    font-weight: 700;
    background-color: #1b4f72;
    border-radius: 10px;
    padding: 15px 10px;
    color: #ffffff;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="cssTabla.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>LISTA DE PEDIDOS LED PIXEL</h1>
        
        <section id="pantalla-dividida">
        <div id="contenedor1">
        <form action="MainController"method="get" id="form1">
            <input type="submit" value="EMPLEADOS" id="emp">
        </form>
            <br> 
        <form action="InventarioController"method="get" id="form2">
            <input type="submit" value="INVENTARIO" id="inv">
        </form>
        <br> 
        <form action="PedidosController"method="get"id="form3">
            <input type="submit" value="PEDIDOS" id="ped">
        </form>
       <br> 
        <form action="VentaController"method="get" id="form4">
            <input type="submit" value="VENTAS" id="venta">
        </form>
       <br>
       <form action="RentasController"method="get" id="form5">
            <input type="submit" value="RENTAS" id="com">
        </form>
       <br>
        <form action="ComprasController"method="get" id="form5">
            <input type="submit" value="COMPRAS" id="com">
        </form>
         <br>
        <a class="boton_enlace" href="PedidosController?action=add">NUEVO REGISTRO</a>

    </div>
 
        <div id="tablas">
        <br><br>
        <table>
            <thead>
            <tr>
                <td>ID</td>
                <td>CLIENTE</td>
                <td>CI</td>
                <td>TELEFONO</td>
                <td>EMPLEADO</td>
                <td>OBJETO PEDIDO</td>
                <td>CANTIDAD PEDIDA</td>
                <td>PAGO</td>
                <td>FECHA DE ENTREGA</td>
                <td></td>
                <td></td>
            </tr>
            </thead>
            <%
                for(Pedidos item : listap){
            %>
            <tbody>
            <tr>
                <td><%=item.getIdPedido()%></td>
                <td><%=item.getNombreCli()%></td>
                <td><%=item.getCiCli()%></td>
                <td><%=item.getTelefonoCli()%></td>
                <td><%=item.getIdEmp().getNombreEmp()%> <%=item.getIdEmp().getApellidoEmp()%></td>
                <td><%=item.getIdObj().getNombreObj()%></td>
                <td><%=item.getCtdPed()%></td>
                <td><%=item.getPago()%></td>
                <td><%=formatoFecha.format(item.getFechaEntrega())%></td>
                <th><a href="PedidosController?action=edit&id=<%= item.getIdPedido()%>">Editar</a></th>
                <th><a href="PedidosController?action=dele&id=<%= item.getIdPedido()%>" onclick="return(confirm('Esta seguro ?'))">Eliminar</a></th>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        </div>
        </section>
    </body>
</html>
