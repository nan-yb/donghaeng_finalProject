<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><tiles:getAsString name="title" /></title>
<tiles:insertAttribute name="manageScript" />
</head>
<body>
	<div id="manageHeader">
		<tiles:insertAttribute name="manageHeader" />
	</div>
	
	<div id="body">
		<tiles:insertAttribute name="body" />
	</div>
	
	<div id="manageFooter">
		<tiles:insertAttribute name="manageFooter" />
	</div>
</body>
</html>













