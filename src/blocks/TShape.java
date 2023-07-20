package blocks;

public class TShape extends tetris.TetrisBlock implements tetris.Globals{
    public TShape() {
        super(new int[][]{{1,1,1},{0,1,0}}, TSHAPE_COLOR);
    }
}
