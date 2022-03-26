<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">



<style>
.text {
	color: #04B404
}
</style>

<div class="topnav">
	<font size=5px><a>CMS</a></font>
	<jsp:useBean id="user" class="model.User" />
	<%
	String id_check = request.getParameter("id");
	if (id_check == null) {
		id_check = "1";
	}
	int id = Integer.parseInt(id_check);
	String static_thing = request.getParameter("static");
	if (static_thing == null || static_thing.length() == 1) {
		static_thing = "";
	}
	String name = request.getParameter("name");
	if (name == null) {
		name = "";
	}
	%>
	<div class="topnav-right">
		<div class="dropdown" style="float: right;">

			<font size=6px><a><c:out value="<%=name%>" /></a></font>
			<button class="dropbtn">
				<i class="fa fa-user"></i> <i class="fa fa-caret-down"></i>
			</button>
			<div class="dropdown-content">
				<%
				if (name != "" && id != 1) {
				%>
				<form id="pro" method="post" action="editProfileForm">
					<input type="hidden" id="id" name="id" value="<%=id%>"> <input
						type="hidden" id="name" name="name" value="<%=name%>"> <a
						onclick="document.getElementById('pro').submit();"><i
						class="fa fa-user"></i> User Profile</a>
				</form>
				<a href="logout"><i class="fa fa-sign-out"></i> Logout &emsp;</a>
				<%
				} else {
				%>
				<a href="index.jsp">Login &emsp;</a>
				<%
				}
				%>
			</div>
		</div>
	</div>
</div>

