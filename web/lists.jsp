<%-- 
    Document   : lists
    Created on : Apr 8, 2025, 3:30:28 PM
    Author     : turtl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>To Do Lists</title>
        <script type="text/javascript" src="jquery-3.3.1.js"></script>
        <script src="dreamTaskerLists.js"></script>
        <!-- bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="CSS/dreamTaskerStyles.css" rel="stylesheet">
    </head>
    <body class="bg-gradient" style="background: linear-gradient(to bottom right, #f3e8ff, #e0bbff); min-height: 100vh;">

        <!-- Header/Nav -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light rounded-bottom shadow-sm">
            <div class="container-fluid">
                <span class="navbar-brand fw-bold text-primary">Dream Tasker</span>
                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item"><a class="nav-link" href="Private?action=gotohome">Home</a></li>
                        <li class="nav-item"><a class="nav-link active" href="Private?action=gotolists">Lists</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Budgeting</a></li>
                        <li class="nav-item"><a class="nav-link" href="Private?action=gotoprofile">Profile</a></li>
                        <li class="nav-item"><a class="nav-link text-danger" href="Public?action=gotologin">Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Main Content -->
        <main class="container my-5">
            <h2 class="text-center text-primary mb-4">Your To-Do Lists</h2>

            <div class="table-responsive">
                <table class="table table-bordered table-hover bg-white rounded shadow-sm">
                    <thead class="table-light">
                        <tr>
                            <th>List Name</th>
                            <th>Items</th>
                            <th>Completed At</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lists}" var="list">
                            <tr>
                                <td class="fw-bold">${list.name}</td>
                                <td>
                                    <table class="table table-sm table-striped mb-0">
                                        <thead class="table-secondary">
                                            <tr>
                                                <th>Item Name</th>
                                                <th>Description</th>
                                                <th>Complete</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${list.items}" var="item">
                                                <tr>
                                                    <td>${item.name}</td>
                                                    <td>${item.description}</td>
                                                    <td>
                                                        <span class="badge bg-${item.complete ? 'success' : 'secondary'}">
                                                            ${item.complete ? 'Yes' : 'No'}
                                                        </span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </td>
                                <td>${list.completedAt}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Create List Button -->
            <div class="text-center mt-4">
                <form action="Private" method="get">
                    <input type="hidden" name="action" value="gotolistcreate">
                    <button type="submit" class="btn btn-primary rounded-pill px-4">+ Create New List</button>
                </form>
                <c:if test="${errors.get('general') != null}">
                    <div class="mt-3 text-danger">${errors.get("general")}</div>
                </c:if>
            </div>
        </main>
    </body>
</html>
