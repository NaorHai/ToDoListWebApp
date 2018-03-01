<%@ page language="java" contentType="text/html; charset=windows-1255"
         import="java.util.*" import="com.todolist.controller.toDoServletController"
         pageEncoding="windows-1255" isErrorPage="false" errorPage="errorPage.jsp" %>
<%@ page import="com.todolist.pojo.Item" %>
<%@ page import="com.todolist.service.IToDoListService" %>
<%@ page import="com.todolist.service.IToDoListServiceImpl" %>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<!-- ---- Include the above in your HEAD tag -------- -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

    <title>My Zone</title>
    <style type="text/css">
        body, html {
            height: 100%;
            background-repeat: no-repeat;
            background-color: #d3d3d3;
            font-family: 'Oxygen', sans-serif;
        }

        h1.title {
            font-size: 50px;
            font-family: 'Passion One', cursive;
            font-weight: 400;
        }

        h2.title {
            font-size: 30px;
            font-family: 'Passion One', cursive;
            font-weight: 400;
        }

        hr {
            width: 10%;
            color: #fff;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            margin-bottom: 15px;
        }

        input,
        input::-webkit-input-placeholder {
            font-size: 11px;
            padding-top: 3px;
        }

        .main-login {
            background-color: #fff;
            /* shadows and rounded borders */
            -moz-border-radius: 2px;
            -webkit-border-radius: 2px;
            border-radius: 2px;
            -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);

        }

        .main-center {
            margin-top: 30px;
            margin: 0 auto;
            max-width: 70%;
            padding: 40px 40px;

        }

        #logout {
            position: absolute;
            top: 5px;
            right: 5px;
            font-size: 16px;
        }

        .DeleteButton, .creationDate {
            float: right;
        }

        .wrapperMyTodo {
            margin-bottom: 5px;
        }

        .myTodo {
            display: inline;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row main">
        <div class="panel-heading">
            <div class="panel-title text-center">
                <a href="/todo/logOut" id="logout">LogOut</a>
                <h1 class="title">My Zone</h1>
                <hr/>
                <h2 class="title">Your Items, Create, Edit, Delete etc.</h2>
            </div>
        </div>
        <div class="main-login main-center">
            <form class="form-horizontal" method="post" action="/todo/auth/">

                <blockquote>
                    <%
                        Cookie[] cookie = request.getCookies();
                        IToDoListService iToDoListService = new IToDoListServiceImpl();
                        String email = "", firstName = "", lastName = "";

                        for (Cookie ck : cookie) {
                            if (ck.getName().equals("firstName")) {
                                firstName = ck.getValue();
                            }
                            if (ck.getName().equals("lastName")) {
                                lastName = ck.getValue();
                            }
                            if (ck.getName().equals("email")) {
                                email = ck.getValue();
                            }
                        }
                        List<Item> userItems = iToDoListService.getItemsByUserId(email);
                        out.print("Welcome, <b>" + firstName + " " + lastName + "</b><br>");
                        out.print("<a href='/todo/deleteUser?action/deleteUser&email=" + email + "'>Delete this user</a>");
                        if (userItems.size() == 0) {

                            out.print("<h3>No Items have been defined yet.</h3>");

                        } else {

                            out.print("<div class='wrapperMyTodo'><h3 class='MyTodo'><b><u>My Todo List:</b></u></h3>");
                            out.print("<a class='DeleteButton' href='/todo/deleteAllItems?action/deleteAllItems&email=" + email + "'>Delete All</a></div>");
                            for (int i = 0; i < userItems.size(); i++) {

                                out.print("<div class='panel panel-primary'>");
                                out.print("<div class='panel-heading'>" + userItems.get(i).getTitle() + "<span class='creationDate'>Created at: " + userItems.get(i).getCreationDate() + "</span></div>");
                                out.print("<div class='panel-body'>" + userItems.get(i).getContent() + "</div>");
                                out.print("<div class='panel-footer'>");
                                out.print("<a href='/todo/goToUpdateItem?action/goToUpdateItem&itemId=" + userItems.get(i).getItemId()
                                        + "&title=" + userItems.get(i).getTitle() + "&content=" + userItems.get(i).getContent() + "'>Edit</a>");
                                out.print("<a class='DeleteButton' href='/todo/deleteItem?action/deleteItem&itemId=" + userItems.get(i).getItemId() + "'>Delete</a>");
                                out.print("</div>");
                                out.print("</div>");
                            }
                        }
                    %>
                </blockquote>
                <div class="form-group ">
                    <button name="action" value="goToCreateItem" class="btn btn-primary btn-lg btn-block">Create Item
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>