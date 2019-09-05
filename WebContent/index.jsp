<%@page import="java.util.Collection"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="Controller.CreateTST"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
	
<script type="text/javascript" 
			src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.autocomplete.js"></script>


<style>
body {
	background-color: #fff;
}

h4 {
	font-size: 36px;
	color: #000080;
	font-weight: bold;
	text-align: center;
	margin: 30px 5px 15px 5px;
}

.setText {
	width: 500px;
	font-size: 14px;
	color: #000;
	padding: 10px;
	margin: 10px 5px 10px 5px;
	border: 1px solid #000;
}

.orangebtn {
	background: -webkit-gradient(linear, left top, left bottom, from(#faa51a),
		to(#f47a20));
	background: -moz-linear-gradient(top, #faa51a, #f47a20);
	color: #fef4e9;
	padding: 10px 25px;
	margin: 10px;
	font-size: 16px;
	background-color: #000;
}

.divCenter {
	position: fixed;
	top: 40%;
	left: 50%;
	transform: translate(-50%, -50%);
}

</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Search Engine</title>
<script src="http://code.jquery.com/jquery-1.12.4.js"></script>

<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/custom.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />


<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script> 
</head>
 <body >
<%
if(!request.getAttributeNames().hasMoreElements())
CreateTST.createTST();
%>

	<center>
		<div class="divCenter">
			<h4>Web Search Engine</h4>
			<form action="<%=request.getContextPath()%>/mainController" method="post" name="addname">

				<input type="text" name="sname" id="sname" placeholder="Search..." class="setText">
				<i class="fa fa-search" style="margin-left: -30px;display: inline-block;"></i>
				<input type="hidden" name="flag" value="tname"> <br /> 
				<input type="radio" name="command" value="0" checked="checked" />Word Occurrences
				<input type="radio" name="command" value="1" />Page Rank
				<input type="radio" name="command" value="2" />Spell Check<br /> 
				<input type="submit" value="Search" class="btn btn-info">

			</form>
			<h6>
			<%Enumeration attributeNames = request.getAttributeNames();           

	        while(attributeNames.hasMoreElements()) {
	            String current = (String) attributeNames.nextElement();
        		if(current.equals("occ")){
        			out.println(request.getAttribute("occ"));
        		}else if(current.equals("result")){
        			ArrayList<File> arr=(ArrayList<File>)request.getAttribute("result");
        			if(arr != null){
        			for(int i=0;i<arr.size();i++){
        				out.println(arr.get(i));
        			}
        		}else if(current.equals("output")){
        			String spell[]=(String[])request.getAttribute("output");
        			if(spell != null){
            			for(int i=0;i<spell.length;i++){
            				out.println(spell[i]);
            			}
        			}
        		}
	        }   			
	        }
			
			%></h6>
			<%-- <c:forEach var="output" items="${ans}">
			${output}
		</c:forEach> --%>
		</div>
	</center>
	
	<script type="text/javascript">
	
	
$("#sname").autocomplete("getdata.jsp",{
	extraParams:{
		filter:gettextboxvalue()
	}
});

function gettextboxvalue(){
	var check = document.getElementById("sname").value;
	return check;
}
</script>
</body>
 
<%-- <body>
	<div class="col-md-3"></div>
	<div class="col-md-6">
		<div class="row">
			<form action="<%=request.getContextPath()%>/mainController"
				method="post" name="addname">
				<input type="text" name="sname" placeholder="Search anything">
				<button class="btn btn-info">Go</button>
				<input type="hidden" name="flag" value="tname">

			</form>
			<c:forEach var="nums" items="${numbers}">
			${nums}
		</c:forEach>
		</div>
		<div class="row">
			<div class="col-md-3">
				<button class="btn btn-info" name="occurrences">Word
					occurrences</button>
			</div>

			<!-- <div class="col-md-2">
			<button class="btn btn-info">Page rank</button>
		</div> -->
			<div class="col-md-3">
				<button class="btn btn-info" name="spellCheck">Spell check</button>
			</div>
		</div>
		<div class="row">
			//list of URLS here
			<a href="K:\eclipse-workspace\Crawling\htmls\About W3C Standards.htm" target="_blank">About W3C Standards.htm</a><br>
		</div>
		<div class="row">
			//Occurrences here
			the - 13
		</div>
	</div>
</body> --%>
</html>