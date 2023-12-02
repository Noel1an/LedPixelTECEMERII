
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="cssmenu.css" rel="stylesheet" type="text/css"/>
    </head>
    <h1>MENU PRINCIPAL</h1>
    <body>
        <div id="contenedor1">
            <br>
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
        <form action="ComprasController"method="get" id="form5">
            <input type="submit" value="COMPRAS" id="com">
        </form>
        <br>
        <form action="RentasController"method="get" id="form5">
            <input type="submit" value="RENTAS" id="com">
        </form>
    </div>

    </body>
</html>
