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
				document.regForm.username.focus()
				return false
			}
		}

		if (!isNaN(document.regForm.username.value)) {
			alert("User Name  must  be  char's & can't be null")
			document.regForm.username.value = ""
			document.regForm.username.focus()
			return false
		}

		if (!isNaN(document.regForm.password.value)) {
			alert("Password  must  be  char's & can't be null")
			document.regForm.password.value = ""
			document.regForm.password.focus()
			return false
		}

		return true
	}
	try {
		function loadDoc() {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200
						&& this.responseText != null) {
					document.getElementById("message").innerHTML = this.responseText;
				}
			};
			xhttp.open("GET", "RegistrationServlet?username="
					+ document.regForm.username.value, true);
			xhttp.send();
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
					<form name="regForm" onsubmit="return validate(this)"
						action="RegistrationServlet" method="post">
						<table cellspacing="2" cellpadding="2">
							<tr>

								<td colspan="4"><font color="blue">Welcome To Member
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
							<c:if test="${sessionScope.loginType eq 'admin'}">
								<tr>
									<td>Access Type:</td>
									<td><select name="userType">
											<option value="">-select-</option>
											<option value="admin">Admin</option>
											<option value="staff">Staff</option>
											<option value="member">Member</option>
									</select></td>
								</tr>
							</c:if>
							<tr>
								<td>Login Id:</td>
								<td><input type="text" name="username" id="username"
									onchange="loadDoc()" />
									<div style="color: red;" id="message"></div></td>
							</tr>
							<tr>
								<td>Password:</td>
								<td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td>Confirm Password:</td>
								<td><input type="password" name="cpassword" /></td>
							</tr>
							<tr>
								<td colspan="4"></td>
							</tr>
							<tr>
								<td>First Name:</td>
								<td><input type="text" name="fname" /></td>
							</tr>
							<tr>
								<td>Last Name:</td>
								<td><input type="text" name="lname" /></td>
							</tr>
							<tr>
								<td>Mobile Number:</td>
								<td><input type="text" name="mnum" /></td>
							</tr>

							<tr>
								<td>Email-Id:</td>
								<td><input type="text" name="emailid" /></td>
							</tr>
							<tr>
								<td>Id-Type</td>
								<td><select name="idType">
										<option value="">-select-</option>
										<option value="pan">PanCard</option>
										<option value="adhar">AdharCard</option>
										<option value="voter">VoterId</option>
										<option value="licence">License</option>
								</select></td>
							</tr>
							<tr>
								<td>Unique-Id:</td>
								<td><input type="text" name="uId" /></td>
							</tr>

							<tr>
								<td colspan="4"></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" value="Register" /> <input
									type="reset" value="Reset" /></td>
							</tr>
						</table>
					</form>

				</div>


				<h3>
					<font color=blue> About Library EDU </font>
				</h3>

				<p>
					The <font color=blue>EDU Library</font> is the biggest library of
					Bhopal and the well known library chain in the Country by number of
					items catalogued. It holds well over 150 million items from many
					countries. As a legal deposit library, the <font color=blue>EDU
						Library</font> receives copies of all books produced in the Country,
					including a significant proportion of overseas titles distributed
					in the India. The Library is a non-departmental public body
					sponsored by the Department for Culture, Media and Sport.
				</p>

				<div class="cleaner_h20"></div>

				<div class="image_fl">
					<img
						src="${pageContext.request.contextPath}/images/library_images01.jpg"
						alt="image" />
				</div>

				<div class="section_w250 float_r">

					<ul style="color: black;">
						<li>Director : Mr. A. K. Singhal</li>
						<li>precedent : Mr. S. S. Malani</li>
						<li>Account Chief : Mrs. P. Kabra</li>
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