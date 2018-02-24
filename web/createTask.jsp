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
      max-width: 330px;
      padding: 40px 40px;

    }

    .register-button{
      margin-top: 5px;
    }

    .go-to-login-button{
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
        <h1 class="title">ToDo List</h1>
        <hr />
        <h2 class="title">Register</h2>
      </div>
    </div>
    <div class="main-login main-center">
      <form class="form-horizontal" method="post" action="/todo/createTask/">

        <div class="form-group">
          <label for="title" class="cols-sm-2 control-label">Title</label>
          <div class="cols-sm-10">
            <div class="input-group">
              <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
              <input type="text" class="form-control" name="title" id="title"  placeholder="Enter your Title"/>
            </div>
          </div>
        </div>

        <div class="form-group">
          <label for="content" class="cols-sm-2 control-label">Content</label>
          <div class="cols-sm-10">
            <div class="input-group">
              <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
              <input type="text" class="form-control" name="content" id="content"  placeholder="Enter your Content"/>
            </div>
          </div>
        </div>

        <div class="form-group ">
          <button type="submit" name="action" value="createTask" class="btn btn-primary btn-lg btn-block">Create</button>
        </div>

      </form>
    </div>
  </div>
</div>

<script type="text/javascript" src="assets/js/bootstrap.js"></script>
</body>
</html>