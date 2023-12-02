<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="css.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div id="contenedor1">
    <h1>INCIO DE SESION</h1>
    <form action="Login" method="post">
        <label for="username"></label>
        <input type="text" id="username" name="username" placeholder="Username" required>
        <hr><br>
        <label for="password"></label>
        <input type="password" id="password" name="password" placeholder="Password" required>
        <hr><br>

        <button type="submit" id="boton">Login</button>
    </form>
    </div>

    <% if (request.getParameter("error") != null) { %>
        <p style="color: red;">Credenciales incorrectas. Inténtalo de nuevo.</p>
    <% } %>
</body>
</html>
