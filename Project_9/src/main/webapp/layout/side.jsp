
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style/style.css">

<div class="sidenav">
	<%
	String id_check = request.getParameter("id");
	if (id_check == null) {
		id_check = "1";
	}
	String name = request.getParameter("name");
	if (name == null) {
		name = "";
	}
	int id = Integer.parseInt(id_check);
	%>
	<div style="border-bottom: 1px solid #eaeaea;">
		<form class="search" action="viewContentForm">

			<%
			String search = request.getParameter("search");
			if (search != null) {
			%>
			<input type="text" placeholder="Search.." name="search"
				value="<%=search%>">
			<%
			} else {
			%>
			<input type="text" placeholder="Search.." name="search">
			<%
			}
			%>

			<input type="hidden" id="id" name="id" value="<%=id%>"> <input
				type="hidden" id="name" name="name" value="<%=name%>">
			<button type="submit">
				<i class="fa fa-search"></i>
			</button>
		</form>
		<br> <br>
	</div>
	<form id="view" method="post" action="viewContentForm">
		<input type="hidden" id="id" name="id" value="<%=id%>"> <input
			type="hidden" id="name" name="name" value="<%=name%>"> <a
			onclick="document.getElementById('view').submit();"><i
			class="fa fa-calendar"></i>View Content</a>
	</form>
	<%
	if (id != 1) {
	%>
	<form id="add" method="post" action="add-content.tiles">
		<input type="hidden" id="id" name="id" value="<%=id%>"> <input
			type="hidden" id="name" name="name" value="<%=name%>"> <input
			type="hidden" id="static" name="static" value="1"> <a
			onclick="document.getElementById('add').submit();"><i
			class="fa fa-edit"></i> Form Content</a>
	</form>
	<%
	}
	if (id != 2) {
	%>
	<form id="view2" action="viewContentForm" method="get" target="_blank">
		<a onclick="document.getElementById('view2').submit();"><i
			class="fa fa-calendar"></i>View all Content</a>
	</form>
	<%
	}
	%>
</div>
