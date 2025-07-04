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
        <title>JSP Page</title>
        <script type="text/javascript" src="jquery-3.3.1.js"></script>
        <script src="Scripts/dreamTaskerLists.js"></script>
        <!-- bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="CSS/dreamTaskerStyles.css" rel="stylesheet">
    </head>
    <body class="bg-gradient" style="min-height: 100vh;">

        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-white rounded-bottom shadow-sm">
            <div class="container-fluid">
                <span class="navbar-brand fw-bold text-primary">Dream Tasker</span>
                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item"><a class="nav-link" href="Private?action=gotohome">Home</a></li>
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
            <div class="card shadow p-4 rounded bg-white">
                <h2 class="text-center text-primary mb-4">Create a New To-Do List</h2>

                <form action="Private" method="post">
                    <input type="hidden" name="action" value="createlist">

                    <div class="mb-3">
                        <label for="listName" class="form-label">Title</label>
                        <input type="text" id="listName" name="listName" class="form-control">
                    </div>

                    <div id="item_container">
                        <div class="item row g-3 mb-3">
                            <div class="col-md-5">
                                <label class="form-label">Item Name</label>
                                <input type="text" name="itemName" class="form-control">
                            </div>
                            <div class="col-md-7">
                                <label class="form-label">Description</label>
                                <input type="text" name="itemDescription" class="form-control">
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <button type="button" class="btn btn-outline-secondary" onclick="addItem()">+ Add Another Item</button>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-primary px-5">Add</button>
                    </div>

                    <c:if test="${errors.get('general') != null}">
                        <div class="mt-3 text-danger text-center">${errors.get("general")}</div>
                    </c:if>
                </form>
            </div>
        </main>
    </body>
</html>
