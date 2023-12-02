<%@page import="com.emergentes.JpaClases.Rolinventario"%>
<%@page import="com.emergentes.JpaClases.Inventario"%>
<%@page import="java.util.List"%>
<%
    Inventario inventario = (Inventario)request.getAttribute("inventario");
    List<Rolinventario> roles= (List<Rolinventario>)request.getAttribute("roles");
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
        <h1>Registro de Inventario</h1>
        <form action="InventarioController" method="post">
            <input type="hidden" name="idInv" value="<%=inventario.getIdInv()%>">
            <table>
                <tr>
                    <td>OBJETO</td>
                    <td><input type="text" name="ObjInv" value="<%=inventario.getObjInv()%>" id="username"></td>
                </tr>
                <tr>
                    <td>CANTIDAD</td>
                    <td><input type="number" name="CantInv" value="<%=inventario.getCantInv()%>"id="username"></td>
                </tr>
                <tr>
                    <td>ROL DE OBJETO</td>
                    <td>
                        <select name="rol_idr" id="username">
                            <%
                            for(Rolinventario item : roles){
                            %>
                            <option value="<%=item.getIdRI()%>"
                                    ><%=item.getNRinv()%></option>
                            <%
                                }
                            %>
                        </select>
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

