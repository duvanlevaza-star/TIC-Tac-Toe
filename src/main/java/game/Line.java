package game;

public class Line {

    private GameBean.GameState[] datas;
    private int index;

    public Line(GameBean.GameState[] datas, int index) {
        this.datas = datas;
        this.index = index;
    }

    public GameBean.GameState[] getDatas() {
        return datas;
    }

    public int getIndex() {
        return index;
    }
}