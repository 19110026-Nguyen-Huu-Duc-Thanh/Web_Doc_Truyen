<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String check = request.getParameter("static");
if (check == null || check.length() == 1) {
	check = "";
}
%>
<font class="text" size=6px><a><c:out value="<%=check%>" /></a></font>
