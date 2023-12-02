package com.emergentes.controlador;

import com.emergentes.JpaClases.Almacen;
import com.emergentes.JpaClases.Empleados;
import com.emergentes.JpaClases.Pedidos;
import com.emergentes.bean.BeanLed;
import com.emergentes.bean.BeanPedidos;
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
@WebServlet(name = "PedidosController", urlPatterns = {"/PedidosController"})
public class PedidosController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        BeanPedidos daoEmp=new BeanPedidos();
        Pedidos e= new Pedidos();
        BeanLed rol = new BeanLed();
        BeanPedidos daoVen = new BeanPedidos();
        
        List<Empleados> rl;
        List<Almacen>al;
        
        String action = (request.getParameter("action")!=null)? request.getParameter("action"):"view";
        switch(action){
            case "edit":
                id=Integer.parseInt(request.getParameter("id"));
                
                e = daoEmp.buscarp(id);
                
                al=daoVen.listarTodoa();
                request.setAttribute("almacen", al);
                
                rl=rol.listarTodos();
                request.setAttribute("empleado", rl);
                
                request.setAttribute("pedidos",e );
                request.getRequestDispatcher("edit-ped.jsp").forward(request, response);
                break;
            case"dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoEmp.eliminar(id);
                response.sendRedirect("PedidosController");
                break;
            case "add":
            rl=rol.listarTodos();
            al=daoVen.listarTodoa();
            request.setAttribute("empleado", rl);
            request.setAttribute("almacen", al);
            request.setAttribute("pedidos",e );
            request.getRequestDispatcher("edit-ped.jsp").forward(request, response);
            case "view":
            List<Pedidos> lista = daoEmp.listarTodop();
            request.setAttribute("pedido", lista);
            request.getRequestDispatcher("pedidos.jsp").forward(request, response);
                break;
    }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanPedidos daoped=new BeanPedidos();
        BeanPedidos daoal=new BeanPedidos();
        BeanLed daoemp =new BeanLed();
        Pedidos inv = new Pedidos();
        Date fcompra;
       int id = Integer.parseInt(request.getParameter("id"));
       String nombre =  request.getParameter("nombrecli");
       String ci=request.getParameter("cicli");
       String telefono = request.getParameter("telefono");
       int emple = Integer.parseInt(request.getParameter("idemp"));
       int idobj = Integer.parseInt(request.getParameter("idobj"));
       int ctd = Integer.parseInt(request.getParameter("cantidad"));
       int monto = Integer.parseInt(request.getParameter("Pago"));
       String fecha = request.getParameter("fecha");

       SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        try {
        fcompra = formatoFecha.parse(fecha);
        inv.setFechaEntrega(fcompra);
        } catch (ParseException ex) {
            Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
        }
       Empleados emp = daoemp.buscar(emple);
       Almacen obj=daoal.buscarr(idobj);
       
       inv.setNombreCli(nombre);
       inv.setCiCli(ci);
       inv.setTelefonoCli(telefono);
       inv.setIdEmp(emp);
       inv.setIdObj(obj);
       inv.setCtdPed(ctd);
       inv.setPago(monto);
       
       
       
           if (id>0) {
               daoped.editar(inv);
        }else{
               daoped.insertar(inv);
           }
           response.sendRedirect("PedidosController");
    }
    }// </editor-fold>


