<%@page import="com.emergentes.JpaClases.Proveedores"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.JpaClases.Compras"%>
<%
    Compras compra = (Compras)request.getAttribute("compras");
    List<Proveedores> proveedores= (List<Proveedores>)request.getAttribute("proveedores");
    
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
        <h1>Registro de Compras</h1>
        <form action="ComprasController" method="post">
            <input type="hidden" name="id" value="<%=compra.getIdCompra()%>">
            <table>
                <tr>
                    <td>Descripcion de la Compra</td>
                    <td><input type="text" name="descripcion" value="<%=compra.getDescCompra()%>"id="username"></td>
                </tr>
                <tr>
                    <td>Cantidad Comprada</td>
                    <td><input type="number" name="ctd" value="<%=compra.getCtdCompra()%>"id="username"></td>
                </tr>
                 <tr>
                    <td>Proveedor</td>
                    <td>
                        <select name="idProv"id="username">
                            <%
                            for(Proveedores item : proveedores){
                            %>
                            <option value="<%=item.getIdProv()%>"
                                    ><%=item.getNombreProv()%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Costo de Compra</td>
                    <td><input type="number" name="monto" value="<%=compra.getMonto()%>"id="username"></td>
                </tr>
                <tr>
                    <td>Fecha de Compra</td>
                    <td><input type="date" name="fecha" value="<%=compra.getFechaCompra()%>"id="username"></td>
                </tr>
                <tr>
                    <td>Estado de Compra</td>
                    <td><input type="text" name="estado" value="<%=compra.getEstado()%>"id="username"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit"id="BOTON"></td>
                </tr>
            </table>
                
        </form>
       </div>
    </body>
</html>

