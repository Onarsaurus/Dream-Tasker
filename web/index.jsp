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
        <title>Dream Tasker</title>
        <link href="CSS/dreamTaskerStyles.css" rel="stylesheet">
    </head>
    <body>
        <div id="wrapper">
            <h2>Welcome</h2>

            <!-- Login Form -->
            <form action="Public" method="post">
                <input type="hidden" name="action" value="login">

                <div>
                    <label for="username">Username</label>
                    <input type="text" name="username" id="username"
                           value="<c:out value='${username}'/>" >
                    <div>
                        ${errors.get("InvalidCredentials") == null ? "" : errors.get("InvalidCredentials")}
                    </div>
                </div>

                <div>
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password"
                           value="<c:out value='${password}'/>" >
                    <div>
                        ${errors.get("InvalidCredentials") == null ? "" : errors.get("InvalidCredentials")}
                    </div>
                </div>

                <div>
                    <button type="submit">Log In</button>
                </div>
            </form>

            <!-- Redirect to Registration -->
            <form action="Public" method="post">
                <input type="hidden" name="action" value="gotoregistration">
                <button type="submit">Create an Account</button>
            </form>
            <span>${errors.get("Hash") == null ? "" : errors.get("Hash")}</span>
            <span>${errors.get("Error") == null ? "" : errors.get("Error")}</span>
        </div>
    </body>
</html>
