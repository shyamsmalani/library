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
					<form name="loginForm" onsubmit="return validate(this)"
						action="LoginServlet" method="post">
						<table cellspacing="2" cellpadding="2">
							<tr>

								<td colspan="4"><font color="blue">Welcome To Member
										Login/Registration.</font></td>
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
								<td>User Type:</td>
								<td><select name="userType">
										<option value="">-select-</option>
										<option value="admin">Admin</option>
										<option value="staff">Staff</option>
										<option value="member">Member</option>
								</select></td>
							</tr>

							<tr>
								<td>Login Name:</td>
								<td><input type="text" name="username" /></td>
							</tr>
							<tr>
								<td>Password:</td>
								<td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td></td>
								<td><a
									href="<%=request.getContextPath()%>/servlet/RegistrationServlet">NewUser?</a>
									<a href="#">ForgotPassword?</a></td>
							</tr>
							<tr>
								<td colspan="4"></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" value="Login" /> <input
									type="reset" value="Reset" /></td>
							</tr>
						</table>
					</form>

				</div>


				<h3>
					<font color=blue> Library Rules and Regulation</font>
				</h3>

				<p>The <font color=blue>EDU Library</font> is a place for reading, studying,
					and writing; participating in programs, activities and meetings,
					and, for using or borrowing Library materials. We ask for your
					cooperation in maintaining an environment in which all Library
					customers can use and enjoy the Library’s resources, services, and
					facilities. These rules are posted for the comfort and protection
					of all who use the Library. These rules will be firmly enforced by
					Library staff and security. All Library users must comply with the
					rules of conduct listed below:</p>

				<div class="cleaner_h20"></div>

				<div class="image_fl">
					<img
						src="${pageContext.request.contextPath}/images/library_images01.jpg"
						alt="image" />
				</div>

				<div class="section_w250 float_r">

					<ul class="list_01">
						<li>Acts that are subject to prosecution under criminal or civil codes of law.</li>
						<li>Physical abuse, assault, public lewdness, indecent exposure or disorderly conduct.</li>
						<li>Smoking of any type.</li>
						<li>Taking Library materials into restrooms.</li>
						<li>Eating or drinking, except in designated areas.</li>
						<li>Tampering with security or safety devices.</li>
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