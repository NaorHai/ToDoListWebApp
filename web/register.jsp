<%@ page language="java" contentType="text/html; charset=windows-1255"
         import="java.util.*" import="com.todolist.controller.toDoServletController"
         pageEncoding="windows-1255" isErrorPage="false" errorPage="errorPage.jsp" %>
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

    <title>ToDo App</title>
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
            max-width: 330px;
            padding: 40px 40px;

        }

        .register-button {
            margin-top: 5px;
        }

        .go-to-login-button {
            display: block;
            margin: 0 auto;
        }

        .red {
            color: red;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row main">
        <div class="panel-heading">
            <div class="panel-title text-center">
                <h1 class="title">ToDo List</h1>
                <hr/>
                <h2 class="title">Register</h2>
            </div>
        </div>
        <div class="main-login main-center">
            <form class="form-horizontal" method="post" action="/todo/auth/">

                <div class="form-group">
                    <label for="name" class="cols-sm-2 control-label">First Name</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" required class="form-control" name="firstName" id="name"
                                   placeholder="Enter your First Name"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="username" class="cols-sm-2 control-label">Last Name</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                            <input type="text" required class="form-control" name="lastName" id="username"
                                   placeholder="Enter your Last Name"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="cols-sm-2 control-label">Email</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input type="text" required class="form-control" name="email" id="email"
                                   placeholder="Enter your Email"/>
                        </div>
                    </div>
                </div>
                <%
                    Cookie[] cookie = request.getCookies();
                    for (Cookie ck : cookie) {
                        if (ck.getName().equals("isUserAlreadyExist")) {
                            if(ck.getValue().equals("true")){
                            out.print("<p class='red'>Email already exist</p>");
                            }
                        }
                    }
                %>
                <div class="form-group">
                    <label for="password" class="cols-sm-2 control-label">Password</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input type="password" required class="form-control" name="password" id="password"
                                   placeholder="Enter your Password"/>
                        </div>
                    </div>
                </div>

                <div class="form-group ">
                    <button type="submit" name="action" value="registerAccount"
                            class="btn btn-primary btn-lg btn-block register-button">Register
                    </button>
                </div>
            </form>
            <div class="form-group ">
                <a href="/todo/goToLogin?action/goToLogin" name="action" value="goToLogin"
                   class="btn btn-link go-to-login-button">Login</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>