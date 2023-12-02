/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.emergentes.controlador;

import com.emergentes.JpaClases.Compras;
import com.emergentes.JpaClases.Proveedores;
import com.emergentes.bean.BeanCompras;
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

@WebServlet(name = "ComprasController", urlPatterns = {"/ComprasController"})
public class ComprasController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        BeanCompras daoEmp=new BeanCompras();
        Compras e= new Compras();
        BeanCompras rol = new BeanCompras();
        List<Proveedores> ri;
        String action = (request.getParameter("action")!=null)? request.getParameter("action"):"view";
        switch(action){
            case "edit":
                id=Integer.parseInt(request.getParameter("id"));
                e = daoEmp.buscar(id);
                ri=rol.listarTodori();           
                request.setAttribute("proveedores", ri);
                request.setAttribute("compras",e );
                
                request.getRequestDispatcher("edit-com.jsp").forward(request, response);
                break;
            case"dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoEmp.eliminar(id);
                response.sendRedirect("ComprasController");
                break;
            case "add":
              ri=rol.listarTodori();
              request.setAttribute("proveedores", ri);
              request.setAttribute("compras",e );
              request.getRequestDispatcher("edit-com.jsp").forward(request, response);
            case "view":
           List<Compras> lista = daoEmp.listarTodoc();
           request.setAttribute("compras", lista);
           request.getRequestDispatcher("compras.jsp").forward(request, response);
                break;
    }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanCompras daoEmp=new BeanCompras();
        BeanCompras daoRol=new BeanCompras();
        Date fcompra;
        int id = Integer.parseInt(request.getParameter("id"));
        String objeto =  request.getParameter("descripcion");
        int cantidad = Integer.parseInt(request.getParameter("ctd"));
        int id_rolr = Integer.parseInt(request.getParameter("idProv"));
        float monto = Float.parseFloat(request.getParameter("monto"));
        String fecha = request.getParameter("fecha");
        Compras inv = new Compras();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        try {
        fcompra = formatoFecha.parse(fecha);
        inv.setFechaCompra(fcompra);
        } catch (ParseException ex) {
            Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String estado=request.getParameter("estado");
       Proveedores prov = daoRol.buscarr(id_rolr);
       
       inv.setDescCompra(objeto);
       inv.setCtdCompra(cantidad);
       inv.setIdProv(prov);
       inv.setMonto(monto);
       inv.setEstado(estado);
       
           if (id>0) {
               daoEmp.editar(inv);
        }else{
               daoEmp.insertar(inv);
           }
           response.sendRedirect("ComprasController");
    }
    }



