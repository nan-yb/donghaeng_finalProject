<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><tiles:getAsString name="title" /></title>
<tiles:insertAttribute name="preScript" />
</head>
<body>
	<div id="header">
		<tiles:insertAttribute name="header" />
	</div>
	
	<div id="body">
		<tiles:insertAttribute name="body" />
	</div>
	
	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>













