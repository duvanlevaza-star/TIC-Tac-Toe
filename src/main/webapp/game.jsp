<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:useBean id="gameBean" scope="session" class="game.GameBean" />
<!DOCTYPE html>
<html>
<head>
<title>Tic Tac Toe</title>
</head>
<body>

<h1>Tic Tac Toe</h1>

<table border="5">
<c:forEach var="line" items="${gameBean.gridLines}">
<tr>
<c:forEach var="cell" items="${gameBean.getGridStatus(line)}">
<td width="60" height="60" align="center">

<c:choose>
<c:when test="${cell.state == 'X'}">X</c:when>
<c:when test="${cell.state == 'O'}">O</c:when>
<c:otherwise>
<c:if test="${winner == null}">
<a href="gameServlet?Line=${cell.line}&Col=${cell.col}">-</a>
</c:if>
<c:if test="${winner != null}">-</c:if>
</c:otherwise>
</c:choose>

</td>
</c:forEach>
</tr>
</c:forEach>
</table>

<c:if test="${winner != null}">
<h2>${winner} gano!</h2>
<a href="index.jsp">Play again</a>
</c:if>

</body>
</html>