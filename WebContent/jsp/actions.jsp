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
				document.actionForm.username.focus()
				return false
			}
		}

		if (!isNaN(document.actionForm.username.value)) {
			alert("User Name  must  be  char's & can't be null")
			document.actionForm.username.value = ""
			document.actionForm.username.focus()
			return false
		}

		if (!isNaN(document.actionForm.password.value)) {
			alert("Password  must  be  char's & can't be null")
			document.actionForm.password.value = ""
			document.actionForm.password.focus()
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
					<form name="actionForm" action="ActionServlet" method="post">


						<c:if
							test="${(sessionScope.loginType eq 'admin' || sessionScope.loginType eq 'staff') && requestScope.parameter eq 'managebooks' }">
							<input type="hidden" name="parameter" value="managebooks" />
							<table cellspacing="2" cellpadding="2">
								<tr>

									<td colspan="4"><font color="blue">Welcome To Book
											Registration.</font></td>
								</tr>
								<tr>
									<td colspan="4"></td>
								</tr>
								<tr>
									<td colspan="4"><font color="green"><c:out
												value="${requestScope.successMessage}"></c:out> </font> <font
										color="red"><c:out value="${requestScope.errorMessage}"></c:out>
									</font></td>
								</tr>
								<tr>
									<td>Book-Type:</td>
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
									<td>Book Title:</td>
									<td><input type="text" name="booktitle" /></td>
								</tr>
								<tr>
									<td>Book ISBN:</td>
									<td><input type="text" name="isbn" /></td>
								</tr>
								<tr>
									<td>Author Name:</td>
									<td><input type="text" name="author" /></td>
								</tr>
								<tr>
									<td>About Book:</td>
									<td><textarea name="description" rows="6" cols="40"></textarea>
									</td>
								</tr>
								<tr>
									<td colspan="4"></td>
								</tr>

							</table>
							<table>
								<tr>
									<td></td>
									<td><input type="submit" value="Register" /> <input
										type="reset" value="Reset" /></td>
									<td><a
										href="<%=request.getContextPath()%>/servlet/BookDetailServlet">Book Search
											</a></td>

								</tr>
							</table>

						</c:if>
						<c:if
							test="${(sessionScope.loginType eq 'admin' || sessionScope.loginType eq 'staff') && requestScope.parameter eq 'manageUser' }">
							<input type="hidden" name="parameter" value="manageUser" />
							<div>
								<font color="blue" size="2"><strong>Activate/Delete
										Users.</strong> </font>
							</div>
							<br />

							<div>
								<font color="green"><c:out
										value="${requestScope.successMessage}"></c:out> </font> <font
									color="red"><c:out value="${requestScope.errorMessage}"></c:out>
								</font>

							</div>

							<table border="1">
								<tr></tr>
								<tr class="tableHeader">
									<td><font><strong>Name</strong></font></td>
									<td><font><strong>EmailId</strong></font></td>
									<td><font><strong>Access Type</strong></font></td>
									<td><font><strong>Activate</strong></font></td>
									<td><font><strong>Delete</strong></font></td>

								</tr>
								<c:forEach items="${requestScope.inactiveUser}" var="current">
									<tr>
										<td><font><c:out value="${current.firstName}"></c:out>
												<c:out value="${current.lastName}"></c:out></font></td>
										<td><font><c:out value="${current.emailId}"></c:out>
										</font></td>
										<td><font><c:out value="${current.memberType}"></c:out>
										</font></td>
										<td><input type="checkbox" name="activememberId"
											value="${current.member_Id}" /></td>
										<td><input type="checkbox" name="deletememberId"
											value="${current.member_Id}" /></td>
									</tr>
								</c:forEach>

							</table>
							<br />
							<table>
								<tr>
									<td></td>
									<td><input type="submit" value="Submit" /> <input
										type="reset" value="Reset" /></td>
									<td><a
										href="<%=request.getContextPath()%>/servlet/RegistrationServlet">Create
											User</a></td>
								</tr>
							</table>
						</c:if>
						<c:if
							test="${(sessionScope.loginType eq 'admin' || sessionScope.loginType eq 'staff') && requestScope.parameter eq 'manageLibrary' }">
							<input type="hidden" name="parameter" value="manageLibrary" />
							<div>
								<font color="blue" size="2"><strong>Approve Or
										Reject Book Request.</strong> </font>
							</div>
							<br />

							<div>
								<font color="green"><c:out
										value="${requestScope.successMessage}"></c:out> </font> <font
									color="red"><c:out value="${requestScope.errorMessage}"></c:out>
								</font>

							</div>

							<table border="1">
								<tr></tr>
								<tr class="tableHeader">
									<td><font><strong>Book ISBN</strong></font></td>
									<td><font><strong>RequestBy</strong></font></td>
									<td><font><strong>Approve</strong></font></td>
									<td><font><strong>Reject</strong></font></td>

								</tr>
								<c:forEach items="${requestScope.lockBooks}" var="current">
									<tr>
										<td><font><c:out value="${current.isbn}"></c:out></font></td>
										<td><font><c:out value="${current.issuedBy}"></c:out>
										</font></td>
										<td><input type="checkbox" name="issueId"
											value="${current.book_id}" /></td>
										<td><input type="checkbox" name="deleteId"
											value="${current.book_id}" /></td>
									</tr>
								</c:forEach>

							</table>
							<br />
							<table>
								<tr>
									<td></td>
									<td><input type="submit" name="issueBookBtn" value="IssueBook" /><input
										type="reset" value="Reset" /></td>
									<td><a
										href="<%=request.getContextPath()%>/servlet/ActionServlet?parameter=returnBook">Return
											Book</a></td>
								</tr>
							</table>
						</c:if>
						<c:if
							test="${(sessionScope.loginType eq 'admin' || sessionScope.loginType eq 'staff') && requestScope.parameter eq 'returnBook' }">
							<input type="hidden" name="parameter" value="returnBook" />

							<table cellspacing="2" cellpadding="2">
								<tr>

									<td colspan="4"><font color="blue">Welcome To Book
											Return.</font></td>
								</tr>
								<tr>
									<td colspan="4"></td>
								</tr>
								<tr>
									<td colspan="4"><font color="green"><c:out
												value="${requestScope.successMessage}"></c:out> </font> <font
										color="red"><c:out value="${requestScope.errorMessage}"></c:out>
									</font></td>
								</tr>

								<tr>
									<td><input type="text" name="searchUsername"  value="${ requestScope.searchUsername}"/> : <input
										type="submit" value="Search Username" name="userSearchbtn" /></td>
								</tr>
							</table>
							<c:if test="${ requestScope.userBooks ne null && not empty requestScope.userBooks}">
							<input type="hidden" name="parameter" value="returnBooSubmit" />
							
								<br />
								<table border="1">
									<tr></tr>
									<tr class="tableHeader">
										<td><font><strong>Member Name</strong></font></td>
										<td><font><strong>Book Title</strong></font></td>
										<td><font><strong>Issue Date</strong></font></td>
										<td><font><strong>Last Date</strong></font></td>
										<td><font><strong>Return</strong></font></td>

									</tr>
									<c:forEach items="${requestScope.userBooks}" var="current">
										<tr>
											<td><font><c:out value="${current.issuedBy}"></c:out></font></td>
											<td><font><c:out value="${current.booktitle}"></c:out></font></td>
											<td><font><c:out value="${current.issue_date}"></c:out></font></td>
											<td><font><c:out
														value="${current.last_date_to_return}"></c:out></font></td>
											<td><input type="checkbox" name="returnId"
												value="${current.book_id}" /></td>
										</tr>
									</c:forEach>

								</table>
								
								<br />
							<table>
								<tr>
									<td></td>
									<td><input type="submit" name="returnBookSubmit" value="Return Book" /> <input
										type="reset" value="Reset" /></td>
									
								</tr>
							</table>
								<br />


							</c:if>


						</c:if>
						<c:if
							test="${sessionScope.loginType eq 'member' && requestScope.parameter eq 'managebooks' }">

							<div>
								<font color="blue" size="2"><strong>Issued Book
										By LoggedIn User.</strong> </font>
							</div>
							<br />

							<div>
								<font color="green"><c:out
										value="${requestScope.successMessage}"></c:out> </font> <font
									color="red"><c:out value="${requestScope.errorMessage}"></c:out>
								</font>

							</div>

							<table border="1">
								<tr></tr>
								<tr class="tableHeader">
									<td><font><strong>Member Name</strong></font></td>
									<td><font><strong>Book Title</strong></font></td>
									<td><font><strong>Issue Date</strong></font></td>
									<td><font><strong>Last Date</strong></font></td>

								</tr>
								<c:forEach items="${requestScope.myIssuedBooks}" var="current">
									<tr>
										<td><font><c:out value="${current.issuedBy}"></c:out></font></td>
										<td><font><c:out value="${current.booktitle}"></c:out></font></td>
										<td><font><c:out value="${current.issue_date}"></c:out></font></td>
										<td><font><c:out
													value="${current.last_date_to_return}"></c:out></font></td>
									</tr>
								</c:forEach>

							</table>
							<br />
						</c:if>
						<c:if
							test="${sessionScope.loginType eq 'member' && requestScope.parameter eq 'managedue' }"> Member Functionality in progress..
								</c:if>




						<c:if
							test="${sessionScope.loginType eq 'staff' && requestScope.parameter eq 'manageEvents' }">Staff Event Functionality in progress..
								</c:if>
						<c:if
							test="${sessionScope.loginType eq 'staff' && requestScope.parameter eq 'managGallery' }">Staff Gallery Functionality in progress..
								</c:if>


					</form>
					<br />
				</div>
				<h3>
					<font color="blue"> Library Rules and Regulation</font>
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