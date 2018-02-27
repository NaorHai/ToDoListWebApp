<%--<%@ page language="java" contentType="text/html; charset=windows-1255"--%>
         <%--import="java.util.*" import="com.todolist.controller.toDoServletController"--%>
         <%--pageEncoding="windows-1255" isErrorPage="false" errorPage="errorPage.jsp"%>--%>
<%--&lt;%&ndash;<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">&ndash;%&gt;--%>
<%--&lt;%&ndash;<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>&ndash;%&gt;--%>
<%--&lt;%&ndash;<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>&ndash;%&gt;--%>
<%--<!-- ---- Include the above in your HEAD tag -------- -->--%>

<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>

  <%--<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
  <%--<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>--%>
  <%--<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>--%>
  <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
  <%--<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">--%>

  <%--<!-- Website CSS style -->--%>
  <%--<link rel="stylesheet" type="text/css" href="assets/css/main.css">--%>

  <%--<!-- Website Font style -->--%>
  <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">--%>

  <%--<!-- Google Fonts -->--%>
  <%--<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>--%>
  <%--<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>--%>

  <%--<title>ToDo App</title>--%>

<%--</head>--%>
<%--<body>--%>
<%--<h1>Welcome To My Zone!</h1>--%>

<%--<form action = "" method = "post">--%>
  <%--<input type="submit" class="btn-success" name="createTask" value="Create Task" />--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>



<%@ page language="java" contentType="text/html; charset=windows-1255"
         import="java.util.*" import="com.todolist.controller.toDoServletController"
         pageEncoding="windows-1255" isErrorPage="false" errorPage="errorPage.jsp"%>
<%@ page import="com.todolist.pojo.User" %>
<%@ page import="com.todolist.pojo.Item" %>
<%@ page import="com.todolist.service.IToDoListService" %>
<%@ page import="com.todolist.service.IToDoListServiceImpl" %>
<%@ page import="com.todolist.service.UserService" %>
<%@ page import="com.todolist.service.UserServiceImpl" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!-- ---- Include the above in your HEAD tag -------- -->

<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">

  <!-- Website CSS style -->
  <link rel="stylesheet" type="text/css" href="assets/css/main.css">

  <!-- Website Font style -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

  <!-- Google Fonts -->
  <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

  <title>My Zone</title>
  <style type="text/css">
    body, html{
      height: 100%;
      background-repeat: no-repeat;
      background-color: #d3d3d3;
      font-family: 'Oxygen', sans-serif;
    }

    .main{
      /*margin-top: 70px;*/
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
    hr{
      width: 10%;
      color: #fff;
    }

    .form-group{
      margin-bottom: 15px;
    }

    label{
      margin-bottom: 15px;
    }

    input,
    input::-webkit-input-placeholder {
      font-size: 11px;
      padding-top: 3px;
    }

    .main-login{
      background-color: #fff;
      /* shadows and rounded borders */
      -moz-border-radius: 2px;
      -webkit-border-radius: 2px;
      border-radius: 2px;
      -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
      -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
      box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);

    }

    .main-center{
      margin-top: 30px;
      margin: 0 auto;
      max-width: 70%;
      padding: 40px 40px;

    }

    #logout{
      position: absolute;
        top: 5px;
        right: 5px;
        font-size: 16px;
    }
.panelL {
  margin-bottom: 10px;
  border-radius: 4px;
  border: 1px solid;
  border-color: #337ab7;

}
.panelHeadHead{
  color: #fff;
  background-color: #337ab7;
  border-color: #337ab7;
  border-bottom: 0;
  padding: 10px 15px;
  border-top-left-radius: 3px;
  border-top-right-radius: 3px;
}
    .go-to-register-button{
      display: block;
      margin: 0 auto;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="row main">
    <div class="panel-heading">
      <div class="panel-title text-center">
          <a href="todo/logOut" id="logout">LogOut</a>
          <h1 class="title">My Zone</h1>
        <hr />
        <h2 class="title">Your Tasks, Create, Update, Delete etc.</h2>
      </div>
    </div>
    <div class="main-login main-center">
      <form class="form-horizontal" method="post" action="/todo/auth/">

        <blockquote>
          <%
            Cookie[] cookie = request.getCookies();

            IToDoListService iToDoListService = new IToDoListServiceImpl();
            UserService userService = new UserServiceImpl();

            String email = "", firstName = "", lastName="";

			int val=0;
			for(Cookie ck:cookie) {
			    if(ck.getName().equals("firstName")){
			        firstName = ck.getValue();
                }
                if(ck.getName().equals("lastName")){
                lastName = ck.getValue();
              }
              if(ck.getName().equals("email")){
                email = ck.getValue();
              }
			}
            List<Item> userItems = iToDoListService.getItemsByUserId(email);
            out.print("Welcome, <b>" + firstName + " " +lastName + "</b><br>");
            out.print("<a href='/todo/deleteUser?action/deleteUser&email="+email+"'>Delete this user</a>");
            if(userItems.size()==0){

              out.print("<h3>No tasks have been defined yet.</h3>");

            }else{

              out.print("<h3><b><u>My Todo List:</b></u></h3>");
              for (int i = 0; i < userItems.size(); i++) {

                out.print("<div class=panelL>");
                  out.print("<div class=panelHeadHead>"+ userItems.get(i).getTitle()+ "</div>");
                  out.print("<div class" + "="+"panel-body>"+ userItems.get(i).getContent()+ "</div>");
                  out.print("<div class" + "="+"panel-footer>");
                out.print("<a href='/todo/deleteTask?action/deleteTask&taskId="+userItems.get(i).getItemId()+"'>Delete this task</a>");
                out.print("</div>");
                out.print("</div>");

              }
            }

          %>
        </blockquote>


        <div class="form-group ">
          <button name="action" value="goToCreateTask" class="btn btn-primary btn-lg btn-block">Create Task</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script type="text/javascript" src="assets/js/bootstrap.js"></script>
</body>
</html>

