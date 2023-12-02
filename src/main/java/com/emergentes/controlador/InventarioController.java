
package com.emergentes.controlador;

import com.emergentes.JpaClases.Inventario;
import com.emergentes.JpaClases.Rolinventario;
import com.emergentes.bean.BeanInventario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InventarioController", urlPatterns = {"/InventarioController"})
public class InventarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              int id;
       BeanInventario daoEmp=new BeanInventario();
        Inventario e= new Inventario();
        BeanInventario rol = new BeanInventario();
        List<Rolinventario>ri;
        String action = (request.getParameter("action")!=null)? request.getParameter("action"):"view";
        switch(action){
            case "edit":
                id=Integer.parseInt(request.getParameter("id"));
                e = daoEmp.buscar(id);
                ri=rol.listarTodori();
                request.setAttribute("roles", ri);
                request.setAttribute("inventario",e );
                request.getRequestDispatcher("edit-inv.jsp").forward(request, response);
                break;
            case"dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoEmp.eliminar(id);
                response.sendRedirect("InventarioController");
                break;
            case "add":
             ri=rol.listarTodori();
              request.setAttribute("roles", ri);
              request.setAttribute("inventario",e );
              request.getRequestDispatcher("edit-inv.jsp").forward(request, response);
            case "view":
           List<Inventario> lista = daoEmp.listarTodoi();
           request.setAttribute("inventario", lista);
           request.getRequestDispatcher("inventario.jsp").forward(request, response);
                break;
    }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanInventario daoEmp=new BeanInventario();
        BeanInventario daoRol=new BeanInventario();
       int idr = Integer.parseInt(request.getParameter("idInv"));
       String objeto =  request.getParameter("ObjInv");
       int cantidad = Integer.parseInt(request.getParameter("CantInv"));
       int id_rolr = Integer.parseInt(request.getParameter("rol_idr"));
       Rolinventario rol = daoRol.buscarr(id_rolr);
       Inventario inv = new Inventario();
       inv.setObjInv(objeto);
       inv.setCantInv(cantidad);
       inv.setRolInv(rol);
           if (idr>0) {
               daoEmp.editar(inv);
        }else{
               daoEmp.insertar(inv);
           }
           response.sendRedirect("InventarioController");
    }

}
