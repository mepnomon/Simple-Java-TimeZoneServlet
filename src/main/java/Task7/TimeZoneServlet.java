/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task7;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ICP-2052 - Mini Project 4: Java Servlets
 * Class: TimeZoneServlet
 * Created: 2016-03-12
 * 
 * @author Dorian Dressler (eeu436), James Ashford (eeu48c)
 */
public class TimeZoneServlet extends HttpServlet {
    private String city;
    private TimeZoneHandler timeZoneHandler;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TimeZoneServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String time = timeZoneHandler.getTime();
            if (!time.equals("not available")) {
                out.println("The current time in " + city + " is " + time);
            } else {
                out.println("Sorry, no information is availble for " + city);
            }
                    
            out.println("<form action=\"/TimeZoneServlet/SetTimeZone.xhtml\">"
                    + "<input type=\"submit\" value=\"Back\"/></form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        city = request.getParameter("city");
        timeZoneHandler = new TimeZoneHandler();
        timeZoneHandler.setCity(city);
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet retrieves the time at the chosen city";
    }// </editor-fold>

}
