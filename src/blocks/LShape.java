package blocks;

public class LShape extends tetris.TetrisBlock implements tetris.Globals{

    public LShape() {
        super(new int[][]{{1,0},{1,0},{1,1}}, LSHAPE_COLOR);
    }
}
