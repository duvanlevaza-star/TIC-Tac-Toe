package game;

public class Cell {

    private int line;
    private int col;
    private GameBean.GameState state;

    public Cell(GameBean.GameState state, int line, int col) {
        this.state = state;
        this.line = line;
        this.col = col;
    }

    public GameBean.GameState getState() {
        return state;
    }

    public int getLine() {
        return line;
    }

    public int getCol() {
        return col;
    }
}