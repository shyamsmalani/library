<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ContactUS</title>

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
					<form name="contactUsForm" action="ContactUsServlet" method="post">
						<table cellspacing="2" cellpadding="2">
							<tr>

								<td colspan="4"><font color="blue">Welcome To
										ContactUs.</font></td>
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
								<td>Name :</td>
								<td><input type="text" name="username" id="username"
									onchange="loadDoc()" />
									<div style="color: red;" id="message"></div></td>
							</tr>
							<tr>
								<td>Email Id :</td>
								<td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td>Mobile Number:</td>
								<td><input type="text" name="mnum" /></td>
							</tr>
							<tr>
								<td>Web Address :</td>
								<td><input type="password" name="cpassword" /></td>
							</tr>
							<tr>
								<td>Comment(Optional):</td>
								<td><textarea name="description" rows="6" cols="40"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="4"></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="button" value="Submit" /> <input
									type="reset" value="Reset" /></td>
							</tr>
						</table>
					</form>

				</div>


				<h3>
					<font color=blue> About Library EDU </font>
				</h3>

				<p>
					The <font color=blue>EDU Library</font> is the biggest library of Bhopal and 
					the well known library chain in the Country by number of items catalogued. 
					It holds well over 150 million items from many countries. As a legal deposit library, 
					the  <font color=blue>EDU Library</font> receives copies of all books produced in the Country, 
					including a significant proportion of overseas titles distributed in the India. 
					The Library is a non-departmental public body sponsored by the Department for Culture,
					 Media and Sport.
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
						<li>Account Chief : Mrs. P. S. Malani</li>
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