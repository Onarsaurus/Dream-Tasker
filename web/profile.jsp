<%-- 
    Document   : profile
    Created on : May 6, 2025, 12:10:42 AM
    Author     : turtl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <!-- bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="CSS/dreamTaskerStyles.css" rel="stylesheet">

    </head>
    <body class="bg-gradient">

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light rounded-bottom shadow-sm">
            <div class="container-fluid">
                <span class="navbar-brand fw-bold text-primary">Dream Tasker</span>
                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item"><a class="nav-link" href="Private?action=gotohome">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="Private?action=gotolists">Lists</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Budgeting</a></li>
                        <li class="nav-item"><a class="nav-link active" href="Private?action=gotoprofile">Profile</a></li>
                        <li class="nav-item"><a class="nav-link text-danger" href="Public?action=gotologin">Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Main Content -->
        <main class="container my-5">
            <div class="card shadow p-4 rounded bg-white">
                <h2 class="text-center text-primary mb-4">Profile</h2>
                <p class="text-center fs-5">Welcome, <c:out value="${storedUser.username}" /></p>

                <form action="Private" method="post" class="mt-4">
                    <input type="hidden" name="action" value="editprofile">

                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="text" name="email" class="form-control" value="<c:out value='${email}'/>">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Username</label>
                        <input type="text" name="username" class="form-control" value="<c:out value='${username}'/>">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Password</label>
                        <input type="text" name="password" class="form-control" value="<c:out value='${password}'/>">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Birth Date</label>
                        <input type="date" name="birthdate" class="form-control">
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-primary px-5">Save Changes</button>
                    </div>
                </form>
            </div>
        </main>
    </body>
</html>
