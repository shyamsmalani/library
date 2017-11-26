<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Library Home</title>
<link href="${pageContext.request.contextPath}/css/library_style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="script.js">
	var text = [ "Q1", "Q2", "Q3" ];
	var counter = 0;
	var elem = document.getElementById("changeText");
	setInterval(change, 1000);
	function change() {
		elem.innerHTML = text[counter];
		counter++;
		if (counter >= text.length) {
			counter = 0;
		}
	}
</script>
</head>
<body>

	<div id="templatemo_header_wrapper">
		<div id="templatemo_header" style="background: url(${pageContext.request.contextPath}/images/library_header.jpg) no-repeat;">
			<div id="site_title">
				<h1>
					<a href="#" target="_parent"> <img
						src="${pageContext.request.contextPath}/images/library_logo.png" alt="Site Title" /> 
						<span>Book changes Life</span>
					</a>
				</h1>
			</div>
		</div>
	</div>
</body>
</html>