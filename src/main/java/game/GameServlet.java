package game;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/gameServlet")
public class GameServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        GameBean game = (GameBean) session.getAttribute("gameBean");
        if (game == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        int line = Integer.parseInt(request.getParameter("Line"));
        int col = Integer.parseInt(request.getParameter("Col"));

        game.playPlayerTurn(line, col);

        GameBean.GamePlayer winner = game.getWinner();

        if (winner == null && game.hasEmptyCell()) {
            game.playComputerTurn();
            winner = game.getWinner();
        }

        if (winner != null) {
            request.setAttribute("winner",
                winner == GameBean.GamePlayer.USER ? "You" : "Computer");
        }

        request.getRequestDispatcher("/game.jsp")
               .forward(request, response);
    }
}