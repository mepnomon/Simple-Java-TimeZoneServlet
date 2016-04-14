package Task6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ICP-2052 - Mini Project 4: Java Servlets
 * Class: RandomFactServlet
 * Created: 2016-04-14
 * 
 * @author Dorian Dressler (eeu436), James Ashford (eeu48c)
 */
public class RandomFactServlet extends HttpServlet {

    private static int hitCounter = 0;

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Fact of the Day!</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Fact of the Day!</h1>");
            out.println("<p>" + getRandomFact() + "</p>");
            out.println("<p> Site hits " + hitCounter + "</p>");
            out.println("</body>");
            out.println("</html>");
            /* TODO output your page here. You may use following sample code. */

        }
    }
    
    /**
     * Retrieves a fact from a text file.
     * Does so by generating a pseudorandom number, which is used
     * to select a fact.
     * @return a String passage retrieved from a text file.
     */
    private String getRandomFact() {
        // Get a random number
        Random rand = new Random();
        
        int randomFactNo = rand.nextInt(30) + 1;
        String fact = "";
        try {
            final String FILE_NAME = "randomFacts.txt";
            final URI FILE_LOCATION = RandomFactServlet.class.getResource("/" + FILE_NAME).toURI();
            File file = new File(FILE_LOCATION);
            Scanner in = new Scanner(file);

            while (in.hasNext()) {
                String line = in.nextLine();
                String parts[] = line.split(":");
                int num = Integer.parseInt(parts[0]);

                if (num == randomFactNo) {
                    fact = parts[1];
                    break;
                }
            }

        } catch (URISyntaxException ex) {
            Logger.getLogger(RandomFactServlet.class.getName()).log(Level.SEVERE, null, ex);
            fact = "Error loading fact";
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RandomFactServlet.class.getName()).log(Level.SEVERE, null, ex);
            fact = "Error loading fact";
        }
        return fact;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        ++hitCounter;
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
        return "Returns a random fact and counts hits";
    }// </editor-fold>

}
