/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import business.User;
import business.Validation;
import data.DreamTaskerDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.realm.SecretKeyCredentialHandler;

/**
 *
 * @author turtl
 */
public class Public extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Public.class.getName());

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
        
        //The action parameter will determine what needs to be executed
        String action = request.getParameter("action");
        String url = "/index.jsp";
        
        if (action == null) {
            action = "default";
        }

        switch (action) {
            case "login": {
                HashMap<String, String> errors = new HashMap();

                String username = request.getParameter("username");
                String password = request.getParameter("password");

                User storedUser = new User();

                try {
                    storedUser = DreamTaskerDB.getUser(username);
                } catch (NamingException | SQLException ex) {
                    errors.put("Error", "Problem with the database, please try again later");
                }

                SecretKeyCredentialHandler ch;
                try {
                    ch = new SecretKeyCredentialHandler();
                    ch.setAlgorithm("PBKDF2WithHmacSHA256");
                    ch.setKeyLength(256);
                    ch.setSaltLength(16);
                    ch.setIterations(4096);
                    if (storedUser == null || !ch.matches(password, storedUser.getPassword())) {
                        errors.put("InvalidCredentials", "Your username or password is incorrect");
                    } else {
                        request.getSession().setAttribute("storedUser", storedUser);
                        url = "/Private?action=default";
                    }
                } catch (Exception ex) {
                    errors.put("Hash", "Problem with hashing password");
                }
                
                request.setAttribute("errors", errors);
                break;
            }
            case "register": {
                LinkedHashMap<String, String> errors = new LinkedHashMap();

                //checks email
                String email = request.getParameter("email");
                if (!Validation.isEmailValid(email).equals("")) {
                    errors.put("email", Validation.isEmailValid(email));
                } else try {
                    if (DreamTaskerDB.isValueTaken("email", email)) {
                        errors.put("email", "This email is already taken.");
                    }
                } catch (NamingException | SQLException ex) {
                    errors.put("general", "There was a problem with a database.");
                    LOG.log(Level.SEVERE, "Database naming exception or sql exception", ex);
                    request.setAttribute("errors", errors);
                }

                //checks username
                String username = request.getParameter("username");
                if (!Validation.isUsernameValid(username).equals("")) {
                    errors.put("username", Validation.isUsernameValid(username));
                    request.setAttribute("errors", errors);
                } else try {
                    if (DreamTaskerDB.isValueTaken("username", username)) {
                        errors.put("username", "This username is already taken.");
                        request.setAttribute("errors", errors);
                    }
                } catch (NamingException | SQLException ex) {
                    errors.put("general", "There was a problem with a database.");
                    LOG.log(Level.SEVERE, "Database naming exception or sql exception", ex);
                    request.setAttribute("errors", errors);
                }

                //checks password
                String password = request.getParameter("password");
                if (!Validation.isPasswordValid(password).equals("")) {
                    errors.put("password", Validation.isPasswordValid(password));
                    request.setAttribute("errors", errors);
                }

                //checks birthday
                LocalDate birthday = null;
                try {
                    birthday = LocalDate.parse(request.getParameter("birthdate"));
                } catch (DateTimeParseException | NullPointerException ex) {
                    errors.put("birthdate", "Date must be a valid date.");
                }

                if (birthday != null) {
                    if (!Validation.isBirthdateValid(birthday).equals("")) {
                        errors.put("birthdate", Validation.isBirthdateValid(birthday));
                    }
                }
                
                if (errors.isEmpty()) {
                    String hash = "";
                    SecretKeyCredentialHandler ch;

                    try {
                        ch = new SecretKeyCredentialHandler();
                        ch.setAlgorithm("PBKDF2WithHmacSHA256");
                        ch.setKeyLength(256);
                        ch.setSaltLength(16);
                        ch.setIterations(4096);

                        hash = ch.mutate(password);
                    } catch (Exception ex) {
                        LOG.log(Level.SEVERE, null, ex);
                        errors.put("hash", "Error with hashing algorithm.");
                        request.setAttribute("errors", errors);
                    }

                    password = hash;
                    System.out.println(password);

                    User user = new User(email, username, password, birthday);
                    try {
                        DreamTaskerDB.insertUser(user);
                        request.getSession().setAttribute("storedUser", user);
                        url = "/Private?action=gotohome";
                    } catch (NamingException | SQLException ex) {
                        errors.put("general", "There was a problem with a database.");
                        LOG.log(Level.SEVERE, "Problem registering User", ex);
                        request.setAttribute("errors", errors);
                    }
                } else {
                    request.setAttribute("email", email);
                    request.setAttribute("username", username);
                    request.setAttribute("password", password);
                    request.setAttribute("birthdate", birthday);

                    url = "/registration.jsp";
                }
                request.setAttribute("errors", errors);
                break;
            }
            case "gotologin": {
                url = "/index.jsp";
                break;
            }
            case "gotoregistration": {
                url = "/registration.jsp";
                break;
            }
            case "default": {
                url = "/index.jsp";
                break;
            }
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);

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
        return "Short description";
    }// </editor-fold>

}
