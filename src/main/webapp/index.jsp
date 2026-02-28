<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="gameBean" scope="session" class="game.GameBean" />

<!DOCTYPE html>
<html>
<head>
    <title>Tic Tac Toe</title>
</head>
<body>

<h1>Tic Tac Toe</h1>

<form action="entryServlet" method="post">
    <input type="submit" name="start" value="Inicia el jugador">
    <input type="submit" name="start" value="Inicia el computador">
</form>

</body>
</html>