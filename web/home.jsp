<%-- 
    Document   : home
    Created on : Mar 24, 2025, 10:25:00 PM
    Author     : turtl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <!-- FullCalendar.js -->
        <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.17/index.global.min.js'></script>
        <script src="Scripts/dreamTaskerCalendar.js"></script>
        <link href="CSS/dreamTaskerStyles.css" rel="stylesheet">
    </head>
    <body>
        
        <!-- Header/Nav -->
        <header id="header">
            <div id="brand">
                <img src="Images/Dream Tasker Logo.png" alt="logo" width="50" height="50"/>
                <h1>Dream Tasker</h1> 
            </div>

            <!-- Nav Bar -->
            <nav>
                <ul>
                    <li><a href="Private?action=gotohome">Home</a></li>
                    <li><a href="Private?action=gotolists">Lists</a></li>
                    <li><a href="#">Notes</a></li>
                    <li><a href="#">Budgeting</a></li>
                    <li><a href="Private?action=gotoprofile">Profile</a></li>
                    <li><a href="Public?action=gotologin">Logout</a></li>
                </ul>
            </nav>
        </header>

        <!-- Main Content -->
        <main>
            <h2>Your Task for Today</h2>

            <!-- To-Do Table -->
            <div>
                <div>
                    <table>
                        <thead>
                            <tr>
                                <th>To Do</th>
                                <th>Completed At</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lists}" var="list">
                                <tr>
                                    <td>${list.name}</td>
                                    <td>${list.completedAt}</td>                               
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <span>${errors.get("general") == null ? "" : errors.get("general")}</span>

            <!-- Calendar -->
            <div id="calendar"></div>

            <!-- Add Event Modal -->
            <div id="eventModal" tabindex="-1" >
                <div>
                    <form id="eventForm">
                        <div>
                            <div>
                                <h3 id="eventModalLabel">Add Event</h3>
                            </div>
                            <div>                              
                                <div>
                                    <label for="event-start">Event Start</label>
                                    <input id="event-start">
                                    <input id="event-end">
                                </div>
                                
                                <div>
                                    <label for="event-name">Event Name</label>
                                    <input type="text" id="event-name">
                                </div>

                                <div>
                                    <input type="checkbox" id="event-all-day">
                                    <label for="event-all-day">All Day Event</label>
                                </div>

                                <div>
                                    <input type="checkbox" id="event-recurring">
                                    <label for="event-recurring">Recurring Event</label>
                                </div>
                            </div>
                            <div>
                                <button type="submit" class="saveButton">Save Event</button>
                                <button type="button" class="closeModal">Cancel</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </main>
            
        <!-- Footer -->
        <footer> </footer>
    </body>
</html>