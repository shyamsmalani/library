<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Admin</title>

<link href="${pageContext.request.contextPath}/css/library_style.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript">
	function validate(form) {
		for (var i = 0; i < form.elements.length; i++) {
			if (form.elements[i].value == "") {
				alert("Fill out all Fields")
				document.loginForm.username.focus()
				return false
			}
		}

		if (!isNaN(document.loginForm.username.value)) {
			alert("User Name  must  be  char's & can't be null")
			document.loginForm.username.value = ""
			document.loginForm.username.focus()
			return false
		}

		if (!isNaN(document.loginForm.password.value)) {
			alert("Password  must  be  char's & can't be null")
			document.loginForm.password.value = ""
			document.loginForm.password.focus()
			return false
		}

		return true
	}
</script>
</head>
<body>
	<jsp:include page="/jsp/header.jsp"></jsp:include>

	<jsp:include page="/jsp/mainmenu.jsp"></jsp:include>

	<div id="templatemo_content_wrapper">

		<jsp:include page="/jsp/leftNav.jsp"></jsp:include>

		<div id="templatemo_content">

			<div class="content_box">

				<div valign="top">
					<form name="loginForm" action="BookDetailServlet" method="post">
						<table cellspacing="2" cellpadding="2">
							<tr>

								<td colspan="4"><font color="blue">Welcome To Book
										Search.</font></td>
							</tr>
							<tr>
								<td colspan="4"><font color="green"><c:out
											value="${requestScope.successMessage}"></c:out> </font> <font
									color="red"><c:out value="${requestScope.errorMessage}"></c:out>
								</font></td>
							</tr>
							<tr>
								<td colspan="4"></td>
							</tr>

							<tr>
								<td>Search By:</td>
								<td><select name="searchType">
										<option value="">-select-</option>
										<option value="ISBN">Book ISBN</option>
										<option value="title">Book Title</option>
										<option value="type">Book Type</option>
								</select></td>
							</tr>
							<tr>
								<td>Subject-Type:</td>
								<td><select name="booktype">
										<option value="">-select-</option>
										<c:forEach items="${requestScope.booktypes}" var="bookType">
											<option value="${bookType.key}"><c:out
													value="${bookType.value}"></c:out>
											</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td>Search String:</td>
								<td><input type="text" name="booksearch" /></td>
							</tr>
							<tr>
								<td>Fetch Only Available :</td>
								<td><input type="checkbox" name="onlyavail" /></td>
							</tr>
							<tr>
								<td>Books With Same Type :</td>
								<td><input type="checkbox" name="sametopic" /></td>
							</tr>

							<tr>
								<td colspan="4"></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" name="submittype" value="Search" /> <input
									type="reset" value="Reset" /></td>
							</tr>
						</table>

						<br />
						<c:if test="${requestScope.searchResult ne null }">

							<table border="1">
								<tr></tr>
								<tr>
									<td><font><strong>ISBN</strong></font></td>
									<td><font><strong>Title</strong></font></td>
									<td><font><strong>Subject</strong></font></td>
									<td><font><strong>Author</strong></font></td>
									<td><font><strong>Availability</strong></font></td>
									<c:if test="${sessionScope.loginUser ne null}">
										<td><font><strong>Lock Book</strong></font></td>
									</c:if>
									<c:if
										test="${sessionScope.loginUser ne null && (sessionScope.loginType eq 'admin' || sessionScope.loginType eq 'staff')}">
										<td><font><strong>Delete</strong></font></td>
									</c:if>
									<td><font><strong>About Book</strong></font></td>



								</tr>
								<c:forEach items="${requestScope.searchResult}" var="books">
									<tr>
										<td><font><c:out value="${books.isbn}"></c:out> </font></td>
										<td><font><c:out value="${books.booktitle}"></c:out></font>
										</td>
										<td><font><c:out value="${books.subjectType}"></c:out>
										</font></td>
										<td><font><c:out value="${books.author}"></c:out>
										</font></td>
										<c:choose>
											<c:when test="${books.availabilityStatus}">
												<td bgcolor="#ccffcc"><font>Yes</font></td>
											</c:when>
											<c:otherwise>
												<td bgcolor="#ff9999"><font>No</font></td>
											</c:otherwise>

										</c:choose>
										<c:choose>
											<c:when
												test="${sessionScope.loginUser ne null && books.availabilityStatus}">
												<td><input type="checkbox" name="lockId"
													value="${books.book_Id}" /></td>
											</c:when>
											<c:when
												test="${sessionScope.loginUser ne null && !books.availabilityStatus}">
												<td><input type="checkbox" name="lockId"
													disabled="disabled" value="${books.book_Id}" /></td>
											</c:when>

										</c:choose>

										<c:if
											test="${sessionScope.loginUser ne null && (sessionScope.loginType eq 'admin' || sessionScope.loginType eq 'staff')}">
											<c:choose>
											<c:when
												test="${books.availabilityStatus}">
												<td><input type="checkbox" name="deleteId"
												value="${books.book_Id}" /></td>
											</c:when>
											<c:when
												test="${!books.availabilityStatus}">
												<td><input type="checkbox" name="deleteId"
												value="${books.book_Id}" disabled="disabled"/></td>
											</c:when>

										</c:choose>
											
											
										</c:if>
										<td><font> <a href="#">Book Summary</a></font></td>
									</tr>
								</c:forEach>
															
							</table>
							<br/>
							<table>
							<tr>
								<td></td>
								<td><input type="submit" name="submittype" value="Submit" /> <input
									type="reset" value="Reset" /></td>
							</tr>
							</table>
						</c:if>
						<br />
					</form>
					<br></br>
				</div>


				<h3>
					<font color=blue> Library Latest Books Arrival and Important</font>
				</h3>

				<p>
					<a href="http://www.templatemo.com/page/1" target="_parent">Free
						Projects</a> are provided by <a href="http://www.javatpoint.com"
						target="_parent">javatpoint.com</a> for everyone. Feel free to
					download, edit and apply this project for your personal or business
					websites. Validate <a href="http://www.javatpoint.com">corejava</a>
					&amp; <a href="http://www.javatpoint.com">servlet</a>. Credit goes
					to <a href="http://www.javatpoint.com/">Public Domain Pictures</a>
					for photos used in this template. Nam ut libero at lacus feugiat
					tincidunt vitae sed ipsum.
				</p>

				<div class="cleaner_h20"></div>

				<div class="image_fl">
					<img
						src="${pageContext.request.contextPath}/images/library_images01.jpg"
						alt="image" />
				</div>

				<div class="section_w250 float_r">

					<ul class="list_01">
						<li>Praesent condimentum magna ut</li>
						<li>Nunc luctus eros eu enim gravida ut</li>
						<li>Phasellus nec ante eget felis</li>
						<li>Morbi pellentesque tellus adipiscing</li>
						<li>Nunc accumsan sagittis sem, ut</li>
						<li>Nunc luctus eros eu enim gravida ut</li>
						<li>Phasellus nec ante eget felis</li>
					</ul>

				</div>
				<div class="cleaner"></div>
			</div>
			<div class="content_box_bottom"></div>

		</div>
		<!-- end of content -->

		<div class="cleaner"></div>

	</div>

	<jsp:include page="/jsp/footer.jsp"></jsp:include>
</body>
</html>