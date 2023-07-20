package blocks;

public class JShape extends tetris.TetrisBlock  implements tetris.Globals{

    public JShape() {
        super( new int[][]{{0,1},{0,1},{1,1}}, JSHAPE_COLOR);
    }
}
