<%@page import="com.emergentes.JpaClases.Empleados"%>
<%@page import="java.util.List"%>

<%
    List<Empleados> lista =(List<Empleados>)request.getAttribute("empleados");
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
  
        <a class="boton_enlace" href="MainController?action=add">NUEVO REGISTRO</a>

   
    </div>
        
        <div id="tablas">
        <br><br>
        
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>NOMBRE</th>
                <th>APELLIDOS</th>
                <th>CI</th>
                <th>CORREO</th>
                <th>USUARIO</th>
                <th>ROL</th>
                <th>SUELDO</th>
                <th></th>
                <th></th>
            </tr>
          </thead>
            <%
                for(Empleados item : lista){
            %>
            <tbody>
            <tr>
                <td><%= item.getIdEmp()%></td>
                <td><%= item.getNombreEmp()%></td>
                <td><%= item.getApellidoEmp()%></td>
                <td><%= item.getCiEmp()%></td>
                <td><%= item.getCorreoEmp()%></td>
                <td><%= item.getUsuario()%></td>
                <td><%= item.getIdRol().getNombreRol()%></td>
                <td><%= item.getSueldo()%> </td>
                <th><a href="MainController?action=edit&id=<%= item.getIdEmp()%>">Editar</a></th>
                <th><a href="MainController?action=dele&id=<%= item.getIdEmp()%>" onclick="return(confirm('Esta seguro ?'))">Eliminar</a></th>
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