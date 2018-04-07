<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Oseille</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body>
	<div id="header">
		<tiles:insertAttribute name="header" />
	</div>
	<div id="nav">
		<tiles:insertAttribute name="menu" />
	</div>
	<div id="mainContent">
		<tiles:insertAttribute name="body" />
	</div>
	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>