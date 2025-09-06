<%-- 
    Document   : listcreate
    Created on : Apr 8, 2025, 10:46:35 PM
    Author     : turtl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a List!</title>
        <!-- CSS -->
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
                <h2>Create a New To-Do List</h2>

                <form action="Private" method="post">
                    <input type="hidden" name="action" value="createlist">

                    <div>
                        <label for="listName">Title</label>
                        <input type="text" id="listName" name="listName">
                    </div>

                    <div id="item_container">
                        <div>
                            <label>Item Name</label>
                            <input type="text" name="itemName">
                            <label>Description</label>
                            <input type="text" name="itemDescription">
                        </div>
                    </div>

                    <!-- JavaScript to add more list items -->
                    <div>
                        <button id="itemAdd" type="button" >+ Add Another Item</button>
                    </div>

                    <div>
                        <button type="submit">Create List</button>
                    </div>

                    <c:if test="${errors.get('general') != null}">
                        <div>${errors.get("general") == null ? "" : errors.get("general")}</div>
                    </c:if>
                </form>
            </div>
        </main>
        <!-- java script -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
        <script src="Scripts/dreamTaskerListCreate.js"></script>
    </body>
</html>
