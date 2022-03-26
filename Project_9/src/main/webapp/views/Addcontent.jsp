
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style/editprofile_style.css" />
<jsp:useBean id="content" class="model.Content" />
<%
String checker = request.getParameter("id");
if (checker == null) {
	response.sendRedirect("/logout");
}
checker = request.getParameter("name");
if (checker == null) {
	response.sendRedirect("/logout");
}
int id = Integer.parseInt(request.getParameter("id"));
int idC = 0;
int sort = 0;
String title = "";
String brief = "";
String contentn = "";
int check = Integer.parseInt(request.getParameter("static"));
if (check == 0) {
	title = request.getParameter("title");
	brief = request.getParameter("brief");
	brief = brief.trim();
	contentn = request.getParameter("contentn");
	contentn = contentn.trim();
	idC = Integer.parseInt(request.getParameter("idC"));
	sort = Integer.parseInt(request.getParameter("sort"));
}
%>
<div id="loadding">
	<h1>Loading</h1>
</div>
<div id="content" style="display: none;">
	<div class="mid">
		<div class="title">
			<h2>
				<%
				if (check == 1) {
				%>
				Add Content
				<%
				}
				%>
				<%
				if (check == 0) {
				%>
				Update Content
				<%
				}
				%>
			</h2>
			<hr width="98%" align="center" color="#D5D5D5" size="2px" />
		</div>
		<%
		if (check == 1) {
		%>
		<form method="post" onsubmit="return validate()" action="AddContent">
			<%
			}
			%>
			<%
			if (check == 0) {
			%>
			<form method="post" onsubmit="return validate()"
				action="updateContent">
				<%
				}
				%>
				<div class="personal-details">
					<p>Content Form Elements</p>
					<center>
						<a class="text-danger">${mess}</a>
					</center>

					<div class="col-6">
						<span class="error" id="tid" class="text-danger"></span>
						<div class="inputs">
							<%
							if (id == 1) {
							%>
							<label><b>Title</b></label> <input maxlength="200" id="tiles"
								name="tiles" class="form-control" type="text"
								placeholder="Enter the Title" onclick="readonly_fuc()"
								value="<%=title%>">
							<%
							} else {
							%><label><b>Title</b></label> <input maxlength="200" id="tiles"
								name="tiles" class="form-control" type="text"
								placeholder="Enter the Title" value="<%=title%>">
							<%
							}
							%>
						</div>
						<%
						if (check == 0) {
						%>
						<div class="inputs">
							<%
							if (id == 1) {
							%>
							<label><b>Sort</b></label> <input min="0" id="sort"
								onclick="readonly_fuc()" name="sort" class="form-control"
								type="number" placeholder="Enter the sort" value="<%=sort%>"
								required>
							<%
							} else {
							%><label><b>Sort</b></label> <input min="0" id="sort" name="sort"
								class="form-control" type="number" placeholder="Enter the sort"
								value="<%=sort%>" required>
							<%
							}
							%>
						</div>
						<%
						}
						%>

					</div>
					<div class="row">
						<span class="error" id="bid" class="text-danger"></span>
						<div class="inputs">
							<%
							if (id == 1) {
							%>
							<label><b>Brief</b></label>
							<textarea id="brief2" name="brief2" onclick="readonly_fuc()"
								maxlength="150" class="form-control" type="text"
								placeholder="Enter self-description"><%=brief%></textarea>
							<%
							} else {
							%><label><b>Brief</b></label>
							<textarea id="brief2" name="brief2" maxlength="150"
								class="form-control" type="text"
								placeholder="Enter self-description"><%=brief%></textarea>
							<%
							}
							%>
						</div>
						<span class="error" id="cid" class="text-danger"></span>
						<div class="inputs">
							<%
							if (id == 1) {
							%>
							<label><b>Content</b></label>
							<textarea id="content2" name="content2" onclick="readonly_fuc()"
								maxlength="1000" class="form-controls" type="text"
								placeholder="Enter self-description"><%=contentn%></textarea>
							<%
							} else {
							%>
							<label><b>Content</b></label>
							<textarea id="content2" name="content2" 
								maxlength="1000" class="form-controls" type="text"
								placeholder="Enter self-description"><%=contentn%></textarea>
							<%
							}
							%>
						</div>
					</div>

					<div class="justify-content-end">
						<%
						if (id != 1) {
						%>
						<button onclick="myFunction()" type="submit" class="btn">Submit
							Button</button>
						<button type="reset" class="btn">Reset Button</button>
						<%
						}
						%>
						</a>

					</div>
					<input type="hidden" id="id" name="id" value="<%=id%>"> <input
						type="hidden" id="name" name="name" value="<%=checker%>">
					<input type="hidden" id="idC" name="idC" value="<%=idC%>">
					<input type="hidden" id="brief" name="brief"></input> <input
						type="hidden" id="content" name="content"></input>
				</div>
			</form>
	</div>
</div>
<script>
	setTimeout(function() {
		document.getElementById("loadding").style.display = "none";
		document.getElementById("content").style.display = "";
	}, 2000);

	function readonly_fuc() {
		document.getElementById("tiles").readOnly = true;
		document.getElementById("sort").readOnly = true;
		document.getElementById("content2").readOnly = true;
		document.getElementById("brief2").readOnly = true;
	}
	function myFunction() {
		var x = document.getElementById("brief2").value;
		document.getElementById("brief").value = x;
		var y = document.getElementById("content2").value;
		document.getElementById("content").value = y;
	}
	const tiles = document.querySelector("#tiles");
	const brief = document.querySelector("#brief2");
	const content = document.querySelector("#content2");

	function validate() {
		var t = document.getElementById("tiles").value;
		var b = document.getElementById("brief2").value;
		var c = document.getElementById("content2").value;
		const tid = document.getElementById("tid").innerHTML = "";
		document.getElementById("bid").innerHTML = "";
		document.getElementById("cid").innerHTML = "";
		var false_right = "";
		if (t == "") {
			document.getElementById("tid").innerHTML = " ** tiles must be fill";
			false_right = "1"
		} else if (t.length > 200) {
			document.getElementById("tid").innerHTML = " ** tiles length must be less than 200 ";
			false_right = "1"
		}
		if (b == "") {
			document.getElementById("bid").innerHTML = " ** Brief must be fill";
			false_right = "1"
		} else if (b.length > 150) {
			document.getElementById("bid").innerHTML = " ** Brief length must be less than 150";
			false_right = "1"
		}
		if (c == "") {
			document.getElementById("cid").innerHTML = " ** Content must be fill";
			false_right = "1"
		} else if (c.length > 1000) {
			document.getElementById("cid").innerHTML = " ** Content length must be less than 1000";
			false_right = "1"
		}
		if (false_right == "1")
			return false;
		return true;
	}
</script>
