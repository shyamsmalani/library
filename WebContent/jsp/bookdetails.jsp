<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Admin</title>

<link href="${pageContext.request.contextPath}/css/library_style.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript">
	function validate() {
		var searchBy = document.bookForm.searchType.value;
		if (!isNaN(searchBy)) {
			alert("Search By Must be selected.")
			document.bookForm.searchType.value = ""
			document.bookForm.searchType.focus()
			return false
		}
		if ((searchBy == 'title' || searchBy == 'ISBN') && document.bookForm.booksearch.value == null) {
			alert("Search String must not be blank.")
			document.bookForm.booksearch.value = ""
			document.bookForm.booksearch.focus()
			return false
		}
		if (searchBy == 'type' && !isNaN(document.bookForm.booktype.value)) {
			alert("Subject-Type must not be blank.")
			document.bookForm.booktype.value = ""
			document.bookForm.booktype.focus()
			return false
		}
		return true
	}

	try {
		function loadDoc() {
			if(document.bookForm.searchType.value == 'type'){
				document.getElementById("subjectType").style.visibility="visible";
			}else{
				document.bookForm.booktype.value = ""
				document.getElementById("subjectType").style.visibility="collapse";
			}
		}		
	} catch (err) {
		alert(err.message);
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
					<form name="bookForm" action="BookDetailServlet" method="post">
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
								<td><select name="searchType" onchange="loadDoc()">
										<option value="">-select-</option>
										<option value="ISBN">Book ISBN</option>
										<option value="title">Book Title</option>
										<option value="type">Book Type</option>
								</select></td>
							</tr>
							<tr style="visibility:collapse;" id="subjectType">
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
								<td><input type="submit" name="submittype" value="Search"
									onclick="return validate()" /> <input type="reset"
									value="Reset" /></td>
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
												<c:when test="${books.availabilityStatus}">
													<td><input type="checkbox" name="deleteId"
														value="${books.book_Id}" /></td>
												</c:when>
												<c:when test="${!books.availabilityStatus}">
													<td><input type="checkbox" name="deleteId"
														value="${books.book_Id}" disabled="disabled" /></td>
												</c:when>

											</c:choose>


										</c:if>
										<td><font> <a href="#">Book Summary</a></font></td>
									</tr>
								</c:forEach>

							</table>
							<br />
							<table>
								<tr>
									<td></td>
									<td><input type="submit" name="submittype" value="Submit" />
										<input type="reset" value="Reset" /></td>
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
					The <font color=blue>EDU Library</font> is a place for reading, studying,
					and writing; participating in programs, activities and meetings,
					and, for using or borrowing Library materials. We have world class books and biggest stocks of different books. Our aim to evolve our services and materials keeps us updating with new stocks of books and other reading material.
					Please find below list of new Arrivals:
				</p>

				<div class="cleaner_h20"></div>

				<div class="image_fl">
					<img
						src="${pageContext.request.contextPath}/images/library.jpg"
						alt="image" />
				</div>

				<div class="section_w250 float_r">

					<ul class="list_01">
						<li>Exit West - <font color=blue>Mohsin Hamid</font></li>						
						<li>Little Fires Everywhere - <font color=blue>Celeste Ng </font></li>
						<li>Manhatten Beach - <font color=blue>Jennifer Egan</font></li>
						<li>Test Of West - <font color=blue>K. S kamat</font></li>
						<li>My Story - <font color=blue>A. S. Sainy</font></li>
						<li>Lincon in the Bardo - <font color=blue>George Sounders</font></li>
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