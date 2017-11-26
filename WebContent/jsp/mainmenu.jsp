<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Library Home</title>
<link href="${pageContext.request.contextPath}/css/library_style.css" rel="stylesheet" type="text/css" />
</head>
<body>
		<div id="templatemo_menu_wrapper" style="background: url(${pageContext.request.contextPath}/images/library_menu.jpg) repeat-x;">
		<div id="templatemo_menu">

			<ul>
				<li><a  href="<%=request.getContextPath()%>/servlet/HomeServlet">Home</a></li>				
				<li><a href="#">Gallery</a></li>
				<li><a href="<%=request.getContextPath()%>/servlet/BookDetailServlet">Book Search</a></li>
				<li><a href="<%=request.getContextPath()%>/servlet/EventsServlet">Events</a></li>
				<li><a href="<%=request.getContextPath()%>/jsp/contactus.jsp">Contact Us</a></li>
				<c:if test="${sessionScope.loginUser ne null }">
					<li><a href="<%=request.getContextPath()%>/servlet/LoginServlet?param=logout">Logout</a></li>
				</c:if>
				<c:if test="${sessionScope.loginUser eq null }">
				<li><a href="<%=request.getContextPath()%>/servlet/LoginServlet?param=login">Login</a></li>
				</c:if>
			</ul>

		</div>
	</div>
</body>
</html>