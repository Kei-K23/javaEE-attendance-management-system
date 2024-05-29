<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat"%>

<%
// Initialize date variables
List<String> dates = new ArrayList<>();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Calendar cal = Calendar.getInstance();
String today = sdf.format(cal.getTime());

// Generate the next 7 days
for (int i = 0; i < 7; i++) {
	dates.add(sdf.format(cal.getTime()));
	cal.add(Calendar.DAY_OF_MONTH, 1);
}

// Set the dates list and today's date as request attributes
request.setAttribute("dates", dates);
request.setAttribute("today", today);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
</head>
<body>
	<c:choose>
		<c:when test="${not empty sessionScope.username}">
			<h1>Welcome, ${sessionScope.username}</h1>
			<a href="${pageContext.request.contextPath}/logout"
				class="btn btn-danger">Logout</a>

			<!-- Display the next 7 days -->
			<h2>Next 7 Days:</h2>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Date</th>
						<th scope="col">Check in</th>
						<th scope="col">Check out</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="date" items="${dates}">
						<tr>
							<th scope="row">${date}</th>
							<td>
								<form>
									<button <c:if test="${date != today}">disabled</c:if>>Check
										in</button>
								</form>
							</td>
							<td>
								<form>
									<button>Check out</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<ul>
			</ul>
		</c:when>
		<c:otherwise>
			<p>
				You are not logged in. <a
					href="${pageContext.request.contextPath}/login.jsp">Login here</a>.
			</p>
		</c:otherwise>
	</c:choose>
</body>
</html>
