<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>DEBATE EVENT</title>
</head>
<body>

	<div class="container">
	<h3 style="background-color:#4CC417;  height:77px; font-size:200%; text-align: center; padding:1%" > DEBATE EVENT </h3>

		<hr>

		<!-- Add a search form -->

		<form action="/DebateEvent/students/search" class="form-inline">
	
	<a href="/DebateEvent/students/showFormForAdd"
	class="btn btn-primary btn-sm mb-3"> Add Student </a>
	<input type="search" name="name" placeholder="Name" class="form-control-sm ml-5 mr-2 mb-3" /> 
	<input type="search" name="department" placeholder="Department" class="form-control-sm ml-5 mr-2 mb-3" /> 
	<input type="search" name="country" placeholder="Country" class="form-control-sm mr-2 mb-3" />
	
	<button type="submit" class="btn btn-success btn-sm mb-3">Search</button>
	
	</form>
	
	<table class="table table-bordered table-striped">
			<thead style="background-color:#4CC417;">
				<tr>
					<th style="color:white;">Name</th>
					<th style="color:white;">Department</th>
					<th style="color:white;">Country</th>
					<th style="color:white;">Action</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${Students}" var="tempStudent">
					<tr>
						<td><c:out value="${tempStudent.name}" /></td>
						<td><c:out value="${tempStudent.department}" /></td>
						<td><c:out value="${tempStudent.country}" /></td>
						<td>
							<!-- Add "update" button/link --> <a
							href="/DebateEvent/students/showFormForUpdate?studentId=${tempStudent.id}"
							class="btn btn-info btn-sm"> Update </a> <!-- Add "delete" button/link -->
							<a href="/DebateEvent/students/delete?studentId=${tempStudent.id}"
							class="btn btn-danger btn-sm"
							onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
								Delete </a>

						</td>

					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

</body>
</html>