<%-- 
    Document   : index
    Created on : Mar 24, 2025, 9:34:47 PM
    Author     : turtl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kirby's Dream Tasker</title>
        <link href="dreamTaskerStyles.css" rel="stylesheet">
        <!-- bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="CSS/dreamTaskerStyles.css" rel="stylesheet">
    </head>
    <body class="bg-gradient">

        <div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
            <div class="card shadow-lg p-4 rounded-4" style="max-width: 450px; width: 100%;">
                <div class="card-body">
                    <h2 class="card-title text-center mb-4 text-primary fw-bold">Welcome</h2>

                    <!-- Login Form -->
                    <form action="Public" method="post">
                        <input type="hidden" name="action" value="login">

                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" name="username" id="username"
                                   class="form-control rounded-pill"
                                   value="<c:out value='${username}'/>" >
                            <div class="form-text text-danger">
                                ${errors.get("username") == null ? "" : errors.get("username")}
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" name="password" id="password"
                                   class="form-control rounded-pill"
                                   value="<c:out value='${password}'/>" >
                            <div class="form-text text-danger">
                                ${errors.get("password") == null ? "" : errors.get("password")}
                            </div>
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-primary w-100 rounded-pill">Log In</button>
                        </div>
                    </form>

                    <!-- Redirect to Registration -->
                    <form action="Public" method="post" class="mt-3">
                        <input type="hidden" name="action" value="gotoregistration">
                        <button type="submit" class="btn btn-outline-secondary w-100 rounded-pill">Create an Account</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
