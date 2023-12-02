/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.emergentes.Login;

import com.emergentes.JpaClases.Empleados;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author taery
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("LedPixelUP");
        EntityManager em = emf.createEntityManager();
        try{
            List<Empleados> users =  em.createQuery("SELECT u FROM Empleados u WHERE u.usuario = :username AND u.contrasenia = :password",Empleados.class )
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getResultList();
            if (!users.isEmpty()) {
                response.sendRedirect("index.jsp");
            } else{
                System.out.println("eRROR");
            }
                
        }finally{
            em.close();
            emf.close();
        }
    }


}
