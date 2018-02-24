<%@ page language="java" contentType="text/html; charset=windows-1255"
         import="java.util.*" import="com.todolist.controller.toDoServletController"
         pageEncoding="windows-1255" isErrorPage="false" errorPage="errorPage.jsp"%>
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

  <title>Admin</title>
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

    .main-center{
      margin-top: 30px;
      margin: 0 auto;
      max-width: 330px;
      padding: 40px 40px;

    }

    .login-button{
      margin-top: 5px;
    }

  </style>
</head>
<body>
<div class="container">
  <div class="row main">
    <div class="panel-heading">
      <div class="panel-title text-center">
        <h1 class="title">Welcome To ToDo List WebApp</h1>
      </div>
    </div>
    <div class="main-login main-center">
      <form class="form-horizontal" method="post" action="/todo/goToLogin/">
        <div class="form-group ">
          <button type="submit"  name="action" class="btn btn-primary btn-lg btn-block login-button">ENTER TO APP</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script type="text/javascript" src="assets/js/bootstrap.js"></script>
</body>
</html>