<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Library Home</title>
<link href="${pageContext.request.contextPath}/css/library_style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
var content = ["<font style='font: italic; color: blue;'><strong>A room without books is like a body without a soul. - Marcus Tullius Cicero</strong> </font>", 
			   "<font style='font: italic; color: blue;'><strong>There is no friend as loyal as a book. - Ernest Hemingway</strong> </font>", 
			   "<font style='font: italic; color: blue;'><strong>A library is not a luxury but one of the necessities of life. - Henry Ward Beecher</strong> </font>"];
	var counter = 0;
	var myVar;

	function myStartFunction() {
		document.getElementById("quotes").innerHTML = "<font style='font: italic; color: blue;'><strong>Librarians have always been among the most thoughtful and helpful people. They are teachers without a classroom. - Willard Scott</strong> </font>";
	    myVar = setInterval(function(){ alertFunc(content[counter]); }, 5000);
	}

	function alertFunc(param) {
	    document.getElementById("quotes").innerHTML = param;
	    counter = counter + 1;
	    if(counter == 3){
	    counter = 0;
	    }
	    
	}
</script>
</head>
<body onload="myStartFunction()">
	
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
				<span id="quotes" style="position: absolute; top: 200px; left: 500px;"></span>		
		</div>
		
	</div>
	
</body>
</html>