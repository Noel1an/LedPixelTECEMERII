
package com.emergentes.controlador;

import com.emergentes.JpaClases.Almacen;
import com.emergentes.JpaClases.Empleados;
import com.emergentes.JpaClases.Pedidos;
import com.emergentes.JpaClases.Ventas;
import com.emergentes.bean.BeanLed;
import com.emergentes.bean.BeanPedidos;
import com.emergentes.bean.BeanVenta;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VentaController", urlPatterns = {"/VentaController"})
public class VentaController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        BeanVenta daoVen=new BeanVenta();
        Ventas v= new Ventas();
        BeanLed emp = new BeanLed();
        BeanPedidos ped = new BeanPedidos();
        
        List<Empleados> e;
        List<Almacen> al;
        
        String action = (request.getParameter("action")!=null)? request.getParameter("action"):"view";
        switch(action){
            case "edit":
                id=Integer.parseInt(request.getParameter("id"));
                v = daoVen.buscarv(id);
                
                e=emp.listarTodos();
                al=ped.listarTodoa();
                
                request.setAttribute("almacen", al);
                request.setAttribute("empleado", e);
                request.setAttribute("venta",v );
                request.getRequestDispatcher("edit-ven.jsp").forward(request, response);
                
                break;
            case"dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoVen.eliminar(id);
                response.sendRedirect("VentaController");
                break;
            case "add":
            e=emp.listarTodos();
            al=ped.listarTodoa();
            request.setAttribute("almacen", al);
            request.setAttribute("empleado", e);
            request.setAttribute("venta",v );
            request.getRequestDispatcher("edit-ven.jsp").forward(request, response);
            case "view":
           List<Ventas> lista = daoVen.listarTodov();
           request.setAttribute("ventas", lista);
           request.getRequestDispatcher("ventas.jsp").forward(request, response);
                break;
    }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanPedidos ped = new BeanPedidos();
        BeanLed daoEmp= new BeanLed();
        BeanVenta ven = new BeanVenta();
        Date fventa;
        int id = Integer.parseInt(request.getParameter("id"));
        int ctd = Integer.parseInt(request.getParameter("cantidad"));
        int idObjv = Integer.parseInt(request.getParameter("idobj"));
        int idEmp = Integer.parseInt(request.getParameter("idemp"));
        System.out.println("id del elemento " +id);
        Empleados emple= daoEmp.buscar(idEmp);
        Almacen al = ped.buscarr(idObjv);
        Ventas v= new Ventas();
        
        String fecha = request.getParameter("fecha");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        try {
        fventa = formatoFecha.parse(fecha);
        v.setFecha(fventa);
        } catch (ParseException ex) {
            Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        v.setCtdVen(ctd);
        v.setIdEmp(emple);
        v.setIdObjVen(al);
        if (id>0) {
            ven.editar(v);      
        }else{
            ven.insertar(v);
        }
        response.sendRedirect("VentaController");
    }
}
