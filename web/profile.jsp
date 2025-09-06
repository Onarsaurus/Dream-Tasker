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
        <link href="CSS/dreamTaskerStyles.css" rel="stylesheet">
    </head>
    <body>

        <!-- Navbar -->
        <nav>
            <div>
                <span>Dream Tasker</span>
                <div>
                    <ul>
                        <li><a href="Private?action=gotohome">Home</a></li>
                        <li><a href="Private?action=gotolists">Lists</a></li>
                        <li><a href="#">Notes</a></li>
                        <li><a href="#">Budgeting</a></li>
                        <li><a href="Private?action=gotoprofile">Profile</a></li>
                        <li><a href="Public?action=gotologin">Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Main Content -->
        <main>
            <div>
                <h2>Profile</h2>
                <p>Welcome, <c:out value="${storedUser.username}" /></p>

                <form action="Private" method="post">
                    <input type="hidden" name="action" value="editprofile">

                    <div>
                        <label>Email</label>
                        <input type="text" name="email" value="<c:out value='${email}'/>">
                    </div>

                    <div>
                        <label>Username</label>
                        <input type="text" name="username" value="<c:out value='${username}'/>">
                    </div>

                    <div>
                        <label>Password</label>
                        <input type="text" name="password" value="<c:out value='${password}'/>">
                    </div>

                    <div>
                        <label>Birth Date</label>
                        <input type="date" name="birthdate">
                    </div>

                    <div>
                        <button type="submit">Save Changes</button>
                        <span>${errors.get("errors") == null ? "" : errors.get("errors")}</span>
                        <span>${errors.get("username") == null ? "" : errors.get("username")}</span>
                        <span>${errors.get("email") == null ? "" : errors.get("email")}</span>
                        <span>${errors.get("birthdate") == null ? "" : errors.get("birthdate")}</span>
                        <span>${errors.get("general") == null ? "" : errors.get("general")}</span>
                    </div>
                </form>
            </div>
        </main>
    </body>
</html>
