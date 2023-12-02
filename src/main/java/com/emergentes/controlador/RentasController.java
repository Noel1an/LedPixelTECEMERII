/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.emergentes.controlador;

import com.emergentes.JpaClases.Almacen;
import com.emergentes.JpaClases.Empleados;
import com.emergentes.JpaClases.Rentas;
import com.emergentes.bean.BeanLed;
import com.emergentes.bean.BeanPedidos;
import com.emergentes.bean.BeanRenta;
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

/**
 *
 * @author taery
 */
@WebServlet(name = "RentasController", urlPatterns = {"/RentasController"})
public class RentasController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        BeanRenta daoRen=new BeanRenta();
        Rentas v= new Rentas();
        BeanLed emp = new BeanLed();
        BeanPedidos ped = new BeanPedidos();
        
        List<Empleados> e;
        List<Almacen> al;
        
        String action = (request.getParameter("action")!=null)? request.getParameter("action"):"view";
        switch(action){
            case "edit":
                id=Integer.parseInt(request.getParameter("id"));
                v = daoRen.buscarren(id);
                
                e=emp.listarTodos();
                al=ped.listarTodoa();
                
                request.setAttribute("almacen", al);
                request.setAttribute("empleado", e);
                request.setAttribute("rentas",v );
                request.getRequestDispatcher("edit-ren.jsp").forward(request, response);
                
                break;
            case"dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoRen.eliminarren(id);
                response.sendRedirect("RentasController");
                break;
            case "add":
            e=emp.listarTodos();
            al=ped.listarTodoa();
            request.setAttribute("almacen", al);
            request.setAttribute("empleado", e);
            request.setAttribute("rentas",v );
            request.getRequestDispatcher("edit-ren.jsp").forward(request, response);
            case "view":
           List<Rentas> lista = daoRen.listarTodoren();
           request.setAttribute("rentas", lista);
           request.getRequestDispatcher("rentas.jsp").forward(request, response);
                break;
    }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanPedidos ped = new BeanPedidos();
        BeanLed daoEmp= new BeanLed();
        BeanRenta ven = new BeanRenta();
        Date fventa;
        Date Drenta;
        int id = Integer.parseInt(request.getParameter("id"));
        int ctd = Integer.parseInt(request.getParameter("cantidad"));
        int idObjv = Integer.parseInt(request.getParameter("idobj"));
        int idEmp = Integer.parseInt(request.getParameter("idemp"));
        float monto = Float.parseFloat(request.getParameter("monto"));
        String nombrecli = request.getParameter("nombrecli");
        String cicli = request.getParameter("cicli");
        String telefonocli = request.getParameter("telefonocli");
        
        Empleados emple= daoEmp.buscar(idEmp);
        Almacen al = ped.buscarr(idObjv);
        Rentas v= new Rentas();
        String fechae = request.getParameter("fechae");
        String fechad = request.getParameter("fechad");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        try {
        fventa = formatoFecha.parse(fechae);
        Drenta = formatoFecha.parse(fechad);
        v.setFechaEntrega(fventa);
        v.setFechaDev(Drenta);
        } catch (ParseException ex) {
            Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        v.setIdObjRen(al);
        v.setCtdRen(ctd);
        v.setIdEmp(emple);
        v.setMonto(monto);
        v.setNombreCliR(nombrecli);
        v.setCi(cicli);
        v.setTelefono(telefonocli);
        if (id>0) {
            ven.editarren(v);      
        }else{
            ven.insertarren(v);
        }
        response.sendRedirect("RentasController");
    }
    }

