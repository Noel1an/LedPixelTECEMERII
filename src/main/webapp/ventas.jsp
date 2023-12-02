
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.JpaClases.Ventas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Ventas> listav =(List<Ventas>)request.getAttribute("ventas");
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html>
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
        <h1>VENTAS LED PIXEL</h1>
      
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
        <a class="boton_enlace" href="VentaController?action=add">NUEVO REGISTRO</a>

       
    </div>
       <div id="tablas">
        <br><br>
        <table>
            <thead>
            <tr>
                <td>ID</td>
                <td>OBJETO VENDIDO</td>
                <td>CANTIDAD VENDIDA</td>
                <td>EMPLEADO</td>
                <td>FECHA</td>
                <td></td>
                <td></td>
            </tr>
        </thead>
            <%
                for(Ventas item : listav){
            %>
            <tbody>
            <tr>
                <td><%=item.getIdVen()%></td>
                <td><%=item.getIdObjVen().getNombreObj()%></td>
                <td><%=item.getCtdVen()%></td>
                <td><%=item.getIdEmp().getNombreEmp()%> <%=item.getIdEmp().getApellidoEmp()%></td>
                <td><%=formatoFecha.format(item.getFecha())%></td>
                <th><a href="VentaController?action=edit&id=<%= item.getIdVen()%>">Editar</a></th>
                <th><a href="VentaController?action=dele&id=<%= item.getIdVen()%>" onclick="return(confirm('Esta seguro ?'))">Eliminar</a></th>
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
