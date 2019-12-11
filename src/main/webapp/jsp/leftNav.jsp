<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/library_style.css"
	rel="stylesheet" type="text/css" />
<title>Left Navigator</title>
</head>
<body>
	<div id="templatemo_sidebar">

		<c:if test="${sessionScope.loginType ne null }">
			<div class="sidebar_box">
			<div class="news_box">
				<ul>
					<c:if test="${sessionScope.loginType eq 'admin' }">
						<h3 align="left">Administrator Actions</h3>
						<li></li>
						<li><a
							href="<%=request.getContextPath()%>/servlet/ActionServlet?parameter=manageUser">Manage
								User</a></li>
						<li><a
							href="<%=request.getContextPath()%>/servlet/ActionServlet?parameter=managebooks">Manage
								Books</a></li>
								
						<li><a
							href="<%=request.getContextPath()%>/servlet/ActionServlet?parameter=manageLibrary">Manage
								Library</a></li>
					</c:if>
					<c:if test="${sessionScope.loginType eq 'staff' }">
						<h3>Staff Actions</h3>
						<li></li>
						<li><a
							href="<%=request.getContextPath()%>/servlet/ActionServlet?parameter=manageUser">Manage
								User</a></li>
						<li><a
							href="<%=request.getContextPath()%>/servlet/ActionServlet?parameter=managebooks">Manage
								Books</a></li>
								<li><a
							href="<%=request.getContextPath()%>/servlet/ActionServlet?parameter=manageEvents">Manage
								Events</a></li>
								<li><a
							href="<%=request.getContextPath()%>/servlet/ActionServlet?parameter=managGallery">Manage
								Gallery</a></li>
								<li><a
							href="<%=request.getContextPath()%>/servlet/ActionServlet?parameter=manageLibrary">Manage
								Library</a></li>
					</c:if>
					<c:if test="${sessionScope.loginType eq 'member' }">
						<h3>Member Actions</h3>
						<li></li>
						<li><a
							href="<%=request.getContextPath()%>/servlet/ActionServlet?parameter=managebooks">Books
								Issued </a></li>
						<li> <a
							href="<%=request.getContextPath()%>/servlet/ActionServlet?parameter=managedue">Due
								Amount </a></li>
					</c:if>
				</ul>
				</div>
				<div class="cleaner"></div>

			</div>
			<div class="sidebar_box_bottom" style="background: url(${pageContext.request.contextPath}/images/library_sidebar_section_bottom.jpg)"></div>
		</c:if>
		<div class="sidebar_box">

			<h2>Announcements</h2>

			<div class="news_box">
				<a href="#">Latest research on Neno Technology for betterment of
					digital communication..</a>
				<p class="post_info">
					Posted by <a href="#">Admin</a> on <span>April 30, 2017</span>
				</p>
			</div>

			<div class="news_box">
				<a href="#">Latest Books stock arrived for Computer science
					branch.</a>
				<p class="post_info">
					Posted by <a href="#">Admin</a> on <span>April 22, 2017</span>
				</p>
			</div>

			<div class="news_box">
				<a href="#">Please do not leave Fan and Lights on after reading
					in library..</a>
				<p class="post_info">
					Posted by <a href="#">Admin</a> on <span>April 14, 2017</span>
				</p>
			</div>

		</div>
		<div class="sidebar_box_bottom" style="background: url(${pageContext.request.contextPath}/images/library_sidebar_section_bottom.jpg)"></div>


	</div>
	<!-- end of sidebar -->
</body>
</html>