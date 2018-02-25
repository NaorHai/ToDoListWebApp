<%@ page language="java" contentType="text/html; charset=windows-1255"
         import="java.util.*" import="com.todolist.controller.toDoServletController"
         pageEncoding="windows-1255" isErrorPage="false" errorPage="errorPage.jsp"%>
<%--<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
<%--<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>--%>
<%--<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>--%>
<!-- ---- Include the above in your HEAD tag -------- -->

<!DOCTYPE html>
<html lang="en">
<head>

  <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">

  <!-- Website CSS style -->
  <link rel="stylesheet" type="text/css" href="assets/css/main.css">

  <!-- Website Font style -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

  <!-- Google Fonts -->
  <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

  <title>ToDo App</title>

</head>
<body>
<h1>Welcome To My Zone!</h1>

<form action = "" method = "post">
  <input type="submit" class="btn-success" name="createTask" value="Create Task" />
</form>
</body>
</html>