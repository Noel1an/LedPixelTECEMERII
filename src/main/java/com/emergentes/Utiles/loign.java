
package com.emergentes.Utiles;

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
@WebServlet(name = "loign", urlPatterns = {"/loign"})
public class loign extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String username = request.getParameter("Usuario");
        String password = request.getParameter("Contrasenia");
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("LedPixelUP");
        EntityManager em = emf.createEntityManager();
        try{
            List<Empleados> users = (List<Empleados>) em.createQuery("SELECT * FROM Empleados WHERE Usuario = :username AND Contrasenia = :password",Empleados.class )
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }


}
