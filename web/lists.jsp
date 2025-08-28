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
        <link href="CSS/dreamTaskerStyles.css" rel="stylesheet">
    </head>
    <body>

        <!-- Header/Nav -->
        <nav>
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
            <h2>Your To-Do Lists</h2>

            <div>
                <table>
                    <thead>
                        <tr>
                            <th>List Name</th>
                            <th>Items</th>
                            <th>Completed At</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lists}" var="list">
                            <tr>
                                <td>${list.name}</td>
                                <td>
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>Item Name</th>
                                                <th>Description</th>
                                                <th>Complete?</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${list.items}" var="item">
                                                <tr>
                                                    <td>${item.name}</td>
                                                    <td>${item.description}</td>
                                                    <td>
                                                        <form action="Private" method="post">
                                                           <input type="hidden" name="action" value="completeitem">
                                                           <input type="hidden" name="listName" value="${list.name}">
                                                           <input type="hidden" name="itemName" value="${item.name}">
                                                           <input type="hidden" name="itemDescription" value="${item.description}">
                                                           <button type="submit" id="yes" name="yesno" value="yes"> 
                                                                Yes
                                                           </button>
                                                           <button type="submit" id="no" name="yesno" value="no">
                                                                No
                                                           </button>
                                                        </form>
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
            <div>
                <form action="Private" method="get">
                    <input type="hidden" name="action" value="gotolistcreate">
                    <button type="submit">+ Create New List</button>
                </form>
                <c:if test="${errors.get('general') != null}">
                    <div>${errors.get("general")}</div>
                </c:if>
            </div>
        </main>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
        <script src="Scripts/dreamTaskerLists.js"></script>
    </body>
</html>
