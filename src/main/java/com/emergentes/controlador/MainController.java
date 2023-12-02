
package com.emergentes.controlador;


import com.emergentes.JpaClases.Empleados;
import com.emergentes.JpaClases.Inventario;
import com.emergentes.JpaClases.Roles;
import com.emergentes.bean.BeanInventario;
import com.emergentes.bean.BeanLed;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        BeanLed daoEmp=new BeanLed();
        Empleados e= new Empleados();
        BeanLed rol = new BeanLed();
        List<Roles> rl;
        String action = (request.getParameter("action")!=null)? request.getParameter("action"):"view";
        switch(action){
            case "edit":
                id=Integer.parseInt(request.getParameter("id"));
                e = daoEmp.buscar(id);
                rl=rol.listarTodor();
                request.setAttribute("roles", rl);
                request.setAttribute("empleado",e );
                request.getRequestDispatcher("edit-emp.jsp").forward(request, response);
                break;
            case"dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoEmp.eliminar(id);
                response.sendRedirect("MainController");
                break;
            case "add":
            rl=rol.listarTodor();
            request.setAttribute("roles", rl);
            request.setAttribute("empleado",e );
            request.getRequestDispatcher("edit-emp.jsp").forward(request, response);
            case "view":
           List<Empleados> lista = daoEmp.listarTodos();
           request.setAttribute("empleados", lista);
           request.getRequestDispatcher("empleados.jsp").forward(request, response);
                break;
        }
           
        }
      
      
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanLed daoEmp=new BeanLed();
        BeanLed daoRol=new BeanLed();
       int id = Integer.parseInt(request.getParameter("idEmp"));
       String nombre =  request.getParameter("NombreEmp");
       String apellido =  request.getParameter("ApellidoEmp");
       String ci =  request.getParameter("ciEmp");
       String correo =  request.getParameter("CorreoEmp");
       String usuario =  request.getParameter("Usuario");
       String contrasenia =  request.getParameter("Contrasenia");
       int id_rol = Integer.parseInt(request.getParameter("rol_id"));
       float sueldo =  Float.parseFloat(request.getParameter("Sueldo"));
       
       Roles rol = daoRol.buscarr(id_rol);
       Empleados emp = new Empleados();
       emp.setNombreEmp(nombre);
       emp.setApellidoEmp(apellido);
       emp.setCiEmp(ci);
       emp.setCorreoEmp(correo);
       emp.setUsuario(usuario);
       emp.setContrasenia(contrasenia);
       emp.setSueldo(sueldo);
       emp.setIdRol(rol);
           if (id>0) {
               daoEmp.editar(emp);
        }else{
               daoEmp.insertar(emp);
           }
           response.sendRedirect("MainController");
    }
    }

