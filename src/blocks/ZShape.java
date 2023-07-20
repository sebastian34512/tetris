package blocks;

public class ZShape extends tetris.TetrisBlock implements tetris.Globals {

    public ZShape() {
        super(new int[][]{{1,1,0},{0,1,1}}, ZSHAPE_COLOR);
    }
}
