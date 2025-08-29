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
        <script type="text/javascript" src="jquery-3.3.1.js"></script>
        <!-- Bootstrap js -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="Scripts/dreamTaskerCalendar.js"></script>
        <link href="CSS/dreamTaskerStyles.css" rel="stylesheet">
    </head>
    <body>
        
        <!-- Header/Nav -->
        <nav>
            <img src="Images/Dream Tasker Logo.png" alt="logo" width="200" height="200"/>
            <div>
                <span>Dream Tasker</span>
                <div>
                    <ul>
                        <li><a href="Private?action=gotohome">Home</a></li>
                        <li><a href="Private?action=gotolists">Lists</a></li>
                        <li><a href="#">Budgeting</a></li>
                        <li><a href="Private?action=gotoprofile">Profile</a></li>
                        <li><a href="Public?action=gotologin">Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>

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
            <span>${errors("general")}</span>

            <!-- Calendar -->
            <div id="calendar"></div>

            <!-- Add Event Modal -->
            <div id="eventModal" tabindex="-1" aria-labelledby="eventModalLabel" aria-hidden="true">
                <div>
                    <form id="eventForm">
                        <div>
                            <div>
                                <h5 id="eventModalLabel">Add Event</h5>
                                <button type="button" data-bs-dismiss="modal"></button>
                            </div>
                            <div>
                                <input type="hidden" id="event-start">
                                <input type="hidden" id="event-end">

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
                                <button type="submit">Save Event</button>
                                <button type="button" data-bs-dismiss="modal">Cancel</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>