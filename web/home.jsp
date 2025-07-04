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
        <!-- bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
        <nav class="navbar navbar-expand-lg navbar-light bg-light rounded-bottom shadow-sm">
            <div class="container-fluid">
                <span class="navbar-brand fw-bold text-primary">Dream Tasker</span>
                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item"><a class="nav-link active" href="Private?action=gotohome">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="Private?action=gotolists">Lists</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Budgeting</a></li>
                        <li class="nav-item"><a class="nav-link" href="Private?action=gotoprofile">Profile</a></li>
                        <li class="nav-item"><a class="nav-link text-danger" href="Public?action=gotologin">Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Main Content -->
        <main class="container my-5">
            <h2 class="text-center text-primary mb-4">Your Task for Today</h2>

            <!-- To-Do Table -->
            <div class="card p-4 mb-4 shadow-lg">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped mb-0">
                        <thead class="table-light">
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

            <!-- Calendar -->
            <div id="calendar" class="shadow-lg mb-4"></div>

            <!-- Add Event Modal -->
            <div class="modal fade" id="eventModal" tabindex="-1" aria-labelledby="eventModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <form id="eventForm">
                        <div class="modal-content p-3">
                            <div class="modal-header">
                                <h5 class="modal-title" id="eventModalLabel">Add Event</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" id="event-start">
                                <input type="hidden" id="event-end">

                                <div class="mb-3">
                                    <label for="event-name" class="form-label">Event Name</label>
                                    <input type="text" class="form-control rounded-pill" id="event-name">
                                </div>

                                <div class="form-check mt-2">
                                    <input class="form-check-input" type="checkbox" id="event-all-day">
                                    <label class="form-check-label" for="event-all-day">All Day Event</label>
                                </div>

                                <div class="form-check mt-2">
                                    <input class="form-check-input" type="checkbox" id="event-recurring">
                                    <label class="form-check-label" for="event-recurring">Recurring Event</label>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary rounded-pill">Save Event</button>
                                <button type="button" class="btn btn-secondary rounded-pill" data-bs-dismiss="modal">Cancel</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>