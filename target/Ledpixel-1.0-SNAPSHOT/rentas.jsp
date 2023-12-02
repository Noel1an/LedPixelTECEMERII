<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.emergentes.JpaClases.Rentas"%>
<%@page import="java.util.List"%>
<%
    List<Rentas> listar =(List<Rentas>)request.getAttribute("rentas");
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h1>LED PIXEL</h1>
     
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
        <a class="boton_enlace" href="RentasController?action=add">NUEVO REGISTRO</a>

    </div>

       
        <div id="tablas">
        <br><br>
        <table>
            <thead>
            <tr>
                <td>ID</td>
                <td>OBJETO RENTADO</td>
                <td>CANTIDAD RENTADA</td>
                <td>EMPLEADO</td>
                <td>MONTO DE RENTA</td>
                <td>FECHA DE ENTREGA</td>
                <td>FECHA DE DEVOLUCION</td>
                <td>NOMBRE DEL CLIENTE</td>
                <td>CI DEL CLIENTE</td>
                <td>TELEFONO DEL CLIENTE</td>
                <td></td>
                <td></td>
            </tr>
            </thead>
            <%
                for(Rentas item : listar){
            %>
            <tbody>
            <tr>
                <td><%=item.getIdRen()%></td>
                <td><%=item.getIdObjRen().getNombreObj()%></td>
                <td><%=item.getCtdRen()%></td>
                <td><%=item.getIdEmp().getNombreEmp()%> <%=item.getIdEmp().getApellidoEmp()%></td>
                <td><%=item.getMonto()%></td>
                <td><%=formatoFecha.format(item.getFechaEntrega())%></td>
                <td><%=formatoFecha.format(item.getFechaDev())%></td>
                <td><%=item.getNombreCliR()%></td>
                <td><%=item.getCi()%></td>
                <td><%=item.getTelefono()%></td>
                <th><a href="RentasController?action=edit&id=<%= item.getIdRen()%>">Editar</a></th>
                <th><a href="RentasController?action=dele&id=<%= item.getIdRen()%>" onclick="return(confirm('Esta seguro ?'))">Eliminar</a></th>
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
