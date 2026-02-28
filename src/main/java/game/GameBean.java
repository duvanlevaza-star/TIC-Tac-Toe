package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBean implements Serializable {

    private static final int GRID_SIZE = 4;

    public enum GameState { NULL, O, X }

    public enum GamePlayer {
        USER(GameState.X),
        COMPUTER(GameState.O),
        NOBODY(GameState.NULL);

        GameState state;
        GamePlayer(GameState s) { this.state = s; }
    }

    private boolean userFirst = true;
    private GameState[][] gameStatus;

    public GameBean() {
        gameStatus = new GameState[GRID_SIZE][GRID_SIZE];
        startGame();
    }

    public void setStartByUser(boolean userFirst) {
        this.userFirst = userFirst;
    }

    public void startGame() {
        for (int i = 0; i < GRID_SIZE; i++)
            for (int j = 0; j < GRID_SIZE; j++)
                gameStatus[i][j] = GameState.NULL;

        if (!userFirst) {
            play(GamePlayer.COMPUTER, 1, 1);
        }
    }

    public List<Line> getGridLines() {
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < GRID_SIZE; i++) {
            lines.add(new Line(gameStatus[i], i));
        }
        return lines;
    }

    public List<Cell> getGridStatus(Line line) {
        List<Cell> cells = new ArrayList<>();
        for (int j = 0; j < GRID_SIZE; j++) {
            cells.add(new Cell(gameStatus[line.getIndex()][j], line.getIndex(), j));
        }
        return cells;
    }

    public void playPlayerTurn(int line, int col) {
        play(GamePlayer.USER, line, col);
    }

    public void playComputerTurn() {
        Random r = new Random();
        int l, c;
        do {
            l = r.nextInt(3);
            c = r.nextInt(3);
        } while (gameStatus[l][c] != GameState.NULL);

        play(GamePlayer.COMPUTER, l, c);
    }

    private void play(GamePlayer player, int line, int col) {
        if (gameStatus[line][col] == GameState.NULL) {
            gameStatus[line][col] = player.state;
        }
    }

    public GamePlayer getWinner() {

        for (int i = 0; i < 3; i++) {
            if (gameStatus[i][0] != GameState.NULL &&
                gameStatus[i][0] == gameStatus[i][1] &&
                gameStatus[i][1] == gameStatus[i][2])
                return getPlayer(gameStatus[i][0]);
        }

        for (int j = 0; j < 3; j++) {
            if (gameStatus[0][j] != GameState.NULL &&
                gameStatus[0][j] == gameStatus[1][j] &&
                gameStatus[1][j] == gameStatus[2][j])
                return getPlayer(gameStatus[0][j]);
        }

        if (gameStatus[0][0] != GameState.NULL &&
            gameStatus[0][0] == gameStatus[1][1] &&
            gameStatus[1][1] == gameStatus[2][2])
            return getPlayer(gameStatus[0][0]);

        if (gameStatus[0][2] != GameState.NULL &&
            gameStatus[0][2] == gameStatus[1][1] &&
            gameStatus[1][1] == gameStatus[2][0])
            return getPlayer(gameStatus[0][2]);

        return null;
    }

    private GamePlayer getPlayer(GameState s) {
        for (GamePlayer p : GamePlayer.values()) {
            if (p.state == s) return p;
        }
        return null;
    }

    public boolean hasEmptyCell() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (gameStatus[i][j] == GameState.NULL)
                    return true;
        return false;
    }
}