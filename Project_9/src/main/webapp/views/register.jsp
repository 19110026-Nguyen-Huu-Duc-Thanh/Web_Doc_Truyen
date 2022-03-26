<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<meta charset="UTF-8" />
<title>Register</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/register_style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body>
	<div class="container">
		<div class="title">Register</div>
		
		<form method="post" onsubmit="return validate()" action="insertUser" >
		<center>	 <p class="text-danger">${mess}</p></center>
			<div class="user-detail">
				<span class="error" id="uid" class="text-danger"></span>
				<div class="input-box">
					<span class="details"> </span> <input value="${user.username }" min="3" max="50" onblur="validate1()" name="username" id="username" type="text" autocomplete="off" placeholder="Enter username"/>
				</div>
				<span class="error" id="eid" class="text-danger"></span>
				<div class="input-box" >
					<span class="details"> </span> <input min="3" max="50" onblur="validate2()" onkeyup="check()" name="email" id="email" type="text" autocomplete="off" placeholder="Enter your email"/>
						
				</div>
				<div class="error-text">
               Please Enter Valid Email Address
            	</div>
				
				<span class="error" id="pid" class="text-danger"></span>
				<div class="input-box">
					<span class="details"> </span> <input input min="3" max="50" onblur="validate3()" name="password" id="password" type="password" autocomplete="off" placeholder="Enter your password"
						pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z].{8,}"/>
				</div>
				<span class="error" id="rid" class="text-danger"></span>
				<div class="input-box">
					<span class="details"> </span> <input input min="3" max="50" onblur="validate4()"  id="rpassword" type="password" autocomplete="off" placeholder="Confirm your password"/>
				</div>
			
			</div>
			<div class = "button">
    			<button type="submit" >Register </button>
			</div>
		</form>
			<script>
         const email = document.querySelector("#email");
         const error = document.querySelector(".error-text");
         const btn = document.querySelector("button");
         let regExp =  /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
         function check(){
           if(email.value.match(regExp)){
             email.style.borderColor = "#27ae60";
             email.style.background = "#eafaf1";
             error.style.display = "none";
             return true;
           }else{
             email.style.borderColor = "#e74c3c";
             email.style.background = "#fceae9";
             error.style.display = "block";
             return false;
           }
         }
         const user = document.querySelector("#username");
    	 const pass = document.querySelector("#password");
    	 const repass = document.querySelector("#rpassword");
    	 
         function validate1(){
             var u = document.getElementById("username").value;

        	 if (user.value == ""){
        		 user.style.borderColor = "#e74c3c";
                 user.style.background = "#fceae9";
                 error.style.display = "none";
        	 }
        	 else{
        		 user.style.borderColor = "#27ae60";
                 user.style.background = "#eafaf1";
                 error.style.display = "none";
               }
         }
         function validate2(){
        	 if (email.value == ""){
        		 email.style.borderColor = "#e74c3c";
                 email.style.background = "#fceae9";
                 error.style.display = "none";
        	 }
         }
         function validate3(){
        	 if (pass.value == "" ){
        		 pass.style.borderColor = "#e74c3c";
                 pass.style.background = "#fceae9";
                 error.style.display = "none";
        	 }
        	 else{
        		 pass.style.borderColor = "#27ae60";
                 pass.style.background = "#eafaf1";
                 error.style.display = "none";
               }
         }
         function validate4(){
        	 if (repass.value == "" || pass.value != repass.value){
        		 repass.style.borderColor = "#e74c3c";
                 repass.style.background = "#fceae9";
                 error.style.display = "none";
        	 }
        	 else{
        		 repass.style.borderColor = "#27ae60";
                 repass.style.background = "#eafaf1";
               }
         }
         function validate()  {
             var u = document.getElementById("username").value;
             var e = document.getElementById("email").value;
             var p = document.getElementById("password").value;
             var r = document.getElementById("rpassword").value;

             if(u== "") {
            	 document.getElementById("uid").innerHTML = " ** Username must be fill";
                 return false;
             }
             else if(u.length <3 || u.length > 30){
            	 document.getElementById("uid").innerHTML = " ** Username length must be between 3-30";
                 return false;
             }
             else if(e == "") {
            	 document.getElementById("eid").innerHTML = " ** E-mail must be fill";
                 return false;
             }
             else if(e.length<5 ){
            	 document.getElementById("eid").innerHTML = " ** Email length must be larger or equal to 5";
                 return false;
             }
             else if(p== "") {
            	 document.getElementById("pid").innerHTML = " ** Password must be fill";
                 return false;
             }
             else if(p.length<8 || p.length > 30){
            	 document.getElementById("pid").innerHTML = " ** Password length must be between 8-30";
                 return false;
             }
             else if(!(email.value.match(regExp))){
            	 document.getElementById("eid").innerHTML = " ** E-mail invalid";
                 return false;
             }
             else if(r== "") {
            	 document.getElementById("rid").innerHTML = " ** Confirm password must be fill";
                 return false;
             }
             else if(pass.value != repass.value){
            	 document.getElementById("rid").innerHTML = " ** Confirm password incorrect";
                 return false;
             }

             return true;
         }
      </script>
		<a	href="loginForm">Click here to Login</a>
	</div>

</body>

</html>