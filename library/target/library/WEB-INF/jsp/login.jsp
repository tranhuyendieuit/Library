<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel='stylesheet' type='text/css' media='screen' href='./css/bootstrap.min.css'>
<link rel="stylesheet" href="./css/all.css" />
<link rel="stylesheet" href="./css/login-style.css" />
</head>
<body>
	<div class="container">
	    <div class="row">
	      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
	        <div class="card card-signin my-5">
	          <div class="card-body">
	            <h5 class="card-title text-center">Login</h5>
	            <small style="color: red;padding: 1em"><%=request.getAttribute("error") == null ? "" : request.getAttribute("error")%></small>
	            <form class="form-signin" action="login" method="post">
	              <div class="form-label-group">
	                <input type="text" id="inputUsername" name="inputUsername" class="form-control" placeholder="User" required autofocus>
	                <label for="inputUsername">User name</label>
	              </div>
	
	              <div class="form-label-group">
	                <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" required>
	                <label for="inputPassword">Password</label>
	              </div>
	
	              <div class="custom-control custom-checkbox mb-3">
	                <input type="checkbox" class="custom-control-input" id="customCheck1">
	                <label class="custom-control-label" for="customCheck1">Remember password</label>
	              </div>
	              <div class="text-center">
	              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Login</button>
	              </div>
	              <hr class="my-4">
	              <div class="text-center">
                  	<a class="small" href="#">Forgot password?</a>
                  </div>
                   <div class="text-center">
                  	Don't have an account?<a class="small" href="#">Register here</a>
                  </div>
	            </form>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	<script src='./js/bootstrap.min.js'></script>
	<script src='./js/all.js'></script>
	<script src='./js/bootstrap.bundle.min.js'></script>
	<script src='./js/popper.min.js'></script>
	<script src='./js/jquery-3.5.1-git.js'></script>
</body>
</html>