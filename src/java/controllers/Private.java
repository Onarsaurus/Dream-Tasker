/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import business.Event;
import business.ToDoItem;
import business.ToDoList;
import business.User;
import business.Validation;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import data.DreamTaskerDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
public class Private extends HttpServlet {

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

        //Gets the logged in user
        User storedUser = (User) request.getSession().getAttribute("storedUser");

        //Redirects the user if the user is not logged in
        if (storedUser == null) {
            response.sendRedirect("Public");
            return;
        } else {

        }

        //The action parameter will determine what needs to be executed
        String action = request.getParameter("action");
        String url = "/Private?action=default";

        //CHANGE EER DIAGRAM FOR EVENTS TO HAVE start_day and end_day, and allday reccuring!
        switch (action) {
            case "getevents": {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                try {
                    // Fetch all events for this user
                    ArrayList<Event> events = DreamTaskerDB.getEvents(storedUser);

                    // Transform to FUllCalendar foprmatwaeqt
                    JsonArray array = new JsonArray();
                    for (Event e: events ) {
                        JsonObject event = new JsonObject();
                        event.addProperty("id", e.getId());
                        event.addProperty("title", e.getName());
                        event.addProperty("start", e.getStartDay().toString());
                        event.addProperty("end", e.getEndDay().toString());
                        event.addProperty("allDay", e.isAllDay());
                        array.add(event);
                    }

                    response.getWriter().write(array.toString());

                    return; // VERY IMPORTANT: stop here, don’t forward to JSP
                } catch (NamingException | SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                    response.getWriter().write("[]"); // fallback empty array
                    return;
                }
            }
            case "saveevent": {
                //saves an event when user adds one on the calendar
                Gson gson = new Gson();
                JsonObject data = gson.fromJson(request.getReader(), JsonObject.class);
                
                int id = 1;
                String name = data.get("name").getAsString();
                LocalDate eventStart = null;
                LocalDate eventEnd = null;
                LocalDateTime eventStartTime = null;
                LocalDateTime eventEndTime = null;
                String description = null;
                
                
                //Figure out how to make these LocalDates/LocalDateTimes. LocalDate rn is fine as I only get back a date in yyyy-mm-dd format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                try {
                    eventStart = LocalDate.parse(data.get("start").getAsString(), formatter);
                    System.out.println("LocalDate: " + eventStart);
                } catch (DateTimeParseException e) {
                    System.err.println("Error parsing date string: " + e.getMessage());
                }
                try {
                    eventEnd = LocalDate.parse(data.get("end").getAsString(), formatter);
                    System.out.println("LocalDate: " + eventEnd);
                } catch (DateTimeParseException e) {
                    System.err.println("Error parsing date string: " + e.getMessage());
                }
                boolean allDay = data.get("allDay").getAsBoolean();
                boolean recurring = data.get("recurring").getAsBoolean();

                Event event = new Event(name, description, eventStart, eventEnd, eventStartTime, eventEndTime, allDay, recurring);

                //add event to database
                try {
                    id = DreamTaskerDB.insertEvent(event, storedUser);
                    
                    JsonObject result = new JsonObject();
                    result.addProperty("id", id);
                    result.addProperty("title", name);
                    result.addProperty("start", eventStart.toString());
                    result.addProperty("end", eventEnd.toString());
                    result.addProperty("allDay", allDay);
                    
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write(result.toString());
                    return;
                    
                } catch (NamingException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    return;
                }

                System.out.println(id + name + eventStart + eventEnd + allDay + recurring);
                break;
            }
            case "deleteevent": {

                break;
            }
            case "gotoprofile": {
                url = "/profile.jsp";
                break;
            }
            case "gotohome": {
                LinkedHashMap<String, String> errors = new LinkedHashMap();

                //Attemps to retreive all list from the user to display on list.jsp when redirected
                ArrayList<ToDoList> lists = null;
                try {
                    lists = DreamTaskerDB.getUsersCompleteLists(storedUser);

                } catch (NamingException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                    errors.put("general", "There was an sql problem in the database");
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                    errors.put("general", "There was an sql problem in the database");
                }

                //Send all event data to calendar to display all events.
                //Sets all list data for the page
                request.setAttribute("lists", lists);
                request.setAttribute("errors", errors);

                url = "/home.jsp";
                break;
            }
            case "gotolists": {
                LinkedHashMap<String, String> errors = new LinkedHashMap();

                //Attemps to retreive all list from the user to display on list.jsp when redirected
                ArrayList<ToDoList> lists = null;
                try {
                    lists = DreamTaskerDB.getUsersCompleteLists(storedUser);

                } catch (NamingException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                    errors.put("general", "There was an sql problem in the database");
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                    errors.put("general", "There was an sql problem in the database");
                }

                //Sets all list data for the page
                request.setAttribute("lists", lists);
                request.setAttribute("errors", errors);
                url = "/lists.jsp";
                break;
            }
            case "gotolistcreate": {
                url = "/listcreate.jsp";
                break;
            }
            case "createlist": {
                LinkedHashMap<String, String> errors = new LinkedHashMap();

                //get the parameters and make an ArrayList to store all list items
                String listName = request.getParameter("listName");
                String[] itemNames = request.getParameterValues("itemName");
                String[] itemDescs = request.getParameterValues("itemDescription");
                ArrayList<ToDoItem> items = new ArrayList<ToDoItem>();

                //itereate through the parameters, adding them to the items ArrayList
                for (int i = 0; i < itemNames.length; i++) {
                    String name = itemNames[i];
                    String desc = itemDescs[i];
                    ToDoItem item = new ToDoItem(name, desc);
                    items.add(item);
                }

                //creates the ToDoList object
                ToDoList list = new ToDoList(listName, items);

                //Attempts to add the ToDoList into the database
                try {
                    DreamTaskerDB.insertList(list, storedUser);
                    url = "/lists.jsp";
                } catch (NamingException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                    errors.put("general", "There was a naming problem with to do lists in the database");
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                    errors.put("general", "There was an sql problem with to do lists in the database");
                }

                //Iterates through the items ArrayList to add every item into the database
                for (int i = 0; i < items.size(); i++) {
                    ToDoItem item = items.get(i);
                    //Attemps to add the item into the database
                    try {
                        DreamTaskerDB.insertItem(item, list, storedUser);
                        url = "/lists.jsp";
                    } catch (NamingException ex) {
                        Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                        errors.put("general", "There was a naming problem with items in the database");
                        url = "/listcreate.jsp";
                    } catch (SQLException ex) {
                        Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                        errors.put("general", "There was an sql problem with items in the database");
                        url = "/listcreate.jsp";
                    }
                }

                //Attemps to retreive all list from the user to display on list.jsp when redirected
                ArrayList<ToDoList> lists = null;
                try {
                    lists = DreamTaskerDB.getUsersCompleteLists(storedUser);

                } catch (NamingException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                    errors.put("general", "There was an sql problem in the database");
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                    errors.put("general", "There was an sql problem in the database");
                }

                //Sets all list data for the page
                request.setAttribute("lists", lists);
                request.setAttribute("errors", errors);
                break;
            }
            case "editlist": {
                LinkedHashMap<String, String> errors = new LinkedHashMap();
                //THIS IS THE CURRENT GOAL. RE USE LISTCREATE.JSP (THIS ISNT MVC with VIEWS THO...) OR CREATE A NEW EDIT LIST JSP THAT IS AUTO FILLED WITH INFO. 
                url = "/lists.jsp";
                break;
            }
            case "deletelist": {
                LinkedHashMap<String, String> errors = new LinkedHashMap();

                //Gets all the parameters
                String listName = request.getParameter("listName");
                String option = request.getParameter("option");
                ToDoList list = null;

                try {
                    list = DreamTaskerDB.getList(storedUser, listName);
                    int rows = DreamTaskerDB.deleteList(list);
                } catch (NamingException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (option.equals("delete")) {
                    //DreamTaskerDB.deleteList(storedUser, list)
                }
                break;
            }
            case "edititem": {
                //IN TANDEM WITH EDIT LIST CASE MAYBE? OR EVEN completeitem?? CURRRENT GOAL TOO?
                break;
            }
            case "completeitem": {
                LinkedHashMap<String, String> errors = new LinkedHashMap();

                //gets the parameters
                boolean complete = request.getParameter("yesno").equals("yes");
                String listName = request.getParameter("listName");
                String itemName = request.getParameter("itemName");
                String itemDescription = request.getParameter("itemDescription");

                ToDoList list = null;
                try {
                    //get the list the item belongs to by using list name and stored user:
                    list = DreamTaskerDB.getList(storedUser, listName);
                    try {
                        DreamTaskerDB.updateItem(list, itemName, itemDescription, complete);
                        if (complete) {
                            //maybe remove item from the list? (visually/archived)
                        } else {

                        }
                    } catch (NamingException ex) {
                        Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (NamingException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }

                //Attemps to retreive all list from the user to display on list.jsp when redirected
                ArrayList<ToDoList> lists = null;
                try {
                    lists = DreamTaskerDB.getUsersCompleteLists(storedUser);

                } catch (NamingException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                    errors.put("general", "There was an sql problem in the database");
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                    errors.put("general", "There was an sql problem in the database");
                }

                //Sets all list data for the page
                request.setAttribute("lists", lists);
                request.setAttribute("errors", errors);
                url = "/lists.jsp";
                break;
            }
            case "deleteitem": {

            }
            case "editprofile": {
                LinkedHashMap<String, String> errors = new LinkedHashMap();

                //checks email
                String email = request.getParameter("email");
                if (!Validation.isEmailValid(email).equals("")) {
                    errors.put("email", Validation.isEmailValid(email));
                    url = "/profile.jsp";
                } else try {
                    if (DreamTaskerDB.isValueTaken("email", email)) {
                        errors.put("email", "This email is already taken.");
                        url = "/profile.jsp";
                    } else {
                        //storedUser.setEmail(email);
                    }
                } catch (NamingException | SQLException ex) {
                    errors.put("general", "There was a problem with the database");
                    request.setAttribute("errors", errors);
                    url = "/profile.jsp";
                }

                //checks username
                String username = request.getParameter("username");
                if (!Validation.isUsernameValid(username).equals("")) {
                    errors.put("username", Validation.isUsernameValid(username));
                    request.setAttribute("errors", errors);
                    url = "/profile.jsp";
                } else {
                    //storedUser.setUsername(username);
                }

                //checks password
                String password = request.getParameter("password");
                String hash = "";
                if (!password.isEmpty()) {
                    if (!Validation.isPasswordValid(password).equals("")) {
                        errors.put("password", Validation.isPasswordValid(password));
                        request.setAttribute("errors", errors);
                        url = "/profile.jsp";
                    } else {
                        hash = "";
                        SecretKeyCredentialHandler ch = null;
                        try {
                            ch = new SecretKeyCredentialHandler();
                            ch.setAlgorithm("PBKDF2WithHmacSHA256");
                            ch.setKeyLength(256);
                            ch.setSaltLength(16);
                            ch.setIterations(4096);

                            hash = ch.mutate(password);

                            //storedUser.setPassword(hash);
                        } catch (Exception ex) {
                            request.setAttribute("message", "invalid credentials");
                            url = "/profile.jsp";
                        }

                    }
                }

                //checks birthday
                LocalDate birthday = null;
                try {
                    birthday = LocalDate.parse(request.getParameter("birthdate"));
                } catch (DateTimeParseException | NullPointerException ex) {
                    errors.put("birthdate", "Date must be a valid date.");
                    request.setAttribute("errors", errors);
                    url = "/profile.jsp";
                }

                if (birthday != null) {
                    if (!Validation.isBirthdateValid(birthday).equals("")) {
                        errors.put("birthdate", Validation.isBirthdateValid(birthday));
                        request.setAttribute("errors", errors);
                        url = "/profile.jsp";
                    } else {
                        //storedUser.setBirthday(birthday);
                    }
                }

                //attemps to update user if no errors were encountered, and redirect the user to the login page if successful
                if (errors.isEmpty()) {
                    storedUser.setEmail(email);
                    storedUser.setUsername(username);
                    storedUser.setPassword(hash);
                    storedUser.setBirthday(birthday);
                    try {
                        DreamTaskerDB.updateUser(storedUser);
                        url = "/index.jsp";
                    } catch (NamingException | SQLException ex) {
                        errors.put("general", "There was a problem with the database.");
                        request.setAttribute("errors", errors);
                        url = "/profile.jsp";
                    }
                }

                request.setAttribute("errors", errors);
                break;
            }
            case "logout": {
                //Removes the user from the session
                request.getSession().setAttribute("storedUser", null);
                url = "/index.jsp";
                break;
            }
            case "default": {
                url = "/home.jsp";
                break;
            }
        }

        getServletContext()
                .getRequestDispatcher(url).forward(request, response);

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
