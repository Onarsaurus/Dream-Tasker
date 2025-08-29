<%-- 
    Document   : registration
    Created on : Mar 24, 2025, 10:07:29 PM
    Author     : turtl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link href="CSS/dreamTaskerStyles.css" rel="stylesheet">
    </head>
    <body>

        <div style="min-height: 100vh;">
            <div style="max-width: 450px; width: 100%;">
                <div>
                    <h2>Create Your Account</h2>

                    <!-- Registration Form -->
                    <form action="Public" method="post">
                        <input type="hidden" name="action" value="register">

                        <div>
                            <label for="email">Email</label>
                            <input type="text" name="email" id="email"
                                   value="<c:out value='${email}'/>" >
                            <div>
                                ${errors.get("email") == null ? "" : errors.get("email")}
                            </div>
                        </div>

                        <div>
                            <label for="username">Username</label>
                            <input type="text" name="username" id="username"
                                   value="<c:out value='${username}'/>" >
                            <div>
                                ${errors.get("username") == null ? "" : errors.get("username")}
                            </div>
                        </div>

                        <div>
                            <label for="password">Password</label>
                            <input type="password" name="password" id="password"
                                   value="<c:out value='${password}'/>" >
                            <div>
                                ${errors.get("password") == null ? "" : errors.get("password")}
                            </div>
                        </div>

                        <div>
                            <label for="birthdate">Birth Date</label>
                            <input type="date" name="birthdate" id="birthdate"
                                   value="${birthdate}" >
                            <div>
                                ${errors.get("birthdate") == null ? "" : errors.get("birthdate")}
                            </div>
                        </div>

                        <button type="submit">Register</button>
                    </form>

                    <!-- Redirect to Login -->
                    <form action="Public" method="post">
                        <input type="hidden" name="action" value="gotologin">
                        <button type="submit">Back to Login</button>
                    </form>
                    
                    <span>${errors.get("general") == null ? "" : errors.get("general")}</span>
                    <span>${errors.get("hash") == null ? "" : errors.get("hash")}</span>
                </div>
            </div>
        </div>
    </body>
</html>
