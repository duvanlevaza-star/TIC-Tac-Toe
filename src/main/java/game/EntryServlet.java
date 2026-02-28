package game;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/entryServlet")
public class EntryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        GameBean game = (GameBean) session.getAttribute("gameBean");
        if (game == null) {
            game = new GameBean();
            session.setAttribute("gameBean", game);
        }

        boolean userFirst = request.getParameter("User") != null;
        game.setStartByUser(userFirst);
        game.startGame();

        request.getRequestDispatcher("/game.jsp")
               .forward(request, response);
    }
}